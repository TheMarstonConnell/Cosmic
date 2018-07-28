package com.mic.testmod.util.compat.jei.compressor;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.mic.testmod.blocks.machine.compressor.CompressorRecipes;
import com.mic.testmod.blocks.machine.forge.CosmicForgeRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class CompressorRecipeMaker {
	public static List<CompressorRecipe> getRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		CompressorRecipes instance = CompressorRecipes.getInstance();
		Map<ItemStack, ItemStack> recipes = instance.getCompressingList();
		List<CompressorRecipe> jeiRecipes = Lists.newArrayList();
		
		
		for(Entry<ItemStack, ItemStack> entry : recipes.entrySet()){
		
				ItemStack input1 = entry.getKey();
				ItemStack output = entry.getValue();
				ItemStack input = input1;
				CompressorRecipe recipe = new CompressorRecipe(input, output);
				jeiRecipes.add(recipe);
			
		}
		return jeiRecipes;
	}
}
