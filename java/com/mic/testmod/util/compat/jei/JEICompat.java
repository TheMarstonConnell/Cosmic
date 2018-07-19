package com.mic.testmod.util.compat.jei;

import java.util.IllegalFormatException;

import com.mic.testmod.blocks.machine.compressor.ContainerCompressor;
import com.mic.testmod.blocks.machine.compressor.GuiCompressor;
import com.mic.testmod.blocks.machine.forge.ContainerCosmicForge;
import com.mic.testmod.blocks.machine.forge.GuiCosmicForge;
import com.mic.testmod.util.compat.jei.compressor.CompressorRecipeCategory;
import com.mic.testmod.util.compat.jei.compressor.CompressorRecipeMaker;
import com.mic.testmod.util.compat.jei.forge.CosmicForgeRecipeCategory;
import com.mic.testmod.util.compat.jei.forge.ForgeRecipeMaker;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.util.text.translation.I18n;

@JEIPlugin
public class JEICompat implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new CosmicForgeRecipeCategory(gui));
		registry.addRecipeCategories(new CompressorRecipeCategory(gui));

	}

	@Override
	public void register(IModRegistry registry) {
		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		
		registry.addRecipes(ForgeRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.FORGE);
		registry.addRecipeClickArea(GuiCosmicForge.class, 40, 36, 24, 17, RecipeCategories.FORGE);
		recipeTransfer.addRecipeTransferHandler(ContainerCosmicForge.class, RecipeCategories.FORGE, 0, 1, 3, 36);
		
		registry.addRecipes(CompressorRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.COMPRESSOR);
		registry.addRecipeClickArea(GuiCompressor.class, 43, 36, 24, 17, RecipeCategories.COMPRESSOR);
		recipeTransfer.addRecipeTransferHandler(ContainerCompressor.class, RecipeCategories.COMPRESSOR, 0, 1, 2, 34);
	}

	public static String translateToLocal(String key) {
		if (I18n.canTranslate(key))
			return I18n.translateToLocal(key);
		else
			return I18n.translateToFallback(key);

	}

	public static String translateToLocalFromFormatted(String key, Object... format) {
		String s = translateToLocal(key);

		try {
			return String.format(s, format);
		} catch (IllegalFormatException e) {
			return "Format error: " + s;
		}
	}

}
