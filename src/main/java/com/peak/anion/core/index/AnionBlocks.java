package com.peak.anion.core.index;

import com.mojang.serialization.MapCodec;
import com.peak.anion.core.Anion;
import com.peak.anion.core.block.AttractorBlock;
import com.peak.anion.core.block.ChargedIronBlock;
import com.peak.anion.core.block.GeneratorBlock;
import com.peak.anion.core.block.VoltEaterBlock;
import com.peak.anion.core.index.ref.AnionBlockSoundGroups;
import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.acoyt.acornlib.impl.item.TranslationBlockItem;
import net.acoyt.acornlib.impl.util.Util;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Function;

/**
 * @author Chemthunder
 */
public interface AnionBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Anion.MOD_ID);

    Block ANION_GENERATOR = registerWithItem("anion_generator", GeneratorBlock::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
            .sounds(BlockSoundGroup.WOOD), GeneratorBlock.CODEC);

    Block ATTRACTOR = registerWithItem("attractor", AttractorBlock::new, AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)
            .ticksRandomly()
            .sounds(AnionBlockSoundGroups.ELECTRICAL), AttractorBlock.CODEC);

    Block CHARGED_IRON_BLOCK = registerWithItem("charged_iron_block", ChargedIronBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
            .sounds(BlockSoundGroup.METAL), Block.CODEC);

    Block VOLT_EATER = registerWithItem("volt_eater", VoltEaterBlock::new, AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)
            .sounds(AnionBlockSoundGroups.ELECTRICAL), VoltEaterBlock.CODEC);

    private static Block registerWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings,
                                          MapCodec<? extends Block> codec) {
        Block block = factory.apply(settings);
        Util.register(Anion.id(name), itemSettings -> new TranslationBlockItem(block, itemSettings), new Item.Settings());
        Registry.register(Registries.BLOCK_TYPE, name, codec);
        return BLOCKS.register(name, block);
    }

    static void init() {}
}
