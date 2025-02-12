package top.srcres258.tutorialmod.effect

import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

object ModEffects {
    val SLIMEY: RegistryEntry<StatusEffect> = registerStatusEffect(
        "slimey",
        SlimeyEffect(StatusEffectCategory.NEUTRAL, 0x36EBAB)
            .addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                Identifier.of(TutorialMod.MOD_ID, "slimey"),
                -0.25,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            )
    )

    private fun registerStatusEffect(name: String, statusEffect: StatusEffect) =
        Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(TutorialMod.MOD_ID, name), statusEffect)

    fun registerEffects() {
        TutorialMod.LOGGER.info("Registering Mod Effects for ${TutorialMod.MOD_ID}")
    }
}