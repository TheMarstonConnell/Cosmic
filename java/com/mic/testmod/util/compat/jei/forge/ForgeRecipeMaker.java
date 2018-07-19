package com.mic.testmod.util.compat.jei.forge;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.mic.testmod.blocks.machine.forge.CosmicForgeRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class ForgeRecipeMaker {
	public static List<CosmicForgeRecipe> getRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		CosmicForgeRecipes instance = CosmicForgeRecipes.getInstance();
		Table<ItemStack, ItemStack, ItemStack> recipes = instance.getDualSmeltingList();
		List<CosmicForgeRecipe> jeiRecipes = Lists.newArrayList();
		
		
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipes.columnMap().entrySet()){
			for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()){
				ItemStack input1 = entry.getKey();
				ItemStack input2 = ent.getKey();
				ItemStack output = ent.getValue();
				List<ItemStack> inputs = Lists.newArrayList(input1, input2);
				CosmicForgeRecipe recipe = new CosmicForgeRecipe(inputs, output);
				jeiRecipes.add(recipe);
			}
		}
		return jeiRecipes;
	}
}
