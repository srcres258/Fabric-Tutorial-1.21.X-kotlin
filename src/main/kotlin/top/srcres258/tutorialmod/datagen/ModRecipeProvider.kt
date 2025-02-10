package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems
import java.util.concurrent.CompletableFuture

private val PINK_GARNET_SMELTABLES = listOf<ItemConvertible>(
    ModItems.RAW_PINK_GARNET,
    ModBlocks.PINK_GARNET_ORE,
    ModBlocks.PINK_GARNET_DEEPSLATE_ORE
)

class ModRecipeProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricRecipeProvider(output, registriesFuture) {
    override fun generate(exporter: RecipeExporter) {
        offerSmelting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET,
            0.25F, 200, "pink_garnet")
        offerBlasting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET,
            0.25F, 100, "pink_garnet")

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET,
            RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_BLOCK)

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK)
            .pattern("RRR")
            .pattern("RRR")
            .pattern("RRR")
            .input('R', ModItems.RAW_PINK_GARNET)
            .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK),
                RecipeProvider.conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 9)
            .input(ModBlocks.RAW_PINK_GARNET_BLOCK)
            .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK),
                RecipeProvider.conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 32)
            .input(ModBlocks.MAGIC_BLOCK)
            .criterion(hasItem(ModBlocks.MAGIC_BLOCK), conditionsFromItem(ModBlocks.MAGIC_BLOCK))
            .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "raw_pink_garnet_from_magic_block"))

        offerSmithingTrimRecipe(exporter, ModItems.KAUPEN_SMITHING_TEMPLATE, Identifier.of(TutorialMod.MOD_ID, "kaupen"))
    }
}