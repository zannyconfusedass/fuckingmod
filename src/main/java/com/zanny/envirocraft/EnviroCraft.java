package com.zanny.envirocraft;

import com.zanny.envirocraft.init.ModBlocks;
import com.zanny.envirocraft.init.ModItems;
import com.zanny.envirocraft.world.entity.CamelEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.geckolib3.GeckoLib;

public class EnviroCraft implements ModInitializer {

    public static final String MOD_ID = "enviro";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModItems.MUD));

    public static final EntityType<CamelEntity> CAMEL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(EnviroCraft.MOD_ID, "camel"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CamelEntity::new).dimensions(EntityDimensions.fixed(1f, 2.25f)).build());

    //Entity sounds
    public static final Identifier CAMEL_AMBIENT_SOUND = new Identifier(EnviroCraft.MOD_ID, "camel_ambient");
    public static SoundEvent CAMEL_AMBIENT = new SoundEvent (CAMEL_AMBIENT_SOUND);
    public static final Identifier CAMEL_HURT_SOUND = new Identifier(EnviroCraft.MOD_ID, "camel_hurt");
    public static SoundEvent CAMEL_HURT = new SoundEvent (CAMEL_HURT_SOUND);
    public static final Identifier CAMEL_DEATH_SOUND = new Identifier(EnviroCraft.MOD_ID, "camel_death");
    public static SoundEvent CAMEL_DEATH = new SoundEvent (CAMEL_DEATH_SOUND);
    public static final Identifier CAMEL_STEP_SOUND = new Identifier(EnviroCraft.MOD_ID, "camel_step");
    public static SoundEvent CAMEL_STEP = new SoundEvent (CAMEL_STEP_SOUND);
    public static final Identifier BABY_CAMEL_HUM = new Identifier(EnviroCraft.MOD_ID, "camel_hum");
    public static SoundEvent CALF_HUM = new SoundEvent (BABY_CAMEL_HUM);
    public static final Identifier CAMEL_ANGRY_SOUND = new Identifier(EnviroCraft.MOD_ID, "camel_angry");
    public static SoundEvent CAMEL_GROWL = new SoundEvent (CAMEL_ANGRY_SOUND);

    //Spawn eggs
    public static final Item CAMEL_SPAWN_EGG = new SpawnEggItem(EnviroCraft.CAMEL, 13540691, 16777149, new Item.Settings().group(EnviroCraft.ITEM_GROUP));




    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        FabricDefaultAttributeRegistry.register(CAMEL, CamelEntity.createCamelAttributes());
        GeckoLib.initialize();

        //Sounds
        Registry.register(Registry.SOUND_EVENT, EnviroCraft.CAMEL_AMBIENT_SOUND, CAMEL_AMBIENT);
        Registry.register(Registry.SOUND_EVENT, EnviroCraft.CAMEL_HURT_SOUND, CAMEL_HURT);
        Registry.register(Registry.SOUND_EVENT, EnviroCraft.CAMEL_DEATH_SOUND, CAMEL_DEATH);
        Registry.register(Registry.SOUND_EVENT, EnviroCraft.CAMEL_STEP_SOUND,CAMEL_STEP);
        Registry.register(Registry.SOUND_EVENT, EnviroCraft.BABY_CAMEL_HUM, CALF_HUM);
        Registry.register(Registry.SOUND_EVENT, EnviroCraft.CAMEL_ANGRY_SOUND, CAMEL_GROWL);

        //Spawn Eggs
        Registry.register(Registry.ITEM, new Identifier(EnviroCraft.MOD_ID, "camel_spawn_egg"), CAMEL_SPAWN_EGG);
    }
}
