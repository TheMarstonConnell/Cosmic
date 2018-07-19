package com.mic.testmod.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class HardBlock extends BlockBase{

	/**
	 * Meteor Block.
	 * @author Marston Connell
	 * @param name
	 * @param material
	 * @param hardness
	 * @param lightLevel 15.0f = glowstone, 0.0f = stone
	 * @param tool 0 = wood, 1 = stone, 2 = iron, 3 = diamond
	 */
	public HardBlock(String name, Material material, float hardness, float lightLevel, int tool) {
		super(name, material);
		
		setSoundType(SoundType.METAL);
		this.setHardness(hardness);
		this.setResistance(15.0f);
		this.setLightLevel(lightLevel);
		this.setHarvestLevel("pickaxe", tool);
		
		
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		// TODO Auto-generated method stub
		return super.getItemDropped(state, rand, fortune);
	}
	
}
