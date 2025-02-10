package top.srcres258.tutorialmod.trim

import net.minecraft.item.Item
import net.minecraft.item.trim.ArmorTrimPattern
import net.minecraft.registry.Registerable
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Util
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.ModItems

object ModTrimPatterns {
    val KAUPEN: RegistryKey<ArmorTrimPattern> = RegistryKey.of(RegistryKeys.TRIM_PATTERN,
        Identifier.of(TutorialMod.MOD_ID, "kaupen"))

    fun bootstrap(context: Registerable<ArmorTrimPattern>) {
        register(context, ModItems.KAUPEN_SMITHING_TEMPLATE, KAUPEN)
    }

    private fun register(
        context: Registerable<ArmorTrimPattern>,
        item: Item,
        key: RegistryKey<ArmorTrimPattern>
    ) {
        val trimPattern = ArmorTrimPattern(key.value, Registries.ITEM.getEntry(item),
            Text.translatable(Util.createTranslationKey("trim_pattern", key.value)), false)

        context.register(key, trimPattern)
    }
}