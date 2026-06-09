package com.peak.anion.core.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * @author AcoYT
 */
public class AnionUtil {
    public static Box getUpwardsOfWater(World world, BlockPos pos, Vec3d offset) {
        int y = pos.getY() + 1;
        for (int i = pos.getY() + 1; i < world.getTopY(); i++) {
            BlockPos blockPos = new BlockPos(pos.getX(), i, pos.getZ());
            BlockState state = world.getBlockState(blockPos);
            if (state.isOf(Blocks.BUBBLE_COLUMN) || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() >= 8 && state.getFluidState().isStill()) {
                y++;
            }
        }

        return new Box(pos).offset(offset).withMaxY(pos.getY() + 12);
    }
}