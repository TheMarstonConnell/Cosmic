package com.mic.testmod.util.compat.oredict;

import com.mic.testmod.init.ModItems;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat {
	public static void registerOres(){
		OreDictionary.registerOre("gearStone", ModItems.STONE_GEAR);
		OreDictionary.registerOre("stickIron", ModItems.IRON_ROD);

	}
}
