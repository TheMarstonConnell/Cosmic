package com.mic.testmod.items.tools;

import com.mic.testmod.Main;
import com.mic.testmod.init.ModItems;
import com.mic.testmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;

public class ToolAxe extends ItemAxe implements IHasModel{
	public ToolAxe(String name, ToolMaterial material) {
		super(material, 6.0f, -3.2f);
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
