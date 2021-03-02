package com.zanny.envirocraft.init;

import com.zanny.envirocraft.EnviroCraft;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block MUD = new Block(FabricBlockSettings
            .of(Material.SOIL)
            .breakByHand(true)
            .breakByTool(FabricToolTags.SHOVELS)
            .hardness(2.5f)
            .resistance(2.5f)
            .sounds(BlockSoundGroup.WET_GRASS));
    public static final Block WET_DIRT = new Block(FabricBlockSettings
            .of(Material.SOIL)
            .breakByHand(true)
            .breakByTool(FabricToolTags.SHOVELS)
            .hardness(1.5f)
            .resistance(2.5f)
            .sounds(BlockSoundGroup.WET_GRASS));
    public static final Block DRY_MUD = new Block(FabricBlockSettings
            .of(Material.SOIL)
            .breakByHand(true)
            .breakByTool(FabricToolTags.SHOVELS)
            .hardness(2.0f)
            .resistance(2.5f)
            .sounds(BlockSoundGroup.SOUL_SOIL));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(EnviroCraft.MOD_ID, "mud"), MUD);
        Registry.register(Registry.BLOCK, new Identifier(EnviroCraft.MOD_ID, "wet_dirt"), WET_DIRT);
        Registry.register(Registry.BLOCK, new Identifier(EnviroCraft.MOD_ID, "dry_mud"), DRY_MUD);
    }
}
