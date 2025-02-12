package top.srcres258.tutorialmod.potion

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.effect.ModEffects

object ModPotions {
    val SLIMEY_POTION: RegistryEntry<Potion> = registerPotion("slimey_potion",
        Potion(StatusEffectInstance(ModEffects.SLIMEY, 1200, 0))
    )

    private fun registerPotion(name: String, potion: Potion) =
        Registry.registerReference(Registries.POTION, Identifier.of(TutorialMod.MOD_ID, name), potion)

    fun registerPotions() {
        TutorialMod.LOGGER.info("Registering Mod Potions for ${TutorialMod.MOD_ID}")
    }
}