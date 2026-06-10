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

        builder.add("itemGroup.anion", "Anion");

        builder.add("tooltip.anion.battery", "Charged with %sS");

        builder.add("display.anion.volteater.0", "This Volteater is storing %s charges.");
        builder.add("display.anion.volteater.1", "Interact with a Battery to transfer the electricity.");

        builder.add("display.anion.attractor.0", "This Attractor is charged with %s Shockies.");
        builder.add("display.anion.attractor.1.has_hopper", "This Attractor will pick up nearby dropped items and deposit them in the Hopper below.");
        builder.add("display.anion.attractor.1.no_hopper", "Place a Hopper below this Attractor for it to begin attracting dropped items.");
    }
}