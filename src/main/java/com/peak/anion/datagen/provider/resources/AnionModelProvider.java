package com.peak.anion.datagen.provider.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

/**
 * @author AcoYT
 */
public class AnionModelProvider extends FabricModelProvider {
    public AnionModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll();
    }

    public void generateItemModels(ItemModelGenerator generator) {
        //
    }
}