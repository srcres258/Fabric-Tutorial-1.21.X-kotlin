package top.srcres258.tutorialmod

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys
import top.srcres258.tutorialmod.datagen.*
import top.srcres258.tutorialmod.enchantment.ModEnchantments
import top.srcres258.tutorialmod.trim.ModTrimMaterials
import top.srcres258.tutorialmod.trim.ModTrimPatterns

object TutorialModDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		fabricDataGenerator.createPack().let { pack ->
			pack.addProvider(::ModBlockTagProvider)
			pack.addProvider(::ModItemTagProvider)
			pack.addProvider(::ModLootTableProvider)
			pack.addProvider(::ModModelProvider)
			pack.addProvider(::ModRecipeProvider)
			pack.addProvider(::ModRegistryDataGenerator)
		}
	}

	override fun buildRegistry(builder: RegistryBuilder) {
		builder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
		builder.addRegistry(RegistryKeys.TRIM_PATTERN, ModTrimPatterns::bootstrap)
		builder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantments::bootstrap)
	}
}