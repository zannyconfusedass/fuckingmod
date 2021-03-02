package com.zanny.envirocraft.init;

import com.zanny.envirocraft.EnviroCraft;
import com.zanny.envirocraft.world.entity.CamelEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities implements ModInitializer {
    public static final EntityType<CamelEntity> CAMEL = Registry.register(Registry.ENTITY_TYPE, new Identifier("envirocraft", "world/entity/camelentity"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CamelEntity::new).dimensions(EntityDimensions.fixed(1f, 2.25f)).build());

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(CAMEL, CamelEntity.createMobAttributes());
    }
}