package com.example.fullbright;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class FullbrightChatMod implements ClientModInitializer {
    private static final double FULLBRIGHT_GAMMA = 15.0;
    private static boolean active = false;
    private static double originalGamma = 1.0;
    private static boolean saved = false;

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("on").executes(context -> {
                MinecraftClient client = MinecraftClient.getInstance();

                if (client.options != null) {
                    if (!active) {
                        originalGamma = client.options.getGamma().getValue();
                        saved = true;
                    }

                    active = true;
                    client.options.getGamma().setValue(FULLBRIGHT_GAMMA);
                    context.getSource().sendFeedback(Text.literal("§e[Fullbright] §aON"));
                }

                return 1;
            }));

            dispatcher.register(ClientCommandManager.literal("off").executes(context -> {
                MinecraftClient client = MinecraftClient.getInstance();

                active = false;

                if (client.options != null && saved) {
                    client.options.getGamma().setValue(originalGamma);
                    saved = false;
                }

                context.getSource().sendFeedback(Text.literal("§e[Fullbright] §cOFF"));
                return 1;
            }));
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (active && client.options != null
                    && client.options.getGamma().getValue() != FULLBRIGHT_GAMMA) {
                client.options.getGamma().setValue(FULLBRIGHT_GAMMA);
            }
        });
    }
}
