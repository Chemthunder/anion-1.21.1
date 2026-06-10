package com.peak.anion.datagen.provider;

import com.peak.anion.core.index.AnionBlocks;
import com.peak.anion.core.index.AnionItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnionBlocks.ANION_GENERATOR)
                .pattern("DID")
                .pattern("S#S")
                .pattern("SCS")
                .input('D', Blocks.POLISHED_DEEPSLATE)
                .input('I', Items.IRON_INGOT)
                .input('S', Blocks.SPRUCE_PLANKS)
                .input('#', AnionItems.CHARGED_IRON_INGOT)
                .input('C', Items.COPPER_BLOCK)
                .criterion("has_charged_iron_ingot", conditionsFromItem(AnionItems.CHARGED_IRON_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnionBlocks.ATTRACTOR)
                .pattern("CSC")
                .pattern("SHS")
                .pattern("LBL")
                .input('C', AnionItems.CHARGED_IRON_INGOT)
                .input('S', Blocks.SPRUCE_PLANKS)
                .input('L', Blocks.SPRUCE_SLAB)
                .input('H', Blocks.HOPPER)
                .input('B', Items.ENDER_PEARL)
                .criterion("has_charged_iron_ingot", conditionsFromItem(AnionItems.CHARGED_IRON_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnionItems.BATTERY)
                .pattern(" /c")
                .pattern("/c/")
                .pattern("// ")
                .input('/', Items.COPPER_INGOT)
                .input('c', AnionItems.CHARGED_IRON_INGOT)
                .criterion("has_charged_iron_ingot", conditionsFromItem(AnionItems.CHARGED_IRON_INGOT))
                .offerTo(exporter);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, AnionItems.CHARGED_IRON_INGOT, RecipeCategory.BUILDING_BLOCKS, AnionBlocks.CHARGED_IRON_BLOCK);
    }
}
