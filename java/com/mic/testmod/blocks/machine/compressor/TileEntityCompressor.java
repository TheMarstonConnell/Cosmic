package com.mic.testmod.blocks.machine.compressor;

import net.minecraft.tileentity.TileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCompressor extends TileEntity implements IInventory, ITickable {
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack> withSize(4, ItemStack.EMPTY);
	private String customName;

	private int burnTime = 100;
	private int currentBurnTime = 100;
	private int cookTime;
	private int totalCookTime;

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.compressor";
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
			this.totalCookTime = this.getCookTime(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack> withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.burnTime = 100;
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = 100;

		if (compound.hasKey("CustomName", 8))
			this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short) this.burnTime);
		compound.setInteger("CookTime", (short) this.cookTime);
		compound.setInteger("CookTimeTotal", (short) this.totalCookTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);

		if (this.hasCustomName())
			compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isBurning() {
		return this.cookTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		return inventory.getField(1) > 0;
	}

	public void update() {
//		System.out.println("Update");
		boolean flag = this.isBurning();
		boolean flag1 = false;

		// if(this.isBurning()) --this.burnTime;

		if (!this.world.isRemote) {
			
			

			//				System.out.println("It's burning");
			if (this.canSmelt()) {
				++this.cookTime;
				if(this.cookTime != 0 && cookTime % 60 == 0){
		            this.getWorld().playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
				}
				if (this.cookTime == this.totalCookTime) {
					this.cookTime = 0;
					this.totalCookTime = this.getCookTime((ItemStack) this.inventory.get(0));
					this.smeltItem();
					flag1 = true;
				}
			} else
				this.cookTime = 0;
			if (cookTime > 0) {
				flag1 = true;
				BlockCompressor.setState(true, this.world, this.pos);
			}else{
				BlockCompressor.setState(false, this.world, this.pos);
			}
		}
		if (flag1)
			this.markDirty();
	}

	public int getCookTime(ItemStack input1) {
		return 600;
	}

	private boolean canSmelt() {
		if (((ItemStack) this.inventory.get(0)).isEmpty())
			return false;
		else {
			ItemStack result = CompressorRecipes.getInstance().getCompressorResult((ItemStack) this.inventory.get(0));
			if (result.isEmpty())
				return false;
			else {
				ItemStack output = (ItemStack) this.inventory.get(1);
				if (output.isEmpty())
					return true;
				if (!output.isItemEqual(result))
					return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack input1 = (ItemStack) this.inventory.get(0);
			ItemStack result = CompressorRecipes.getInstance().getCompressorResult(input1);
			ItemStack output = (ItemStack) this.inventory.get(1);

			if (output.isEmpty())
				this.inventory.set(1, result.copy());
			else if (output.getItem() == result.getItem())
				output.grow(result.getCount());

			input1.shrink(1);
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

		if (index == 1)
			return false;
		return true;

	}

	public String getGuiID() {
		return "miccw:compressor";
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}
}
