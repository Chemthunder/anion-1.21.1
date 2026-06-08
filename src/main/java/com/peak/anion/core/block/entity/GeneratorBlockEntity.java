package com.peak.anion.core.block.entity;

import com.peak.anion.core.index.AnionBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author AcoYT
 */
public class GeneratorBlockEntity extends BlockEntity {
    public GeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(AnionBlockEntities.GENERATOR, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {}
}
