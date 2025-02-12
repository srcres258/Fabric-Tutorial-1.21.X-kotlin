package top.srcres258.tutorialmod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SweetBerryBushBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.state.property.IntProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldView
import net.minecraft.world.event.GameEvent
import top.srcres258.tutorialmod.item.ModItems

class HoneyBerryBushBlock(settings: Settings) : SweetBerryBushBlock(settings) {
    companion object {
        val AGE: IntProperty = SweetBerryBushBlock.AGE
    }

    override fun getPickStack(world: WorldView, pos: BlockPos, state: BlockState): ItemStack =
        ItemStack(ModItems.HONEY_BERRIES)

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hit: BlockHitResult
    ): ActionResult {
        val i = state.get(AGE)
        val bl = i == 3
        if (i > 1) {
            val j = 1 + world.random.nextInt(2)
            Block.dropStack(world, pos, ItemStack(ModItems.HONEY_BERRIES,
                j + if (bl) 1 else 0))
            val blockState = state.with(AGE, Integer.valueOf(1))
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS)
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState))
            return ActionResult.success(world.isClient)
        } else {
            return super.onUse(state, world, pos, player, hit)
        }
    }
}