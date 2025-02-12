package top.srcres258.tutorialmod.enchantment

import com.mojang.serialization.MapCodec
import net.minecraft.enchantment.effect.EnchantmentEntityEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.enchantment.custom.LightningStrikerEnchantmentEffect

object ModEnchantmentEffects {
    val LIGHTNING_STRIKER: MapCodec<out EnchantmentEntityEffect> =
        registerEntityEffect("lightning_striker", LightningStrikerEnchantmentEffect.CODEC)

    private fun registerEntityEffect(
        name: String,
        codec: MapCodec<out EnchantmentEntityEffect>
    ): MapCodec<out EnchantmentEntityEffect> =
        Registry.register(
            Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
            Identifier.of(TutorialMod.MOD_ID, name),
            codec
        )

    fun registerEnchantmentEffects() {
        TutorialMod.LOGGER.info("Registering Mod Enchantment Effects for ${TutorialMod.MOD_ID}")
    }
}