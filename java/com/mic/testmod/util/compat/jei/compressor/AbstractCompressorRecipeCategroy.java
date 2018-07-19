package com.mic.testmod.util.compat.jei.compressor;

import com.mic.testmod.util.Reference;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractCompressorRecipeCategroy<T extends IRecipeWrapper> implements IRecipeCategory<T>{
	
	protected static final int input1 = 0;

	protected static final int output = 1;
	
	protected final IDrawableAnimated animatedArrow;
	
	protected static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/compressor.png");
	
	public AbstractCompressorRecipeCategroy(IGuiHelper helper){
		
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 14, 24, 17);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);
	}


	

}
