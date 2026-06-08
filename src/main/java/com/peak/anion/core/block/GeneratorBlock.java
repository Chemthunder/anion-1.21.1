package com.peak.anion.core.block;

import com.mojang.serialization.MapCodec;
import com.peak.anion.core.block.entity.GeneratorBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author AcoYT
 */
public class GeneratorBlock extends BlockWithEntity {
    private static final MapCodec<GeneratorBlock> CODEC = createCodec(GeneratorBlock::new);

    public GeneratorBlock(Settings settings) {
        super(settings);
    }

    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
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
}
