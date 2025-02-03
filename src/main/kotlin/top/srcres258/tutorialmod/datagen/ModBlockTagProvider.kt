package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.util.ModTags
import java.util.concurrent.CompletableFuture

class ModBlockTagProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(p0: RegistryWrapper.WrapperLookup?) {
        val goctb = ::getOrCreateTagBuilder

        goctb(BlockTags.PICKAXE_MINEABLE)
            .add(ModBlocks.PINK_GARNET_BLOCK)
            .add(ModBlocks.RAW_PINK_GARNET_BLOCK)
            .add(ModBlocks.PINK_GARNET_ORE)
            .add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE)
            .add(ModBlocks.MAGIC_BLOCK)

        goctb(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE)

        goctb(BlockTags.FENCES)
            .add(ModBlocks.PINK_GARNET_FENCE)
        goctb(BlockTags.FENCE_GATES)
            .add(ModBlocks.PINK_GARNET_FENCE_GATE)
        goctb(BlockTags.WALLS)
            .add(ModBlocks.PINK_GARNET_WALL)

        goctb(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.MAGIC_BLOCK)

        goctb(ModTags.Blocks.NEEDS_PINK_GARNET_TOOL)
            .add(ModBlocks.MAGIC_BLOCK)
            .addTag(BlockTags.NEEDS_IRON_TOOL)
    }
}