package top.srcres258.tutorialmod.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.math.Vec3d

class SlimeyEffect(category: StatusEffectCategory, color: Int) : StatusEffect(category, color) {
    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int): Boolean {
        if (entity.horizontalCollision) {
            val initialVec = entity.velocity
            val climbVec = Vec3d(initialVec.x, 0.2, initialVec.z)
            entity.velocity = climbVec.multiply(0.96)
            return true
        }

        return super.applyUpdateEffect(entity, amplifier)
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean = true
}