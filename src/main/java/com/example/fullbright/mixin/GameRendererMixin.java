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
        method = "pack", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private static void fullbright$packOverride(
        int blockLight, 
        int skyLight, 
        CallbackInfoReturnable<Integer> cir
    ) {
        if (FullbrightChatMod.isActive()) {
            // Mobile render engine ke liye light values ko direct maximum (15, 15) par force kar do
            cir.setReturnValue((15 << 4) | 15);
        }
    }
}
