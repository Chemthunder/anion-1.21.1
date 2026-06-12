package com.peak.anion.api.block;

import com.peak.anion.core.util.AnionUtil;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author AcoYT
 * @author Chemthunder
 */
public interface ChargeableBlock {
    default int increment(World world, BlockPos pos, BlockState state, int charges) {
        return AnionUtil.incrementCharges(world, pos, state, charges);
    }

    default void decrement(World world, BlockPos pos, BlockState state) {
        AnionUtil.decrementCharges(world, pos, state);
    }

    default void set(World world, BlockPos pos, BlockState state, int charges) {
        AnionUtil.setCharges(world, pos, state, charges);
    }

    default void onCharged(World world, BlockPos pos, BlockState state) {}
}
