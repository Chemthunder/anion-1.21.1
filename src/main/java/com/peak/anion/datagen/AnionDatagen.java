package com.peak.anion.datagen;

import com.peak.anion.datagen.provider.AnionBlockLootTableProvider;
import com.peak.anion.datagen.provider.AnionRecipeProvider;
import com.peak.anion.datagen.provider.resources.AnionModelProvider;
import com.peak.anion.datagen.provider.resources.AnionParticleProvider;
import com.peak.anion.datagen.provider.resources.lang.AnionLangProvider;
import com.peak.anion.datagen.provider.tag.AnionItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * @author AcoYT
 * @author Chemthunder
 */
public class AnionDatagen implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Lang
        pack.addProvider(AnionLangProvider::new);

        // Resources
        pack.addProvider(AnionModelProvider::new);
        pack.addProvider(AnionParticleProvider::new);

        // Tags
        pack.addProvider(AnionItemTagProvider::new);

        // Other Data
        pack.addProvider(AnionBlockLootTableProvider::new);
        pack.addProvider(AnionRecipeProvider::new);
    }
}
