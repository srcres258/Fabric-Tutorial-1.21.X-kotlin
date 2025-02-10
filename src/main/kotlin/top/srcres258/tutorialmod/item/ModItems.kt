package top.srcres258.tutorialmod.item

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ArmorItem
import net.minecraft.item.AxeItem
import net.minecraft.item.HoeItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ShovelItem
import net.minecraft.item.SwordItem
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.custom.ChiselItem
import top.srcres258.tutorialmod.item.custom.HammerItem
import top.srcres258.tutorialmod.item.custom.ModArmorItem
import kotlin.reflect.full.primaryConstructor

object ModItems {
    val PINK_GARNET: Item = registerItem("pink_garnet", Item(Item.Settings()))
    val RAW_PINK_GARNET: Item = registerItem("raw_pink_garnet", Item(Item.Settings()))

    val CHISEL: Item = registerItem("chisel", ChiselItem(Item.Settings().maxDamage(32)))

    val CAULIFLOWER: Item = registerItem("cauliflower",
        object : Item(Settings().food(ModFoodComponents.CAULIFLOWER)) {
            override fun appendTooltip(
                stack: ItemStack,
                context: TooltipContext,
                tooltip: MutableList<Text>,
                type: TooltipType
            ) {
                tooltip.add(Text.translatable("tooltip.tutorialmod.cauliflower.tooltip"))
                super.appendTooltip(stack, context, tooltip, type)
            }
        })

    val STARLIGHT_ASHES: Item = registerItem("starlight_ashes", Item(Item.Settings()))

    val PINK_GARNET_SWORD: Item = registerItem("pink_garnet_sword",
        SwordItem(ModToolMaterials.PINK_GARNET, Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 3, -2.4F))))
    val PINK_GARNET_PICKAXE: Item = registerItem("pink_garnet_pickaxe",
        PickaxeItem(ModToolMaterials.PINK_GARNET, Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 1F, -2.8F))))
    val PINK_GARNET_SHOVEL: Item = registerItem("pink_garnet_shovel",
        ShovelItem(ModToolMaterials.PINK_GARNET, Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 1.5F, -3F))))
    val PINK_GARNET_AXE: Item = registerItem("pink_garnet_axe",
        AxeItem(ModToolMaterials.PINK_GARNET, Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 6F, -3.2F))))
    val PINK_GARNET_HOE: Item = registerItem("pink_garnet_hoe",
        HoeItem(ModToolMaterials.PINK_GARNET, Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 0F, -3F))))

    val PINK_GARNET_HAMMER: Item = registerItem("pink_garnet_hammer",
        HammerItem(ModToolMaterials.PINK_GARNET, Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 7F, -3.4F))))

    val PINK_GARNET_HELMET: Item = registerArmorItem<ModArmorItem>("pink_garnet_helmet", ArmorItem.Type.HELMET)
    val PINK_GARNET_CHESTPLATE: Item = registerArmorItem<ArmorItem>("pink_garnet_chestplate", ArmorItem.Type.CHESTPLATE)
    val PINK_GARNET_LEGGINGS: Item = registerArmorItem<ArmorItem>("pink_garnet_leggings", ArmorItem.Type.LEGGINGS)
    val PINK_GARNET_BOOTS: Item = registerArmorItem<ArmorItem>("pink_garnet_boots", ArmorItem.Type.BOOTS)

    private inline fun <reified T : ArmorItem> registerArmorItem(
        name: String,
        type: ArmorItem.Type,
        maxDamage: Int = 15
    ) = registerItem(
        name,
        T::class.constructors.toList()[0]
            .call(
                ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL,
                type,
                Item.Settings().maxDamage(type.getMaxDamage(maxDamage))
            )
    )

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