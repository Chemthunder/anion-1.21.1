package com.peak.anion.core.block;

import com.mojang.serialization.MapCodec;
import com.peak.anion.api.block.ChargeableBlock;
import com.peak.anion.api.block.InformationalBlock;
import com.peak.anion.core.block.entity.AttractorBlockEntity;
import com.peak.anion.core.util.AnionUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.peak.anion.core.util.AnionUtil.SHOCKIES;

/**
 * @author Chemthunder
 */
public class AttractorBlock extends BlockWithEntity implements ChargeableBlock, InformationalBlock {
    public static final MapCodec<AttractorBlock> CODEC = createCodec(AttractorBlock::new);
    public static final BooleanProperty HAS_HOPPER_BELOW = BooleanProperty.of("has_hopper_below");

    public AttractorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SHOCKIES, 0).with(HAS_HOPPER_BELOW, false));
    }

    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHOCKIES);
        builder.add(HAS_HOPPER_BELOW);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (canTick(state)) {
            if (random.nextBetween(0, 14) > 9) {
                this.decrement(world, pos, state);
            }
        }
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        BlockPos downPos = pos.down();

        if (world.getBlockEntity(downPos) instanceof HopperBlockEntity) {
            world.setBlockState(
                    pos,
                    state.with(HAS_HOPPER_BELOW, true),
                    Block.NOTIFY_LISTENERS
            );
        } else {
            world.setBlockState(
                    pos,
                    state.with(HAS_HOPPER_BELOW, false),
                    Block.NOTIFY_LISTENERS
            );
        }

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();

        if (world.getBlockEntity(pos.down()) instanceof HopperBlockEntity) {
            return this.getDefaultState().with(HAS_HOPPER_BELOW, true);
        }

        return super.getPlacementState(ctx);
    }

    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AttractorBlockEntity(pos, state);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World ignoredWorld, BlockState ignoredState, BlockEntityType<T> type) {
        return (world, pos, state, blockEntity) -> {
            if (blockEntity instanceof AttractorBlockEntity attractorBlockEntity && world.getBlockEntity(pos.down()) instanceof HopperBlockEntity hopper) {
                if (canTick(state)) {
                    attractorBlockEntity.tick(world, pos, state, hopper);
                }
            }
        };
    }

    public static boolean canTick(BlockState state) {
        if (state.get(HAS_HOPPER_BELOW)) {
            if (state.get(SHOCKIES) > 0) {
                return true;
            }
        }

        return false;
    }

    public List<Text> getInfoDisplayTexts(BlockState state, PlayerEntity viewer) {
        return List.of(
                Text.translatable("display.anion.attractor.0", state.get(SHOCKIES)),
                AnionUtil.SPACER,
                Text.translatable(state.get(HAS_HOPPER_BELOW) ? "display.anion.attractor.1.has_hopper" : "display.anion.attractor.1.no_hopper")
        );
    }
}