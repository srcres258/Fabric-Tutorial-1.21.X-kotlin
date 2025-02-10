package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.data.client.TextureMap
import net.minecraft.data.client.TexturedModel
import net.minecraft.data.client.VariantsBlockStateSupplier
import net.minecraft.item.ArmorItem
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.PinkGarnetLampBlock
import top.srcres258.tutorialmod.item.ModItems

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
        generator.registerCubeAllModelTexturePool(ModBlocks.PINK_GARNET_BLOCK).run {
            stairs(ModBlocks.PINK_GARNET_STAIRS)
            slab(ModBlocks.PINK_GARNET_SLAB)

            button(ModBlocks.PINK_GARNET_BUTTON)
            pressurePlate(ModBlocks.PINK_GARNET_PRESSURE_PLATE)

            fence(ModBlocks.PINK_GARNET_FENCE)
            fenceGate(ModBlocks.PINK_GARNET_FENCE_GATE)
            wall(ModBlocks.PINK_GARNET_WALL)
        }
        generator::registerSimpleCubeAll.let { rsca ->
            rsca(ModBlocks.RAW_PINK_GARNET_BLOCK)
            rsca(ModBlocks.PINK_GARNET_ORE)
            rsca(ModBlocks.PINK_GARNET_DEEPSLATE_ORE)

            rsca(ModBlocks.MAGIC_BLOCK)
        }

        generator.registerDoor(ModBlocks.PINK_GARNET_DOOR)
        generator.registerTrapdoor(ModBlocks.PINK_GARNET_TRAPDOOR)

        Pair(
            TexturedModel.CUBE_ALL.upload(ModBlocks.PINK_GARNET_LAMP, generator.modelCollector),
            generator.createSubModel(ModBlocks.PINK_GARNET_LAMP, "_on", Models.CUBE_ALL, TextureMap::all)
        ).let { (lampOffId, lampOnId) ->
            generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.PINK_GARNET_LAMP)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(PinkGarnetLampBlock.CLICKED,
                    lampOnId, lampOffId)))
        }
    }

    override fun generateItemModels(generator: ItemModelGenerator) {
        generator.register(ModItems.PINK_GARNET, Models.GENERATED)
        generator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED)

        generator.register(ModItems.CAULIFLOWER, Models.GENERATED)
//        generator.register(ModItems.CHISEL, Models.GENERATED)
        generator.register(ModItems.STARLIGHT_ASHES, Models.GENERATED)

        generator.register(ModItems.PINK_GARNET_SWORD, Models.HANDHELD)
        generator.register(ModItems.PINK_GARNET_PICKAXE, Models.HANDHELD)
        generator.register(ModItems.PINK_GARNET_SHOVEL, Models.HANDHELD)
        generator.register(ModItems.PINK_GARNET_AXE, Models.HANDHELD)
        generator.register(ModItems.PINK_GARNET_HOE, Models.HANDHELD)

        generator.register(ModItems.PINK_GARNET_HAMMER, Models.HANDHELD)

        generator.registerArmor(ModItems.PINK_GARNET_HELMET as ArmorItem)
        generator.registerArmor(ModItems.PINK_GARNET_CHESTPLATE as ArmorItem)
        generator.registerArmor(ModItems.PINK_GARNET_LEGGINGS as ArmorItem)
        generator.registerArmor(ModItems.PINK_GARNET_BOOTS as ArmorItem)

        generator.register(ModItems.PINK_GARNET_HORSE_ARMOR, Models.GENERATED)
        generator.register(ModItems.KAUPEN_SMITHING_TEMPLATE, Models.GENERATED)
    }
}