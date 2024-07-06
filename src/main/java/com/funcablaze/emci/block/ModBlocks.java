package com.funcablaze.emci.block;

import com.funcablaze.emci.Emci;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks implements ModInitializer {

    public static final Block FUNCTION_BLOCK = registerBlocks("function_block", new Block(FabricBlockSettings.copyOf(Blocks.COMMAND_BLOCK)));

    public static void registerModBlocks() {}

    private static Block registerBlocks(String name, Block block) {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Emci.MOD_ID, name), block);
    }

    private static Item registerBlockItems(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Emci.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    @Override
    public void onInitialize() {

    }
}