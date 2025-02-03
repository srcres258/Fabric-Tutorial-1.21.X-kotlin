package top.srcres258.tutorialmod.util

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import top.srcres258.tutorialmod.item.custom.HammerItem

// Done with the help of https://github.com/CoFH/CoFHCore/blob/c23d117dcd3b3b3408a138716b15507f709494cd/src/main/java/cofh/core/event/AreaEffectEvents.java
// And converted to Kotlin language by src_resources.
object HammerUsageEvent : PlayerBlockBreakEvents.Before {
    private val HARVESTED_BLOCKS = mutableSetOf<BlockPos>()

    override fun beforeBlockBreak(
        world: World,
        player: PlayerEntity,
        pos: BlockPos,
        state: BlockState,
        blockEntity: BlockEntity?
    ): Boolean {
        val stack = player.mainHandStack
        val item = stack.item

        if (item is HammerItem && player is ServerPlayerEntity) {
            if (pos in HARVESTED_BLOCKS) {
                return true
            }

            for (position in HammerItem.getBlocksToBeDestroyed(1, pos, player)) {
                if (pos == position || !item.isCorrectForDrops(stack, world.getBlockState(position))) {
                    continue
                }

                HARVESTED_BLOCKS.add(position)
                player.interactionManager.tryBreakBlock(position)
                HARVESTED_BLOCKS.remove(position)
            }
        }

        return true
    }
}