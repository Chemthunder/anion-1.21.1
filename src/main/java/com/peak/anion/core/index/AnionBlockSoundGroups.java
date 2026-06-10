package com.peak.anion.core.index;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

/**
 * @author Chemthunder
 */
public interface AnionBlockSoundGroups {
    BlockSoundGroup ELECTRICAL = new BlockSoundGroup(
            1.0F, 1.0F,
            SoundEvents.BLOCK_COPPER_BREAK,
            SoundEvents.BLOCK_COPPER_BULB_STEP,
            SoundEvents.BLOCK_COPPER_BULB_PLACE,
            SoundEvents.BLOCK_COPPER_BULB_HIT,
            SoundEvents.BLOCK_COPPER_BULB_FALL
    );
}
