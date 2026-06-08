package com.peak.anion.mixin;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import com.peak.anion.core.index.AnionBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = LightningRodBlock.class)
public abstract class LightningRodBlockMixin {
    @Inject(method = "setPowered", at = @At(value = "TAIL"))
    private void anion$chickenFried(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        if (world.getBlockState(pos.down()).equals(Blocks.IRON_BLOCK.getDefaultState())) {
            world.setBlockState(pos.down(), AnionBlocks.CHARGED_IRON_BLOCK.getDefaultState());

            world.playSound(
                    null,
                    pos.down(),
                    SoundEvents.ITEM_TRIDENT_THUNDER.value(),
                    SoundCategory.BLOCKS,
                    1F,
                    0.2F
            );

            world.getEntitiesByClass(
                    PlayerEntity.class,
                    new Box(pos.down()).expand(15),
                    entity -> true
            ).forEach(capture -> {
                if (capture instanceof ScreenShaker shaker) {
                    shaker.addScreenShake(1.3F, 40);
                }
            });
        }
    }
}
