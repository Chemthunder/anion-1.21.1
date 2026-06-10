package com.peak.anion.core.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * @author AcoYT
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

    public static void incrementCharges(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.withIfExists(SHOCKIES, MathHelper.clamp(state.get(SHOCKIES) + 1, 0, 8)));
    }

    public static void decrementCharges(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.withIfExists(SHOCKIES, MathHelper.clamp(state.get(SHOCKIES) - 1, 0, 8)));
    }

    public static int itemBarStep(ItemStack stack, float value, int max) {
        return Math.round(value / max * 13);
    }

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