package com.example.fullbright;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;

public class FullbrightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                ClientCommandManager.literal("on")
                    .executes(context -> {
                        FullbrightChatMod.setActive(true);
                        var player = MinecraftClient.getInstance().player;
                        if (player != null) player.sendMessage(Text.literal("Fullbright enabled"), false);
                        return 1;
                    })
            );

            dispatcher.register(
                ClientCommandManager.literal("off")
                    .executes(context -> {
                        FullbrightChatMod.setActive(false);
                        var player = MinecraftClient.getInstance().player;
                        if (player != null) player.sendMessage(Text.literal("Fullbright disabled"), false);
                        return 1;
                    })
            );
        });
    }
}
