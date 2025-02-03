package top.srcres258.tutorialmod.item

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.custom.ChiselItem

object ModItems {
    val PINK_GARNET: Item = registerItem("pink_garnet", Item(Item.Settings()))
    val RAW_PINK_GARNET: Item = registerItem("raw_pink_garnet", Item(Item.Settings()))

    val CHISEL: Item = registerItem("chisel", ChiselItem(Item.Settings().maxDamage(32)))

    private fun registerItem(name: String, item: Item) =
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item)

    fun registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for ${TutorialMod.MOD_ID}")

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register { entries ->
                entries.add(PINK_GARNET)
                entries.add(RAW_PINK_GARNET)
            }
    }
}