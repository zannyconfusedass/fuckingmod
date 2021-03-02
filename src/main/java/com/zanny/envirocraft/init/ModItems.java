package com.zanny.envirocraft.init;

import com.zanny.envirocraft.EnviroCraft;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final BlockItem MUD = new BlockItem(ModBlocks.MUD, new Item.Settings().group(EnviroCraft.ITEM_GROUP));
    public static final BlockItem WET_DIRT = new BlockItem(ModBlocks.WET_DIRT, new Item.Settings().group(EnviroCraft.ITEM_GROUP));
    public static final BlockItem DRY_MUD = new BlockItem(ModBlocks.DRY_MUD, new Item.Settings().group(EnviroCraft.ITEM_GROUP));
    //Register
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(EnviroCraft.MOD_ID, "mud"), MUD);
        Registry.register(Registry.ITEM, new Identifier(EnviroCraft.MOD_ID, "wet_dirt"), WET_DIRT);
        Registry.register(Registry.ITEM, new Identifier(EnviroCraft.MOD_ID, "dry_mud"), DRY_MUD);

    }
}
