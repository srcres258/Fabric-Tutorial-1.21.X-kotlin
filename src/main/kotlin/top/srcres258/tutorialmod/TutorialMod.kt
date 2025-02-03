package top.srcres258.tutorialmod

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.srcres258.tutorialmod.item.ModItems

object TutorialMod : ModInitializer {
	const val MOD_ID = "tutorialmod"
	val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItems.registerModItems()
	}
}