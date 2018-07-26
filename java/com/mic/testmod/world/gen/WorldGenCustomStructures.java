package com.mic.testmod.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomStructures implements IWorldGenerator{
	public static final WorldGenStructure METEOR = new WorldGenStructure("meteor");

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()){
		case 2:
			break;
			
		case 1:
			break;
		case 0:
			
			
			generateStructure(METEOR, world, random, chunkX, chunkZ, 90, Blocks.SAND, BiomeDesert.class);
			random = new Random();
			generateStructure(METEOR, world, random, chunkX, chunkZ, 90, Blocks.GRASS, BiomePlains.class);
			random = new Random();
			generateStructure(METEOR, world, random, chunkX, chunkZ, 90, Blocks.SAND, BiomeBeach.class);
			random = new Random();
			generateStructure(METEOR, world, random, chunkX, chunkZ, 90, Blocks.GRASS, BiomeHills.class);
			random = new Random();
			generateStructure(METEOR, world, random, chunkX, chunkZ, 60, Blocks.GRASS, BiomeSavanna.class);
			random = new Random();
			generateStructure(METEOR, world, random, chunkX, chunkZ, 60, Blocks.GRASS, BiomeSwamp.class);
			random = new Random();
			generateStructure(METEOR, world, random, chunkX, chunkZ, 60, Blocks.HARDENED_CLAY, BiomeMesa.class);




			
			break;
		case -1:
			
			
		}
		
	}
	
	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>...classes){
			ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
			
			int x = (chunkX * 16) + random.nextInt(15);
			int z = (chunkX * 16) + random.nextInt(15);
			
			int y = getGroundFromAbove(world, x, z, topBlock);
			
			BlockPos pos = new BlockPos(x,y,z);
			
			Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
			
			if(world.getWorldType() != WorldType.FLAT){
				if(classesList.contains(biome)){
					if(random.nextInt(chance) == 0){
						generator.generate(world, random, pos);
					}
				}
			}
			
			
			
	}
	
	// find a grass or dirt block to place the bush on
		public static int getGroundFromAbove(World world, int x, int z, Block topBlock)
		{
			int y = world.getHeight();
			boolean foundGround = false;
			while(!foundGround && y-- >= 0)
			{
				Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
				// "ground" for our bush is grass or dirt
				foundGround = blockAt == topBlock;
			}

			return y;
		}
	
	
}
