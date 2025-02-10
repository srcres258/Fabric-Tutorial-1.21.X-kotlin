package top.srcres258.tutorialmod.item.custom

import net.minecraft.entity.Entity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.world.World
import top.srcres258.tutorialmod.item.ModArmorMaterials

private val MATERIAL_TO_EFFECT_MAP = mapOf<RegistryEntry<ArmorMaterial>, List<StatusEffectInstance>>(
    Pair(
        ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL,
        listOf(
            StatusEffectInstance(StatusEffects.HASTE, 400, 2, false, false),
            StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, 1, false, false)
        )
    )
)

class ModArmorItem(
    material: RegistryEntry<ArmorMaterial>,
    type: Type,
    settings: Settings
) : ArmorItem(material, type, settings) {
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (!world.isClient) {
            if (entity is PlayerEntity) {
                if (hasFullSuitOfArmorOn(entity)) {
                    evaluateArmorEffects(entity)
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected)
    }

    private fun evaluateArmorEffects(player: PlayerEntity) {
        for ((mapArmorMaterial, mapStatusEffects) in MATERIAL_TO_EFFECT_MAP) {
            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects)
            }
        }
    }

    private fun addStatusEffectForMaterial(
        player: PlayerEntity,
        mapArmorMaterial: RegistryEntry<ArmorMaterial>,
        mapStatusEffect: List<StatusEffectInstance>
    ) {
        val hasPlayerEffect = mapStatusEffect.all { instance -> player.hasStatusEffect(instance.effectType) }

        if (!hasPlayerEffect) {
            for (instance in mapStatusEffect) {
                player.addStatusEffect(StatusEffectInstance(
                    instance.effectType,
                    instance.duration,
                    instance.amplifier,
                    instance.isAmbient,
                    instance.shouldShowParticles()
                ))
            }
        }
    }

    private fun hasFullSuitOfArmorOn(player: PlayerEntity) =
        player.inventory.armor.all { armorStack ->
            !armorStack.isEmpty
        }

    private fun hasCorrectArmorOn(
        material: RegistryEntry<ArmorMaterial>,
        player: PlayerEntity
    ): Boolean = player.inventory.armor.all { armorStack ->
        val armorItem = armorStack.item
        if (armorItem is ArmorItem) {
            armorItem.material == material
        } else {
            false
        }
    }
}