package com.peak.anion.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class AnionRecipeProvider extends FabricRecipeProvider {
    public AnionRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public void generate(RecipeExporter exporter) {
//        ShapedRecipeJsonBuilder.create(
//                        RecipeCategory.REDSTONE,
//                        AnionBlocks.ATTRACTOR
//                )
//                .pattern("csc")
//                .pattern("shs")
//                .pattern("lbl")
//                .input('c', AnionItems.CHARGED_IRON)
//                .input('s', Blocks.SPRUCE_PLANKS)
//                .input('l', Blocks.SPRUCE_SLAB)
//                .input('h', Blocks.HOPPER)
//                .input('b', Items.ENDER_PEARL)
//                .criterion("unknown", insert)
//                .offerTo(exporter);

        ///  IN DEV
        // Replace Insert with a criterion unlocked upon obtaining Charged Iron or sumth
    }
}
