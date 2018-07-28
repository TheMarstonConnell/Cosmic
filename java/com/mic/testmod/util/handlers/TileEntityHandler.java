package com.mic.testmod.util.handlers;

import com.mic.testmod.blocks.machine.bedrock.TileEntityBedrockMiner;
import com.mic.testmod.blocks.machine.compressor.TileEntityCompressor;
import com.mic.testmod.blocks.machine.duster.TileEntityDustCollector;
import com.mic.testmod.blocks.machine.enscriber.TileEntityEnscriber;
import com.mic.testmod.blocks.machine.forge.TileEntityCosmicForge;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityBedrockMiner.class, "bedrock_miner");
		GameRegistry.registerTileEntity(TileEntityCosmicForge.class, "cosmic_forge");
		GameRegistry.registerTileEntity(TileEntityDustCollector.class, "dust_collector");
		GameRegistry.registerTileEntity(TileEntityEnscriber.class, "enscriber");
		GameRegistry.registerTileEntity(TileEntityCompressor.class, "compressor");


	}
}
