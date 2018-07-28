package com.mic.testmod.util.compat.jei.compressor;

import com.mic.testmod.util.Reference;
import com.mic.testmod.util.compat.jei.RecipeCategories;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class CompressorRecipeCategory extends AbstractCompressorRecipeCategroy<CompressorRecipe> {

	private final IDrawable background;
	private final String name;

	public CompressorRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 4, 12, 150, 60);
		name = "Cosmic Compressor";
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		animatedArrow.draw(minecraft, 43, 23);

	}

	@Override
	public String getTitle() {

		return name;
	}

	@Override
	public String getModName() {
		return Reference.NAME;
	}

	@Override
	public String getUid() {
		return RecipeCategories.COMPRESSOR;
	}

	public void setRecipe(IRecipeLayout recipeLayout, CompressorRecipe recipeWrapper,
			IIngredients ingredients) {
		
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 21, 23);
		stacks.init(output, false, 76, 23);
		stacks.set(ingredients);
		
		
	};

}
