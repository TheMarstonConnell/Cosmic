package com.mic.testmod.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShiny extends ItemBase {

	public ItemShiny(String name) {
		super(name);

	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return super.hasEffect(stack) || stack.getMetadata() > 0;
	}

}
