package com.peak.anion.core.util;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * @author AcoYT
 * @author Chemthunder
 */
public class AnionUtil {
    /**
     * Central Int Property for blocks requiring Shockies to run,
     */
    public static final IntProperty SHOCKIES = IntProperty.of("shockies", 0, 8);

    public static final MutableText SPACER = Text.literal("-").formatted(Formatting.DARK_GRAY);

    public static void setCharges(World world, BlockPos pos, BlockState state, int charges) {
        world.setBlockState(pos, state.withIfExists(SHOCKIES, MathHelper.clamp(charges, 0, 8)));
    }

    public static int incrementCharges(World world, BlockPos pos, BlockState state, int charges) {
        world.setBlockState(pos, state.withIfExists(SHOCKIES, MathHelper.clamp(state.get(SHOCKIES) + 1, 0, 8)));
        return state.get(SHOCKIES) - charges;
    }

    public static void decrementCharges(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.withIfExists(SHOCKIES, MathHelper.clamp(state.get(SHOCKIES) - 1, 0, 8)));
    }
}
