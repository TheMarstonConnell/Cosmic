package com.mic.testmod.items;

import com.mic.testmod.Main;
import com.mic.testmod.init.ModItems;
import com.mic.testmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modTab);

		ModItems.ITEMS.add(this);

	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
