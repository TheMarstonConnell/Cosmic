package com.mic.testmod.items.tools;

import com.mic.testmod.Main;
import com.mic.testmod.init.ModItems;
import com.mic.testmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;

public class ToolPickaxe extends ItemPickaxe implements IHasModel{
	public ToolPickaxe(String name, ToolMaterial material) {
		super(material);
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
