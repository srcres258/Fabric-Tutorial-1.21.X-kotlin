package top.srcres258.tutorialmod.item.custom

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import top.srcres258.tutorialmod.block.ModBlocks

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

                    world.playSound(null, context.blockPos, SoundEvents.BLOCK_GRINDSTONE_USE,
                        SoundCategory.BLOCKS)
                }
            }
        }

        return ActionResult.SUCCESS
    }
}