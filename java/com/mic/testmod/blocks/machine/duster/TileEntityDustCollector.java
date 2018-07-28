package com.mic.testmod.blocks.machine.duster;

import net.minecraft.tileentity.TileEntity;

import java.util.Random;

import com.mic.testmod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityDustCollector extends TileEntity implements IInventory, ITickable {
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack> withSize(9, ItemStack.EMPTY);
	private String customName;

	private int counter;
	private int reach = 0;

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.dust_collector";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : this.inventory) {
			if (!stack.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return (ItemStack) this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack) this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);

		if (stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());
		if (index == 0 && index + 1 == 1 && !flag) {
			ItemStack stack1 = (ItemStack) this.inventory.get(index + 1);

			this.markDirty();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack> withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.counter = compound.getInteger("counter");
		this.reach = compound.getInteger("reach");

		if (compound.hasKey("CustomName", 8))
			this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("counter", (short) this.counter);
		compound.setInteger("reach", (short) this.reach);
		ItemStackHelper.saveAllItems(compound, this.inventory);

		if (this.hasCustomName())
			compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public void update() {
		if (reach == 0) {
			Random rand = new Random();
			this.reach = rand.nextInt(144000) + 14400;
//			this.reach = rand.nextInt(300) + 1;
			this.markDirty();
		}

		if (counter > reach) {
			Random rand = new Random();

			int choice = rand.nextInt(4);

			ItemStack item = null;
			switch (choice) {
			case 0:
				item = new ItemStack(ModItems.COMET_TAIL_DUST);
				break;
			case 1:
				item = new ItemStack(Items.BLAZE_POWDER); 
				break;
			case 2:
				item = new ItemStack(Blocks.PACKED_ICE);
				break;
			case 3:
				item = new ItemStack(Items.GLOWSTONE_DUST);
				break;
			default:
				item = new ItemStack(Items.DIAMOND);

			}

			boolean placed = false;
			int x = 0;
			while(!placed){
				if(x >= 9){
					System.out.println("No more room...");
					break;
				}
				int count = this.inventory.get(x).getCount();
				if(count >= 64){
					x ++;
				}else{
					if(this.inventory.get(x).getItem().equals(item.getItem()) || this.inventory.get(x).getItem().equals(Items.AIR)){
						item.setCount(count + 1);
						this.inventory.set(x, item);
						break;
					}else{
						x ++;
					}
				}


			}

			counter = 0;
			this.reach = rand.nextInt(144000) + 14400;
//			this.reach = rand.nextInt(300) + 1;

			this.markDirty();

		} else {
			counter += 1;

		}

	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {

		if (index == 3)
			return false;
		else if (index != 2)
			return true;
		return false;
	}

	public String getGuiID() {
		return "miccw:dust_collector";
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.counter;
		case 1:
			return this.reach;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.counter = value;
			break;
		case 1:
			this.reach = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 2;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}
}
