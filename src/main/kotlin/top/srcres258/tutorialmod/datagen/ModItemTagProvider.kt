package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.util.ModTags
import java.util.concurrent.CompletableFuture

class ModItemTagProvider(
    output: FabricDataOutput,
    completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider.ItemTagProvider(output, completableFuture) {
    override fun configure(p0: RegistryWrapper.WrapperLookup?) {
        val goctb = ::getOrCreateTagBuilder

        goctb(ModTags.Items.TRANSFORMABLE_ITEMS)
            .add(ModItems.PINK_GARNET)
            .add(ModItems.RAW_PINK_GARNET)
            .add(Items.COAL)
            .add(Items.STICK)
            .add(Items.APPLE)
    }
}