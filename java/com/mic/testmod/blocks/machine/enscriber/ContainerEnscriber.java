package com.mic.testmod.blocks.machine.enscriber;

import com.mic.testmod.blocks.machine.bedrock.slots.OutputSlot;
import com.mic.testmod.blocks.machine.forge.slots.CosmicForgeFuelSlot;
import com.mic.testmod.blocks.machine.forge.slots.SlotCosmicForgeOutput;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerEnscriber extends Container{
	private final TileEntityEnscriber tileentity;
	private int counter;
	
	public ContainerEnscriber(InventoryPlayer player, TileEntityEnscriber tileentity){
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new OutputSlot(tileentity, 0, 80, 36));
		
		for(int y = 0; y < 3; y ++){
			for(int x = 0; x < 9; x ++){
				this.addSlotToContainer(new Slot(player, x + (y * 9) + 9, 8 + x * 18, 84 + y * 18));
			}
		}
		
		for(int x = 0; x < 9; x ++){
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		this.counter = this.tileentity.getField(0);
		
		

	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) 
	{
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) 
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(stack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();

			}
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
	
	
	
}
