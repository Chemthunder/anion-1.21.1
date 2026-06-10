package com.peak.anion.core.block.entity;

import com.peak.anion.core.block.AttractorBlock;
import com.peak.anion.core.index.AnionBlockEntities;
import com.peak.anion.core.util.AnionUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class AttractorBlockEntity extends BlockEntity {
    public AttractorBlockEntity(BlockPos pos, BlockState state) {
        super(AnionBlockEntities.ATTRACTOR, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.getBlockEntity(pos.down()) instanceof HopperBlockEntity hopper && state.get(AttractorBlock.HAS_HOPPER_BELOW) && state.get(AnionUtil.SHOCKIES) > 0) {
            for (ItemEntity item : world.getEntitiesByClass(ItemEntity.class, new Box(pos).expand(14), item -> true)) {
                ItemStack stack = item.getStack();

                boolean discard = false;

                for (int i = 0; i < hopper.size(); i++) {
                    ItemStack hopperStack = hopper.getStack(i);

                    if (hopperStack.isEmpty()) {
                        hopper.setStack(i, stack);
                        discard = true;
                        break;
                    } else if (ItemStack.areItemsAndComponentsEqual(stack, hopperStack)) {
                        int sum = stack.getCount() + hopperStack.getCount();
                        if (sum > stack.getMaxCount()) {
                            hopperStack.setCount(hopperStack.getMaxCount());
                            item.getStack().setCount(sum - hopperStack.getMaxCount());
                            break;
                        }
                    }
                }

                if (discard) item.discard();
            }
        }
    }
}
