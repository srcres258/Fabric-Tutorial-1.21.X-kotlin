package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
        generator::registerSimpleCubeAll.let { rsca ->
            rsca(ModBlocks.PINK_GARNET_BLOCK)
            rsca(ModBlocks.RAW_PINK_GARNET_BLOCK)
            rsca(ModBlocks.PINK_GARNET_ORE)
            rsca(ModBlocks.PINK_GARNET_DEEPSLATE_ORE)

            rsca(ModBlocks.MAGIC_BLOCK)
        }
    }

    override fun generateItemModels(generator: ItemModelGenerator) {
        generator.register(ModItems.PINK_GARNET, Models.GENERATED)
        generator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED)

        generator.register(ModItems.CAULIFLOWER, Models.GENERATED)
        generator.register(ModItems.CHISEL, Models.GENERATED)
        generator.register(ModItems.STARLIGHT_ASHES, Models.GENERATED)
    }
}