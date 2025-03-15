package top.srcres258.tutorialmod.world

import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import top.srcres258.tutorialmod.TutorialMod

object ModPlacedFeatures {
    fun bootstrap(context: Registerable<PlacedFeature>) {}

    fun registerKey(name: String): RegistryKey<PlacedFeature> =
        RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(TutorialMod.MOD_ID, name))

    private fun register(
        context: Registerable<PlacedFeature>,
        key: RegistryKey<PlacedFeature>,
        config: RegistryEntry<ConfiguredFeature<*, *>>,
        modifiers: List<PlacementModifier>
    ) {
        context.register(key, PlacedFeature(config, modifiers.toList()))
    }

    private fun <FC : FeatureConfig, F : Feature<FC>> register(
        context: Registerable<PlacedFeature>,
        key: RegistryKey<PlacedFeature>,
        config: RegistryEntry<ConfiguredFeature<*, *>>,
        vararg modifiers: PlacementModifier
    ) {
        register(context, key, config, modifiers.toList())
    }
}