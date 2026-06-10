package com.peak.anion.core.block.entity;

import com.peak.anion.core.index.AnionBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Chemthunder
 */
public class AttractorBlockEntity extends BlockEntity {
    public AttractorBlockEntity(BlockPos pos, BlockState state) {
        super(AnionBlockEntities.ATTRACTOR, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state, HopperBlockEntity hopper) {
        List<ItemEntity> NEARBY_ITEMS = world.getEntitiesByClass(
                ItemEntity.class,
                new Box(pos).expand(15),
                entity -> true
        );

        NEARBY_ITEMS.forEach(itemEntity -> {
            world.addParticle(
                    ParticleTypes.END_ROD,
                    false,
                    itemEntity.getX(),
                    itemEntity.getY(),
                    itemEntity.getZ(),
                    0,
                    0,
                    0
            );

            ItemStack stack = itemEntity.getStack();

            for (int slot = 0; slot < hopper.size(); slot++) {
                ItemStack hopperStack = hopper.getStack(slot);

                if (hopperStack.isEmpty()) {
                    hopper.setStack(slot, stack); /// If empty slot simply insert
                    break;
                } else {
                    if (stack.getItem() == hopperStack.getItem()) { /// Merge items if not empty and applicable
                        hopperStack.setCount(hopperStack.getCount() + stack.getCount());
                        break;
                    }
                }
            }

            itemEntity.discard();
        });
    }
}
