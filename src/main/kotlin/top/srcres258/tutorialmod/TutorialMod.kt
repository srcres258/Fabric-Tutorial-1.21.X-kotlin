package top.srcres258.tutorialmod

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object TutorialMod : ModInitializer {
	const val MOD_ID = "tutorialmod"
    private val LOGGER = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!")
	}
}