package com.mic.testmod.items.tools;

import com.mic.testmod.Main;
import com.mic.testmod.init.ModItems;

public class ToolSplitter extends ItemAOE {
	public ToolSplitter(String name, ToolMaterial material){
		super(name, material, true, false);
		setCreativeTab(Main.modTab);
		ModItems.ITEMS.add(this);
	}
}
