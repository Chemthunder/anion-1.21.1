package com.peak.anion.core.index;

import com.peak.anion.core.Anion;
import com.peak.anion.core.block.ChargedIronBlock;
import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

/**
 * @author Chemthunder
 */
public interface AnionBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Anion.MOD_ID);

    Block CHARGED_IRON_BLOCK = BLOCKS.register("charged_iron_block", ChargedIronBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
            .sounds(BlockSoundGroup.METAL)
    );

    static void init() {}
}
