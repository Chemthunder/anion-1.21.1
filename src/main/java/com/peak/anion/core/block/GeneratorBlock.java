package com.peak.anion.core.block;

import com.mojang.serialization.MapCodec;
import com.peak.anion.core.block.entity.GeneratorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

/**
 * @author AcoYT
 */
public class GeneratorBlock extends BlockWithEntity {
    public static final MapCodec<GeneratorBlock> CODEC = createCodec(GeneratorBlock::new);

    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final BooleanProperty SUBMERGED = BooleanProperty.of("submerged");

    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public GeneratorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWERED, false).with(SUBMERGED, false));
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            boolean powered = world.isReceivingRedstonePower(pos);
            if (state.get(POWERED) != powered) {
                world.setBlockState(pos, state.with(POWERED, powered), Block.NOTIFY_LISTENERS);
                if (state.get(POWERED) != powered) {
                    world.playSound(
                            null,
                            pos,
                            powered ? SoundEvents.BLOCK_COPPER_TRAPDOOR_OPEN : SoundEvents.BLOCK_COPPER_TRAPDOOR_CLOSE, SoundCategory.BLOCKS,
                            1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F
                    );

                    world.emitGameEvent(null, powered ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
                }
            }

            boolean submerged = world.getFluidState(pos.up()).isOf(Fluids.WATER);
            if (state.get(SUBMERGED) != submerged) {
                world.setBlockState(pos, state.with(SUBMERGED, submerged), Block.NOTIFY_LISTENERS);
            }
        }

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, SUBMERGED);
        super.appendProperties(builder);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        FluidState fluidState = world.getFluidState(pos.up());

        return this.getDefaultState().with(POWERED, world.isReceivingRedstonePower(pos)).with(SUBMERGED, fluidState.isOf(Fluids.WATER));
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BubbleColumnBlock.update(world, pos.up(), state, Blocks.MAGMA_BLOCK.getDefaultState());
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && neighborState.isOf(Blocks.WATER)) {
            world.scheduleBlockTick(pos, this, 20);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 20);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World ignoredWorld, BlockState ignoredState, BlockEntityType<T> type) {
        return (world, pos, state, blockEntity) -> {
            if (blockEntity instanceof GeneratorBlockEntity generatorBlock) {
                generatorBlock.tick(world, pos, state);
            }
        };
    }

    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GeneratorBlockEntity(pos, state);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
