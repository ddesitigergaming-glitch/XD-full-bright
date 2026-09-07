package net.brightnessplus.mixin;

import net.brightnessplus.BrightnessPlus;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.ModifyArg;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightmapTextureManager.class)
public class MixinLightmapTextureManager {

    @ModifyArg(
        method = "update",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/option/SimpleOption;getValue()Ljava/lang/Object;"
        )
    )
    private Object brightnessPlus$overrideGamma(SimpleOption<Double> option) {
        try {
            MinecraftClient client = MinecraftClient.getInstance();

            if (client != null
                    && client.options != null
                    && option == client.options.getGamma()
                    && BrightnessPlus.isFullbrightEnabled()) {

                return BrightnessPlus.getBrightness();
            }
        } catch (Throwable ignored) {
        }

        return option.getValue();
    }
}
