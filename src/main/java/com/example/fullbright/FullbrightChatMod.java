package com.example.fullbright;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

public class FullbrightChatMod implements ClientModInitializer {

    private static boolean active = false;

    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            dispatcher.register(
                ClientCommandManager.literal("on")
                    .executes(context -> {
                        active = true;
                        context.getSource().sendFeedback(
                            Text.literal("§e[Fullbright] §aON")
                        );
                        return 1;
                    })
            );

            dispatcher.register(
                ClientCommandManager.literal("off")
                    .executes(context -> {
                        active = false;
                        context.getSource().sendFeedback(
                            Text.literal("§e[Fullbright] §cOFF")
                        );
                        return 1;
                    })
            );
        });
    }

    public static boolean isActive() {
        return active;
    }
}
