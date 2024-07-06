package com.funcablaze.emci;

import com.funcablaze.emci.block.ModBlocks;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Emci implements ModInitializer {

	public static final String MOD_ID = "emci";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("成功载入Fabric EMCI!");
		ModBlocks.registerModBlocks();
	}
}