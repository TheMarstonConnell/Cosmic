package com.mic.testmod.init;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mic.testmod.blocks.HardBlock;
import com.mic.testmod.blocks.OreBlock;
import com.mic.testmod.blocks.machine.bedrock.BlockBedrockMiner;
import com.mic.testmod.blocks.machine.compressor.BlockCompressor;
import com.mic.testmod.blocks.machine.duster.BlockDustCollector;
import com.mic.testmod.blocks.machine.enscriber.BlockEnscriber;
import com.mic.testmod.blocks.machine.forge.BlockCosmicForge;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;



public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block METEOR_BLOCK = new OreBlock("meteor_block", Material.ROCK, ModItems.METEOR_CHUNK, 30.0f, 8.0f, 3);
	public static final Block DSTEEL_BLOCK = new HardBlock("dense_steel_block", Material.IRON, 7.0f, 0, 3);
	public static final Block METEORITE_BLOCK = new HardBlock("meteorite_block", Material.IRON, 12.0f, 6.0f, 3);

	
	public static final Block COSMIC_FORGE = new BlockCosmicForge("cosmic_forge");
	
	public static final Block DUST_COLLECTOR = new BlockDustCollector("dust_collector");
	
	public static final Block BEDROCK_MINER = new BlockBedrockMiner("bedrock_miner");
	
	public static final Block ENSCRIBER = new BlockEnscriber("enscriber");
	
	public static final Block COMPRESSOR = new BlockCompressor("compressor");
	
}
