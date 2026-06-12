package com.peak.anion.mixin;

import com.peak.anion.api.block.ChargeableBlock;
import com.peak.anion.core.index.AnionBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author AcoYT
 * @author Chemthunder
 */
@Mixin(value = LightningRodBlock.class)
public abstract class LightningRodBlockMixin {
    @Inject(method = "setPowered", at = @At(value = "TAIL"))
    private void anion$chargeIron(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.IRON_BLOCK)) {
            world.setBlockState(blockPos, AnionBlocks.CHARGED_IRON_BLOCK.getDefaultState());

            world.playSound(
                    null,
                    blockPos,
                    SoundEvents.ITEM_TRIDENT_THUNDER.value(), SoundCategory.BLOCKS,
                    1.0F, 0.2F
            );
        }

        if (blockState.getBlock() instanceof ChargeableBlock chargeable) {
            chargeable.onCharged(world, blockPos, blockState);
        }
    }
}
