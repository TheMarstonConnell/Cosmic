package com.mic.testmod.util.compat.jei.compressor;

import java.awt.Color;
import java.util.List;

import com.mic.testmod.util.compat.jei.JEICompat;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class CompressorRecipe implements IRecipeWrapper{

	
	private final ItemStack input;
	private final ItemStack output;
	
	
	public CompressorRecipe(ItemStack input, ItemStack output){
		this.input = input;
		this.output = output;
	}


	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
		
	}
	
}
