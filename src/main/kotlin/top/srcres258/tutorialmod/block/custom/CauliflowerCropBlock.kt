package top.srcres258.tutorialmod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.CropBlock
import net.minecraft.item.ItemConvertible
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import top.srcres258.tutorialmod.item.ModItems

class CauliflowerCropBlock(settings: Settings) : CropBlock(settings) {
    companion object {
        const val MAX_AGE = 6
        val AGE: IntProperty = IntProperty.of("age", 0, MAX_AGE)
    }

    override fun getSeedsItem(): ItemConvertible = ModItems.CAULIFLOWER_SEEDS

    override fun getAgeProperty(): IntProperty = AGE

    override fun getMaxAge(): Int = MAX_AGE

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(AGE)
    }
}