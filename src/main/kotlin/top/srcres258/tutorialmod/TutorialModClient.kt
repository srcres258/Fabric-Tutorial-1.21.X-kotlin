package top.srcres258.tutorialmod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.util.ModModelPredicates

object TutorialModClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.run {
            putBlock(ModBlocks.PINK_GARNET_DOOR, RenderLayer.getCutout())
            putBlock(ModBlocks.PINK_GARNET_TRAPDOOR, RenderLayer.getCutout())

            ModModelPredicates.registerModelPredicates()
        }
    }
}