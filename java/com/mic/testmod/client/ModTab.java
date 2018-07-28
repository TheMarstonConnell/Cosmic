package com.mic.testmod.client;

import com.mic.testmod.init.ModItems;
import com.mic.testmod.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTab extends CreativeTabs{

	public ModTab() {
		super("modTab");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.METEOR_CHUNK);
	}
	
	

}
