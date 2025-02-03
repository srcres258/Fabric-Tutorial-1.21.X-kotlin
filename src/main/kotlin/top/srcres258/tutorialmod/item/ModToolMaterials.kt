package top.srcres258.tutorialmod.item

import net.minecraft.block.Block
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.TagKey
import top.srcres258.tutorialmod.util.ModTags

enum class ModToolMaterials(
    private val inverseTag: TagKey<Block>,
    private val itemDurability: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int,
    private val repairIngredient: Lazy<Ingredient>
) : ToolMaterial {
    PINK_GARNET(ModTags.Blocks.INCORRECT_FOR_PINK_GARNET_TOOL,
        1200, 5F, 4F, 22, lazy { Ingredient.ofItems(ModItems.PINK_GARNET) });

    override fun getDurability() = itemDurability

    override fun getMiningSpeedMultiplier() = miningSpeed

    override fun getAttackDamage() = attackDamage

    override fun getInverseTag() = inverseTag

    override fun getEnchantability() = enchantability

    override fun getRepairIngredient(): Ingredient = repairIngredient.value
}