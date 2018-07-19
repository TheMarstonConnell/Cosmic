package com.mic.testmod.blocks.machine.duster;

import com.mic.testmod.blocks.machine.bedrock.slots.OutputSlot;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerDustCollector extends Container{
	private final TileEntityDustCollector tileentity;
	private int counter;
	
	public ContainerDustCollector(InventoryPlayer player, TileEntityDustCollector tileentity){
		this.tileentity = tileentity;
		
		for(int x = 0; x < 9; x ++){
			this.addSlotToContainer(new OutputSlot(tileentity, x, 10 + x * 18, 24));
		}
		
		for(int y = 0; y < 3; y ++){
			for(int x = 0; x < 9; x ++){
				this.addSlotToContainer(new Slot(player, x + (y * 9) + 9, 10 + x * 18, 70 + y * 18));
			}
		}
		
		for(int x = 0; x < 9; x ++){
			this.addSlotToContainer(new Slot(player, x, 10 + x * 18, 128));
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
