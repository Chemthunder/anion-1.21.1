package com.peak.anion.datagen.provider.resources;

import com.peak.anion.core.Anion;
import com.peak.anion.core.block.GeneratorBlock;
import com.peak.anion.core.index.AnionBlocks;
import com.peak.anion.core.index.AnionItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AcoYT
 */
public class AnionModelProvider extends FabricModelProvider {
    public static final List<Identifier> BATTERY_MODELS = new ArrayList<>();

    public AnionModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(AnionBlocks.CHARGED_IRON_BLOCK);
        createGeneratorModel(generator, AnionBlocks.ANION_GENERATOR);
    }

    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(AnionItems.CHARGED_IRON, Models.GENERATED);

        for (int i = 0; i < 9; i++) {
            generator.register(AnionItems.BATTERY, "_" + i, Models.GENERATED);
            BATTERY_MODELS.add(Anion.id("battery_" + i));
        }
    }

    private static void createGeneratorModel(BlockStateModelGenerator generator, Block block) {
        Identifier blockId = ModelIds.getBlockModelId(block);
        Identifier poweredModel = Models.CUBE_BOTTOM_TOP.upload(blockId.withSuffixedPath("_powered"), TextureMap
                .topBottom(blockId.withSuffixedPath("_powered"), ModelIds.getBlockModelId(Blocks.SPRUCE_PLANKS))
                .put(TextureKey.SIDE, blockId.withSuffixedPath("_side")), generator.modelCollector);

        Identifier regularModel = Models.CUBE_BOTTOM_TOP.upload(blockId, TextureMap
                .topBottom(blockId, ModelIds.getBlockModelId(Blocks.SPRUCE_PLANKS))
                .put(TextureKey.SIDE, blockId.withSuffixedPath("_side")), generator.modelCollector);

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AnionBlocks.ANION_GENERATOR)
                .coordinate(BlockStateVariantMap.create(GeneratorBlock.POWERED)
                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, poweredModel))
                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, regularModel))
                ));
    }
}