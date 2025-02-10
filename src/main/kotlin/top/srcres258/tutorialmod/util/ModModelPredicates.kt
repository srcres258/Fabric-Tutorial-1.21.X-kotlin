package top.srcres258.tutorialmod.util

import net.minecraft.client.item.ModelPredicateProviderRegistry
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
    }
}