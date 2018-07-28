package com.mic.testmod.blocks.machine.forge;

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

public class CosmicForgeRecipes 
{	
	private static final CosmicForgeRecipes INSTANCE = new CosmicForgeRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static CosmicForgeRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private CosmicForgeRecipes() 
	{
		addForgeRecipe(new ItemStack(ModItems.METEOR_CHUNK), new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.METEOR_INGOT), 5.0F);
		addForgeRecipe(new ItemStack(ModItems.DENSE_ROCK), new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.DENSE_STEEL_INGOT), 5.0F);
		addForgeRecipe(new ItemStack(ModItems.COMET_TAIL_DUST), new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.COMET_TAIL_INGOT), 5.0F);
		addForgeRecipe(new ItemStack(ModItems.PLACE_HELM), new ItemStack(ModItems.HELMET_COMET), new ItemStack(ModItems.HELMET_GODLY), 5.0F);
		addForgeRecipe(new ItemStack(ModItems.PLACE_CHEST), new ItemStack(ModItems.CHESTPLATE_COMET), new ItemStack(ModItems.CHESTPLATE_GODLY), 5.0F);
		addForgeRecipe(new ItemStack(ModItems.PLACE_LEGS), new ItemStack(ModItems.LEGGINGS_COMET), new ItemStack(ModItems.LEGGINGS_GODLY), 5.0F);
		addForgeRecipe(new ItemStack(ModItems.PLACE_BOOTS), new ItemStack(ModItems.BOOTS_COMET), new ItemStack(ModItems.BOOTS_GODLY), 5.0F);
		addForgeRecipe(new ItemStack(ModItems.COMET_TAIL_DUST), new ItemStack(Items.DIAMOND), new ItemStack(ModItems.COSMIC_GEMSTONE), 6.8F);
		addForgeRecipe(new ItemStack(ModItems.COMPRESSED_GOLD), new ItemStack(ModItems.DENSE_STEEL_INGOT), new ItemStack(ModItems.STURDY_GOLD_INGOT), 3.0F);

	}

	
	public void addForgeRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) 
	{
		if(getForgeResult(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.smeltingList.put(input2, input1, result);
		this.experienceList.put(result, Float.valueOf(experience));

	}
	
	public ItemStack getForgeResult(ItemStack input1, ItemStack input2) 
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) 
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) 
					{
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.smeltingList;
	}
	
	public float getForgeExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
