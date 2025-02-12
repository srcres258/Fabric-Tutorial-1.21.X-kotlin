package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Item
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.CauliflowerCropBlock
import top.srcres258.tutorialmod.block.custom.HoneyBerryBushBlock
import top.srcres258.tutorialmod.item.ModItems
import java.util.concurrent.CompletableFuture

class ModLootTableProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricBlockLootTableProvider(dataOutput, registryLookup) {
    override fun generate() {
        val impl = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT)

        addDrop(ModBlocks.PINK_GARNET_BLOCK)
        addDrop(ModBlocks.RAW_PINK_GARNET_BLOCK)
        addDrop(ModBlocks.MAGIC_BLOCK)

        addDrop(ModBlocks.PINK_GARNET_ORE, oreDrops(ModBlocks.PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET))
        addDrop(ModBlocks.PINK_GARNET_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.PINK_GARNET_DEEPSLATE_ORE,
            ModItems.RAW_PINK_GARNET, 3F, 7F))

        addDrop(ModBlocks.PINK_GARNET_STAIRS)
        addDrop(ModBlocks.PINK_GARNET_SLAB, slabDrops(ModBlocks.PINK_GARNET_SLAB))

        addDrop(ModBlocks.PINK_GARNET_BUTTON)
        addDrop(ModBlocks.PINK_GARNET_PRESSURE_PLATE)

        addDrop(ModBlocks.PINK_GARNET_WALL)
        addDrop(ModBlocks.PINK_GARNET_FENCE)
        addDrop(ModBlocks.PINK_GARNET_FENCE_GATE)

        addDrop(ModBlocks.PINK_GARNET_DOOR, doorDrops(ModBlocks.PINK_GARNET_DOOR))
        addDrop(ModBlocks.PINK_GARNET_TRAPDOOR)

        addDrop(
            ModBlocks.CAULIFLOWER_CROP,
            cropDrops(
                ModBlocks.CAULIFLOWER_CROP,
                ModItems.CAULIFLOWER,
                ModItems.CAULIFLOWER_SEEDS,
                BlockStatePropertyLootCondition.builder(ModBlocks.CAULIFLOWER_CROP)
                    .properties(StatePredicate.Builder.create()
                        .exactMatch(CauliflowerCropBlock.AGE, CauliflowerCropBlock.MAX_AGE))
            )
        )

        addDrop(ModBlocks.HONEY_BERRY_BUSH) { block ->
            applyExplosionDecay(
                block,
                LootTable.builder()
                    .pool(LootPool.builder()
                        .conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.HONEY_BERRY_BUSH)
                            .properties(StatePredicate.Builder.create()
                                .exactMatch(HoneyBerryBushBlock.AGE, 3)))
                        .with(ItemEntry.builder(ModItems.HONEY_BERRIES))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))
                    .pool(LootPool.builder()
                        .conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.HONEY_BERRY_BUSH)
                            .properties(StatePredicate.Builder.create()
                                .exactMatch(HoneyBerryBushBlock.AGE, 2)))
                        .with(ItemEntry.builder(ModItems.HONEY_BERRIES))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))
            )
        }
    }

    private fun multipleOreDrops(drop: Block, item: Item, minDrops: Float, maxDrops: Float) =
        registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).let { impl ->
            dropsWithSilkTouch(drop,
                applyExplosionDecay(drop, ItemEntry.builder(item)
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops)))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
        }
}