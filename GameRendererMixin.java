package com.example.fullbright.mixin;

import com.example.fullbright.FullbrightChatMod;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    // FullBright lighting hook
    @Inject(
        method = "getNightVisionStrength",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void fullbright$nightVision(
            LivingEntity entity,
            float tickProgress,
            CallbackInfoReturnable<Float> cir) {

        if (FullbrightChatMod.isActive()) {
            cir.setReturnValue(1.0F);
        }
    }
}
