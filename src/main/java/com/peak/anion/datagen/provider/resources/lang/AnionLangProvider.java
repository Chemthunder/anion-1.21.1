package com.peak.anion.datagen.provider.resources.lang;

import com.peak.anion.core.index.AnionBlocks;
import com.peak.anion.core.index.AnionItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author AcoYT
 */
public class AnionLangProvider extends FabricLanguageProvider {
    public AnionLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        AnionBlocks.BLOCKS.registerLang(registries, builder);
        AnionItems.ITEMS.registerLang(registries, builder);
    }
}