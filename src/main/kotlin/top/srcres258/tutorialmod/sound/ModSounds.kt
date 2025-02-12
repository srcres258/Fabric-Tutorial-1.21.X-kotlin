package top.srcres258.tutorialmod.sound

import net.minecraft.block.jukebox.JukeboxSong
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

object ModSounds {
    val CHISEL_USE: SoundEvent = registerSoundEvent("chisel_use")

    val MAGIC_BLOCK_BREAK: SoundEvent = registerSoundEvent("magic_block_break")
    val MAGIC_BLOCK_STEP: SoundEvent = registerSoundEvent("magic_block_step")
    val MAGIC_BLOCK_PLACE: SoundEvent = registerSoundEvent("magic_block_place")
    val MAGIC_BLOCK_HIT: SoundEvent = registerSoundEvent("magic_block_hit")
    val MAGIC_BLOCK_FALL: SoundEvent = registerSoundEvent("magic_block_fall")

    val MAGIC_BLOCK_SOUNDS = BlockSoundGroup(
        1F, 1F,
        MAGIC_BLOCK_BREAK, MAGIC_BLOCK_STEP, MAGIC_BLOCK_PLACE, MAGIC_BLOCK_HIT, MAGIC_BLOCK_FALL
    )

    val BAR_BRAWL: SoundEvent = registerSoundEvent("bar_brawl")
    val BAR_BRAWL_KEY: RegistryKey<JukeboxSong> =
        RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TutorialMod.MOD_ID, "bar_brawl"))

    private fun registerSoundEvent(name: String) =
        Identifier.of(TutorialMod.MOD_ID, name).let { id ->
            Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id))
        }

    fun registerSounds() {
        TutorialMod.LOGGER.info("Registering Mod Sounds for ${TutorialMod.MOD_ID}")
    }
}