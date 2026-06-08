package com.peak.anion.datagen.provider;

import com.peak.anion.core.index.AnionBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author AcoYT
 */
public class AnionBlockLootTableProvider extends FabricBlockLootTableProvider {
    public AnionBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generate() {
        this.addDrop(AnionBlocks.CHARGED_IRON_BLOCK);
        this.addDrop(AnionBlocks.ANION_GENERATOR);
    }
}
