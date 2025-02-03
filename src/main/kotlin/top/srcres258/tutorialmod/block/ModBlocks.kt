package top.srcres258.tutorialmod.block

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.ExperienceDroppingBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.custom.MagicBlock

object ModBlocks {
    val PINK_GARNET_BLOCK: Block = registerBlock("pink_garnet_block",
        Block(AbstractBlock.Settings.create()
            .strength(4F)
            .requiresTool()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)))
    val RAW_PINK_GARNET_BLOCK: Block = registerBlock("raw_pink_garnet_block",
        Block(AbstractBlock.Settings.create()
            .strength(3F)
            .requiresTool()))

    val PINK_GARNET_ORE: Block = registerBlock("pink_garnet_ore",
        ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
            AbstractBlock.Settings.create()
                .strength(3F)
                .requiresTool()))
    val PINK_GARNET_DEEPSLATE_ORE = registerBlock("pink_garnet_deepslate_ore",
        ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
            AbstractBlock.Settings.create()
                .strength(4F)
                .requiresTool()
                .sounds(BlockSoundGroup.DEEPSLATE)))

    val MAGIC_BLOCK: Block = registerBlock("magic_block",
        MagicBlock(AbstractBlock.Settings.create()
            .strength(1F)
            .requiresTool()))

    private fun registerBlock(name: String, block: Block) =
        Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name),
            block.also { registerBlockItem(name, it) })

    private fun registerBlockItem(name: String, block: Block) =
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
            BlockItem(block, Item.Settings()))

    fun registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for ${TutorialMod.MOD_ID}")

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register { entries ->
                entries.add(PINK_GARNET_BLOCK)
                entries.add(RAW_PINK_GARNET_BLOCK)
            }
    }
}