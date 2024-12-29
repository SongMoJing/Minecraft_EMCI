package com.funcablaze.emci;

import com.funcablaze.emci.client.func.BlockEntity_Function_block;
import com.funcablaze.emci.client.func.Block_Function_block;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Emci implements ModInitializer {

	public static final String MOD_ID = "emci";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Block_Function_block FUNCTION_BLOCK = new Block_Function_block(FabricBlockSettings.copyOf(Blocks.COMMAND_BLOCK));
	public static final BlockEntityType<BlockEntity_Function_block> BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new Identifier(Emci.MOD_ID, "function_block"),
			FabricBlockEntityTypeBuilder.create(BlockEntity_Function_block::new, FUNCTION_BLOCK).build()
	);

	@Override
	public void onInitialize() {
		LOGGER.info("成功载入Fabric EMCI!");

		Registry.register(Registries.BLOCK, new Identifier(Emci.MOD_ID, "function_block"), FUNCTION_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(Emci.MOD_ID, "function_block"), new BlockItem(FUNCTION_BLOCK, new FabricItemSettings()));
	}
}
