package com.peak.anion.mixin;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import com.peak.anion.core.block.VolteaterBlock;
import com.peak.anion.core.index.AnionBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
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
    private void anion$chargeIron(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        BlockPos blockPos = pos.down();
        if (world.getBlockState(blockPos).isOf(Blocks.IRON_BLOCK)) {
            world.setBlockState(blockPos, AnionBlocks.CHARGED_IRON_BLOCK.getDefaultState());

            world.playSound(
                    null,
                    blockPos,
                    SoundEvents.ITEM_TRIDENT_THUNDER.value(), SoundCategory.BLOCKS,
                    1.0F, 0.2F
            );

            for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class, new Box(blockPos).expand(15), EntityPredicates.EXCEPT_SPECTATOR)) {
                if (player instanceof ScreenShaker shaker) {
                    shaker.addScreenShake(1.3F, 15);
                }
            }
        }

        if (world.getBlockState(blockPos).isOf(AnionBlocks.VOLTEATER)) {
            VolteaterBlock.charge(world, blockPos, world.getBlockState(blockPos));

            world.playSound(
                    null,
                    blockPos,
                    SoundEvents.ITEM_TRIDENT_THUNDER.value(), SoundCategory.BLOCKS,
                    1.0F, 0.4F
            );

            world.playSound(
                    null,
                    blockPos,
                    SoundEvents.ENTITY_WITHER_HURT, SoundCategory.BLOCKS,
                    0.7F, 0.2F
            );
        }
    }
}