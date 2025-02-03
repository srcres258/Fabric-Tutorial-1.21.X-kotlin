package top.srcres258.tutorialmod.item

import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

object ModArmorMaterials {
    val PINK_GARNET_ARMOR_MATERIAL: RegistryEntry<ArmorMaterial> = registerArmorMaterial("pink_garnet") {
        ArmorMaterial(
            mapOf(
                Pair(ArmorItem.Type.BOOTS, 2),
                Pair(ArmorItem.Type.LEGGINGS, 4),
                Pair(ArmorItem.Type.CHESTPLATE, 6),
                Pair(ArmorItem.Type.HELMET, 8),
                Pair(ArmorItem.Type.BODY, 10)
            ),
            20,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            { Ingredient.ofItems(ModItems.PINK_GARNET) },
            listOf(ArmorMaterial.Layer(
                Identifier.of(TutorialMod.MOD_ID, "pink_garnet")
            )),
            0F,
            0F
        )
    }

    private fun registerArmorMaterial(name: String, material: () -> ArmorMaterial) =
        Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(TutorialMod.MOD_ID, name), material())
}