package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
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

        goctb(ItemTags.SWORDS)
            .add(ModItems.PINK_GARNET_SWORD)
        goctb(ItemTags.PICKAXES)
            .add(ModItems.PINK_GARNET_PICKAXE)
        goctb(ItemTags.SHOVELS)
            .add(ModItems.PINK_GARNET_SHOVEL)
        goctb(ItemTags.AXES)
            .add(ModItems.PINK_GARNET_AXE)
        goctb(ItemTags.HOES)
            .add(ModItems.PINK_GARNET_HOE)

        goctb(ItemTags.TRIMMABLE_ARMOR)
            .add(ModItems.PINK_GARNET_HELMET)
            .add(ModItems.PINK_GARNET_CHESTPLATE)
            .add(ModItems.PINK_GARNET_LEGGINGS)
            .add(ModItems.PINK_GARNET_BOOTS)

        goctb(ItemTags.TRIM_MATERIALS)
            .add(ModItems.PINK_GARNET)

        goctb(ItemTags.TRIM_TEMPLATES)
            .add(ModItems.KAUPEN_SMITHING_TEMPLATE)
    }
}