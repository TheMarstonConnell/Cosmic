package com.mic.testmod.blocks.machine.forge.slots;

import com.mic.testmod.blocks.machine.forge.TileEntityCosmicForge;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CosmicForgeFuelSlot extends Slot {

	public CosmicForgeFuelSlot(IInventory inventoryIn, int index, int x, int y) {
		super(inventoryIn, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityCosmicForge.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {

		return super.getItemStackLimit(stack);
	}
	
	
}
