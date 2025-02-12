package top.srcres258.tutorialmod.enchantment.custom

import com.mojang.serialization.MapCodec
import net.minecraft.enchantment.EnchantmentEffectContext
import net.minecraft.enchantment.effect.EnchantmentEntityEffect
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d

class LightningStrikerEnchantmentEffect : EnchantmentEntityEffect {
    companion object {
        val CODEC: MapCodec<LightningStrikerEnchantmentEffect> = MapCodec.unit(::LightningStrikerEnchantmentEffect)
    }

    override fun apply(
        world: ServerWorld,
        level: Int,
        context: EnchantmentEffectContext,
        user: Entity,
        pos: Vec3d
    ) {
        for (i in 0 ..< level) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.blockPos, SpawnReason.TRIGGERED)
        }
    }

    override fun getCodec(): MapCodec<out EnchantmentEntityEffect> = CODEC
}
