package top.srcres258.tutorialmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.item.Items
import net.minecraft.potion.Potions
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.component.ModDataComponentTypes
import top.srcres258.tutorialmod.effect.ModEffects
import top.srcres258.tutorialmod.enchantment.ModEnchantmentEffects
import top.srcres258.tutorialmod.item.ModItemGroups
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.potion.ModPotions
import top.srcres258.tutorialmod.sound.ModSounds
import top.srcres258.tutorialmod.util.HammerUsageEvent

object TutorialMod : ModInitializer {
	const val MOD_ID = "tutorialmod"
	val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItems.registerModItems()
		ModBlocks.registerModBlocks()
		ModItemGroups.registerItemGroups()
		ModDataComponentTypes.registerDataComponentTypes()
		ModSounds.registerSounds()
		ModEffects.registerEffects()
		ModPotions.registerPotions()
		ModEnchantmentEffects.registerEnchantmentEffects()

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600)

		PlayerBlockBreakEvents.BEFORE.register(HammerUsageEvent)

		AttackEntityCallback.EVENT.register { player, world, hand, entity, hitResult ->
			if (entity is SheepEntity) {
				if (player.mainHandStack.item == Items.END_ROD) {
					player.sendMessage(Text.literal("The Player just hit a sheep with an END ROD! YOU SICK FRICK!"))
					player.mainHandStack.decrement(1)
					entity.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 600, 6))
				}
			}

			ActionResult.PASS
		}

		FabricBrewingRecipeRegistryBuilder.BUILD.register { builder ->
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION)
		}

		CompostingChanceRegistry.INSTANCE.run {
			add(ModItems.CAULIFLOWER, 0.5F)
			add(ModItems.CAULIFLOWER_SEEDS, 0.25F)
			add(ModItems.HONEY_BERRIES, 0.15F)
		}
	}
}