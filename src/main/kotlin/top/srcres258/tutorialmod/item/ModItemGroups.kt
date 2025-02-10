package top.srcres258.tutorialmod.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModItemGroups {
    val PINK_GARNET_ITEMS_GROUP: ItemGroup = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(TutorialMod.MOD_ID, "pink_garnet_items"),
        FabricItemGroup.builder()
            .icon { ItemStack(ModItems.PINK_GARNET) }
            .displayName(Text.translatable("itemgroup.tutorialmod.pink_garnet_items"))
            .entries { displayContext, entries ->
                entries.add(ModItems.PINK_GARNET)
                entries.add(ModItems.RAW_PINK_GARNET)

                entries.add(ModItems.CHISEL)

                entries.add(ModItems.CAULIFLOWER)

                entries.add(ModItems.STARLIGHT_ASHES)

                entries.add(ModItems.PINK_GARNET_SWORD)
                entries.add(ModItems.PINK_GARNET_PICKAXE)
                entries.add(ModItems.PINK_GARNET_SHOVEL)
                entries.add(ModItems.PINK_GARNET_AXE)
                entries.add(ModItems.PINK_GARNET_HOE)

                entries.add(ModItems.PINK_GARNET_HAMMER)

                entries.add(ModItems.PINK_GARNET_HELMET)
                entries.add(ModItems.PINK_GARNET_CHESTPLATE)
                entries.add(ModItems.PINK_GARNET_LEGGINGS)
                entries.add(ModItems.PINK_GARNET_BOOTS)

                entries.add(ModItems.PINK_GARNET_HORSE_ARMOR)
            }
            .build())
    val PINK_GARNET_BLOCKS_GROUP: ItemGroup = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(TutorialMod.MOD_ID, "pink_garnet_blocks"),
        FabricItemGroup.builder()
            .icon { ItemStack(ModBlocks.PINK_GARNET_BLOCK) }
            .displayName(Text.translatable("itemgroup.tutorialmod.pink_garnet_blocks"))
            .entries { displayContext, entries ->
                entries.add(ModBlocks.PINK_GARNET_BLOCK)
                entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK)

                entries.add(ModBlocks.PINK_GARNET_ORE)
                entries.add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE)

                entries.add(ModBlocks.MAGIC_BLOCK)

                entries.add(ModBlocks.PINK_GARNET_STAIRS)
                entries.add(ModBlocks.PINK_GARNET_SLAB)

                entries.add(ModBlocks.PINK_GARNET_BUTTON)
                entries.add(ModBlocks.PINK_GARNET_PRESSURE_PLATE)

                entries.add(ModBlocks.PINK_GARNET_FENCE)
                entries.add(ModBlocks.PINK_GARNET_FENCE_GATE)
                entries.add(ModBlocks.PINK_GARNET_WALL)

                entries.add(ModBlocks.PINK_GARNET_DOOR)
                entries.add(ModBlocks.PINK_GARNET_TRAPDOOR)

                entries.add(ModBlocks.PINK_GARNET_LAMP)
            }
            .build())

    fun registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for ${TutorialMod.MOD_ID}")
    }
}