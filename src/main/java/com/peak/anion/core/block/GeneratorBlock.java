package com.peak.anion.core.block;

import com.mojang.serialization.MapCodec;
import com.peak.anion.core.block.entity.GeneratorBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

/**
 * @author AcoYT
 */
public class GeneratorBlock extends BlockWithEntity {
    private static final MapCodec<GeneratorBlock> CODEC = createCodec(GeneratorBlock::new);

    public static final BooleanProperty POWERED = Properties.POWERED;

    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public GeneratorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWERED, false));
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
                            powered ? SoundEvents.BLOCK_VAULT_ACTIVATE : SoundEvents.BLOCK_VAULT_DEACTIVATE, SoundCategory.BLOCKS,
                            1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F
                    );

                    world.emitGameEvent(null, powered ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
                }
            }
        }
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
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