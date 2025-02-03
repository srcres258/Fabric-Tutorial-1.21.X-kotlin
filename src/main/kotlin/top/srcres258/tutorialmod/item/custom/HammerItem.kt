package top.srcres258.tutorialmod.item.custom

import net.minecraft.item.MiningToolItem
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

class HammerItem(
    material: ToolMaterial,
    settings: Settings
) : MiningToolItem(material, BlockTags.PICKAXE_MINEABLE, settings) {
    companion object {
        fun getBlocksToBeDestroyed(
            range: Int,
            initialBlockPos: BlockPos,
            player: ServerPlayerEntity
        ): List<BlockPos> = mutableListOf<BlockPos>().also { positions ->
            val hit = player.raycast(20.0, 0F, false)
            if (hit.type == HitResult.Type.BLOCK) {
                val blockHit = hit as BlockHitResult

                outer@for (m in -range .. range) {
                    for (n in -range .. range) {
                        when (blockHit.side ?: break@outer) {
                            Direction.DOWN, Direction.UP -> BlockPos(
                                initialBlockPos.x + m,
                                initialBlockPos.y,
                                initialBlockPos.z + n
                            )
                            Direction.NORTH, Direction.SOUTH -> BlockPos(
                                initialBlockPos.x + m,
                                initialBlockPos.y + n,
                                initialBlockPos.z
                            )
                            Direction.EAST, Direction.WEST -> BlockPos(
                                initialBlockPos.x,
                                initialBlockPos.y + n,
                                initialBlockPos.z + m
                            )
                        }.let { positions.add(it) }
                    }
                }
            }
        }
    }
}