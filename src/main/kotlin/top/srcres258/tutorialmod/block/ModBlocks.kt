package top.srcres258.tutorialmod.block

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.*
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.custom.CauliflowerCropBlock
import top.srcres258.tutorialmod.block.custom.HoneyBerryBushBlock
import top.srcres258.tutorialmod.block.custom.MagicBlock
import top.srcres258.tutorialmod.block.custom.PinkGarnetLampBlock
import top.srcres258.tutorialmod.sound.ModSounds

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
            .requiresTool()
            .sounds(ModSounds.MAGIC_BLOCK_SOUNDS)))

    val PINK_GARNET_STAIRS: Block = registerBlock("pink_garnet_stairs",
        StairsBlock(
            PINK_GARNET_BLOCK.defaultState,
            AbstractBlock.Settings.create()
                .strength(2F)
                .requiresTool()
                .noCollision()))
    val PINK_GARNET_SLAB: Block = registerBlock("pink_garnet_slab",
        SlabBlock(AbstractBlock.Settings.create()
            .strength(2F)
            .requiresTool()))

    val PINK_GARNET_BUTTON: Block = registerBlock("pink_garnet_button",
        ButtonBlock(BlockSetType.IRON, 2,
            AbstractBlock.Settings.create()
                .strength(2F)
                .requiresTool()
                .noCollision()))
    val PINK_GARNET_PRESSURE_PLATE: Block = registerBlock("pink_garnet_pressure_plate",
        PressurePlateBlock(BlockSetType.IRON,
            AbstractBlock.Settings.create()
                .strength(2F)
                .requiresTool()))

    val PINK_GARNET_FENCE: Block = registerBlock("pink_garnet_fence",
        FenceBlock(AbstractBlock.Settings.create()
            .strength(2F)
            .requiresTool()))
    val PINK_GARNET_FENCE_GATE: Block = registerBlock("pink_garnet_fence_gate",
        FenceGateBlock(WoodType.ACACIA,
            AbstractBlock.Settings.create()
                .strength(2F)
                .requiresTool()))
    val PINK_GARNET_WALL: Block = registerBlock("pink_garnet_wall",
        WallBlock(AbstractBlock.Settings.create()
            .strength(2F)
            .requiresTool()))

    val PINK_GARNET_DOOR: Block = registerBlock("pink_garnet_door",
        DoorBlock(BlockSetType.IRON,
            AbstractBlock.Settings.create()
                .strength(2F)
                .requiresTool()
                .nonOpaque()))
    val PINK_GARNET_TRAPDOOR: Block = registerBlock("pink_garnet_trapdoor",
        TrapdoorBlock(BlockSetType.IRON,
            AbstractBlock.Settings.create()
                .strength(2F)
                .requiresTool()
                .nonOpaque()))

    val PINK_GARNET_LAMP: Block = registerBlock("pink_garnet_lamp",
        PinkGarnetLampBlock(AbstractBlock.Settings.create()
            .strength(1F)
            .requiresTool()
            .luminance { state -> if (state.get(PinkGarnetLampBlock.CLICKED)) 15 else 0 }))

    val CAULIFLOWER_CROP: Block = registerBlockWithoutBlockItem("cauliflower_crop",
        CauliflowerCropBlock(AbstractBlock.Settings.create()
            .noCollision()
            .ticksRandomly()
            .breakInstantly()
            .sounds(BlockSoundGroup.CROP)
            .pistonBehavior(PistonBehavior.DESTROY)
            .mapColor(MapColor.DARK_GREEN)))

    val HONEY_BERRY_BUSH: Block = registerBlockWithoutBlockItem("honey_berry_bush",
        HoneyBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)))

    private fun registerBlockWithoutBlockItem(name: String, block: Block) =
        Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block)

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