package top.srcres258.tutorialmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.fabricmc.fabric.api.registry.FuelRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.component.ModDataComponentTypes
import top.srcres258.tutorialmod.item.ModItemGroups
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.util.HammerUsageEvent

object TutorialMod : ModInitializer {
	const val MOD_ID = "tutorialmod"
	val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItems.registerModItems()
		ModBlocks.registerModBlocks()
		ModItemGroups.registerItemGroups()
		ModDataComponentTypes.registerDataComponentTypes()

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600)

		PlayerBlockBreakEvents.BEFORE.register(HammerUsageEvent)
	}
}