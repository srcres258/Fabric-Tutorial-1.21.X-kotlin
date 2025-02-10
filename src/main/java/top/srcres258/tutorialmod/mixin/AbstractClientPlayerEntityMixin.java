package top.srcres258.tutorialmod.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.srcres258.tutorialmod.item.ModItems;

// With help from https://github.com/Globox1997/MedievalWeapons/blob/1.21/src/main/java/net/medievalweapons/mixin/client/AbstractClientPlayerEntityMixin.java
// Under MIT License!
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(
            method = "getFovMultiplier",
            at = @At(value = "TAIL"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    private void getFovMultiplierMixin(CallbackInfoReturnable<Float> info, float f) {
        var item = getActiveItem().getItem();
        var itemStack = getActiveItem();
        if (isUsingItem() && itemStack.isOf(ModItems.INSTANCE.getKAUPEN_BOW())) {
            var i = getItemUseTime();
            var g = (float) i / 20.0F;
            g = g > 1.0F ? 1.0F : g * g;
            f *= 1.0F - g * 0.15F;
            info.setReturnValue(MathHelper.lerp(
                    MinecraftClient.getInstance().options.getFovEffectScale().getValue().floatValue(),
                    1.0F,
                    f
            ));
        }
    }
}
