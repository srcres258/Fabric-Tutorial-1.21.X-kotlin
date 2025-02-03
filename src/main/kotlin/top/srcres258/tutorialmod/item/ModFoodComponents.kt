package top.srcres258.tutorialmod.item

import net.minecraft.component.type.FoodComponent
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects

object ModFoodComponents {
    val CAULIFLOWER: FoodComponent = FoodComponent.Builder()
        .nutrition(3)
        .saturationModifier(0.25F)
        .statusEffect(StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15F)
        .build()
}