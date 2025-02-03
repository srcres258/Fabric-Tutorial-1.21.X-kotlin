package top.srcres258.tutorialmod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import top.srcres258.tutorialmod.util.ModTags

class MagicBlock(settings: Settings) : Block(settings) {
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hit: BlockHitResult
    ): ActionResult {
        world.playSound(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1F, 1F)
        return ActionResult.SUCCESS
    }

    override fun onSteppedOn(world: World, pos: BlockPos, state: BlockState, entity: Entity) {
        if (entity is ItemEntity) {
            if (isValidItem(entity.stack)) {
                entity.stack = ItemStack(Items.DIAMOND, entity.stack.count)
            }
        }

        super.onSteppedOn(world, pos, state, entity)
    }

    override fun appendTooltip(
        stack: ItemStack,
        context: Item.TooltipContext,
        tooltip: MutableList<Text>,
        options: TooltipType
    ) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.magic_block.tooltip"))
        super.appendTooltip(stack, context, tooltip, options)
    }
}

private fun isValidItem(stack: ItemStack) = stack.isIn(ModTags.Items.TRANSFORMABLE_ITEMS)