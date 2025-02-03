package top.srcres258.tutorialmod.component

import net.minecraft.component.ComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import top.srcres258.tutorialmod.TutorialMod

object ModDataComponentTypes {
    val COORDINATES = register("coordinates") { builder -> builder.codec(BlockPos.CODEC) }

    fun <T> register(name: String, builderOperator: (ComponentType.Builder<T>) -> ComponentType.Builder<T>) =
        Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TutorialMod.MOD_ID, name),
            builderOperator(ComponentType.builder()).build())

    fun registerDataComponentTypes() {
        TutorialMod.LOGGER.info("Registering Data Component Types for ${TutorialMod.MOD_ID}")
    }
}