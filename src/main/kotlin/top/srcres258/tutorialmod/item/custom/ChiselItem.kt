package top.srcres258.tutorialmod.item.custom

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.client.gui.screen.Screen
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.component.ModDataComponentTypes
import top.srcres258.tutorialmod.sound.ModSounds

private val CHISEL_MAP = mapOf<Block, Block>(
    Pair(Blocks.STONE, Blocks.STONE_BRICKS),
    Pair(Blocks.END_STONE, Blocks.END_STONE_BRICKS),
    Pair(Blocks.OAK_LOG, ModBlocks.PINK_GARNET_BLOCK),
    Pair(Blocks.GOLD_BLOCK, Blocks.NETHERITE_BLOCK)
)

class ChiselItem(settings: Settings) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val clickedBlock = world.getBlockState(context.blockPos).block

        CHISEL_MAP[clickedBlock]?.let { block ->
            if (!world.isClient) {
                context.player?.let { player ->
                    world.setBlockState(context.blockPos, block.defaultState)

                    context.stack.damage(1, world as ServerWorld, player as ServerPlayerEntity)
                    { item -> player.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND) }

                    world.playSound(null, context.blockPos, ModSounds.CHISEL_USE, SoundCategory.BLOCKS)

                    context.stack.set(ModDataComponentTypes.COORDINATES, context.blockPos)
                }
            }
        }

        return ActionResult.SUCCESS
    }

    override fun appendTooltip(
        stack: ItemStack,
        context: TooltipContext,
        tooltip: MutableList<Text>,
        type: TooltipType
    ) {
        tooltip.add(Text.translatable(if (Screen.hasShiftDown()) "tooltip.tutorialmod.chisel.shift_down" else
            "tooltip.tutorialmod.chisel"))

        stack.get(ModDataComponentTypes.COORDINATES)?.let { coords ->
            tooltip.add(Text.literal("Last Block Changed at $coords"))
        }

        super.appendTooltip(stack, context, tooltip, type)
    }
}