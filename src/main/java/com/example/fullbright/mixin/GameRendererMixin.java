package com.example.fullbright.mixin;

import com.example.fullbright.FullbrightChatMod;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightmapTextureManager.class)
public class GameRendererMixin {

    @Inject(
        method = "getBrightness", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void fullbright$renderOverride(
        CallbackInfoReturnable<Float> info
    ) {
        if (FullbrightChatMod.isActive()) {
            info.setReturnValue(100.0f);
        }
    }
}
