package com.peak.anion.datagen;

import com.peak.anion.datagen.provider.AnionBlockLootTableProvider;
import com.peak.anion.datagen.provider.resources.AnionModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AnionDatagen implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(AnionModelProvider::new);

        pack.addProvider(AnionBlockLootTableProvider::new);
    }
}