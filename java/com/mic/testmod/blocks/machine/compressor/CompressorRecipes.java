package com.mic.testmod.blocks.machine.compressor;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.mic.testmod.init.ModBlocks;
import com.mic.testmod.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CompressorRecipes 
{	
	private static final CompressorRecipes INSTANCE = new CompressorRecipes();
	private final Map<ItemStack, ItemStack> compressingList = Maps.<ItemStack, ItemStack>newHashMap();

	
	public static CompressorRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private CompressorRecipes() 
	{
		addCompressorRecipe(new ItemStack(ModItems.PLANET_CRUST), new ItemStack(ModItems.PLANET_CORE));

	}

	
	public void addCompressorRecipe(ItemStack input1, ItemStack result) 
	{
		if(getCompressorResult(input1) != ItemStack.EMPTY) return;
		this.compressingList.put(input1, result);


	}
	
	public ItemStack getCompressorResult(ItemStack input1) 
	{
		for(Entry<ItemStack, ItemStack> entry : this.compressingList.entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
						return (ItemStack)entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getCompressingList() 
	{
		return this.compressingList;
	}
	
}
