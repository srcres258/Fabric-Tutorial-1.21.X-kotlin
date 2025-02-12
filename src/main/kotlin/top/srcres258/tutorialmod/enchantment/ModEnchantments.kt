package top.srcres258.tutorialmod.enchantment

import net.minecraft.component.EnchantmentEffectComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.effect.EnchantmentEffectTarget
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.EnchantmentTags
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.enchantment.custom.LightningStrikerEnchantmentEffect

object ModEnchantments {
    val LIGHTNING_STRIKER: RegistryKey<Enchantment> =
        RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(TutorialMod.MOD_ID, "lightning_striker"))

    fun bootstrap(registerable: Registerable<Enchantment>) {
        val enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT)
        val items = registerable.getRegistryLookup(RegistryKeys.ITEM)

        register(registerable, LIGHTNING_STRIKER, Enchantment.builder(Enchantment.definition(
            items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
            items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
            5,
            2,
            Enchantment.leveledCost(5, 7),
            Enchantment.leveledCost(25, 9),
            2,
            AttributeModifierSlot.MAINHAND
        ))
            .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
            .addEffect(
                EnchantmentEffectComponentTypes.POST_ATTACK,
                EnchantmentEffectTarget.ATTACKER,
                EnchantmentEffectTarget.VICTIM,
                LightningStrikerEnchantmentEffect()
            ))
    }

    private fun register(
        registry: Registerable<Enchantment>,
        key: RegistryKey<Enchantment>,
        builder: Enchantment.Builder
    ) = registry.register(key, builder.build(key.value))
}