package top.srcres258.tutorialmod.trim

import net.minecraft.item.Item
import net.minecraft.item.trim.ArmorTrimMaterial
import net.minecraft.registry.Registerable
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Identifier
import net.minecraft.util.Util
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.ModItems

object ModTrimMaterials {
    val PINK_GARNET: RegistryKey<ArmorTrimMaterial> = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
        Identifier.of(TutorialMod.MOD_ID, "pink_garnet"))

    fun bootstrap(registerable: Registerable<ArmorTrimMaterial>) {
        register(registerable, PINK_GARNET, Registries.ITEM.getEntry(ModItems.PINK_GARNET),
            Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0F)
    }

    private fun register(
        registerable: Registerable<ArmorTrimMaterial>,
        armorTrimKey: RegistryKey<ArmorTrimMaterial>,
        item: RegistryEntry<Item>,
        style: Style,
        itemModelIndex: Float
    ) {
        val trimMaterial = ArmorTrimMaterial(armorTrimKey.value.path, item, itemModelIndex, mapOf(),
            Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.value))
                .fillStyle(style))

        registerable.register(armorTrimKey, trimMaterial)
    }
}