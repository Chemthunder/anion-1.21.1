package com.peak.anion.datagen.provider.tag;

import com.peak.anion.core.index.tag.AnionItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class AnionItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public AnionItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(AnionItemTags.MAGNETIC)
                .addOptionalTag(ConventionalItemTags.IRON_INGOTS)
                .addOptionalTag(ConventionalItemTags.IRON_NUGGETS)
                .addOptionalTag(ConventionalItemTags.IRON_ORES)
                .addOptionalTag(ConventionalItemTags.IRON_RAW_MATERIALS)
                .addOptionalTag(BlockTags.IRON_ORES.id())
                .setReplace(false);
    }
}
