package com.zanny.envirocraft;

import com.zanny.envirocraft.world.entity.renderer.CamelEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)

public class EnviroCraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerEntityRenders();
    }

    private void registerEntityRenders() {
        EntityRendererRegistry.INSTANCE.register(EnviroCraft.CAMEL, (dispatcher, context) -> new CamelEntityRenderer(dispatcher));
    }
}