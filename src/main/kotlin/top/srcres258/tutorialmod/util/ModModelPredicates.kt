package top.srcres258.tutorialmod.util

import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.component.ModDataComponentTypes
import top.srcres258.tutorialmod.item.ModItems

object ModModelPredicates {
    fun registerModelPredicates() {
        ModelPredicateProviderRegistry.register(
            ModItems.CHISEL,
            Identifier.of(TutorialMod.MOD_ID, "used")
        ) { stack, world, entity, seed ->
            if (stack.get(ModDataComponentTypes.COORDINATES) == null) 0F else 1F
        }

        registerCustomBow(ModItems.KAUPEN_BOW)
    }

    private fun registerCustomBow(item: Item) {
        ModelPredicateProviderRegistry.register(
            item,
            Identifier.ofVanilla("pull")
        ) { stack, world, entity, seed ->
            if (entity == null) {
                0.0F
            } else {
                if (entity.activeItem == stack) {
                    (stack.getMaxUseTime(entity) - entity.itemUseTimeLeft).toFloat() / 20.0F
                } else {
                    0.0F
                }
            }
        }
        ModelPredicateProviderRegistry.register(
            item,
            Identifier.ofVanilla("pulling"),
        ) { stack, world, entity, seed ->
            if (entity != null && entity.isUsingItem && entity.activeItem == stack) {
                1.0F
            } else {
                0.0F
            }
        }
    }
}