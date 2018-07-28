package com.mic.testmod.blocks.machine.watersource;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.mic.testmod.blocks.BlockBase;
import com.mic.testmod.init.ModBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWaterSource extends BlockBase {
    

	public BlockWaterSource(String name) {
		super(name, Material.IRON);
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;	
	}

	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.WATERSOURCE);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ModBlocks.WATERSOURCE);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack itemstack = playerIn.getHeldItem(hand);

		if (itemstack.isEmpty()) {
			return true;
		} else {
			Item item = itemstack.getItem();

			if (item == Items.WATER_BUCKET) {
				if (!worldIn.isRemote) {
					if (!playerIn.capabilities.isCreativeMode) {
						playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
					}

					playerIn.addStat(StatList.CAULDRON_FILLED);
					worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS,
							1.0F, 1.0F);
				}

				return true;
			} else if (item == Items.BUCKET) {
				if (!worldIn.isRemote) {
					if (!playerIn.capabilities.isCreativeMode) {
						itemstack.shrink(1);

						if (itemstack.isEmpty()) {
							playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
						} else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
							playerIn.dropItem(new ItemStack(Items.WATER_BUCKET), false);
						}
					}

					playerIn.addStat(StatList.CAULDRON_USED);
					worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS,
							1.0F, 1.0F);
				}

				return true;
			} else if (item == Items.GLASS_BOTTLE) {
				if (!worldIn.isRemote) {
					if (!playerIn.capabilities.isCreativeMode) {
						ItemStack itemstack3 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
								PotionTypes.WATER);
						playerIn.addStat(StatList.CAULDRON_USED);
						itemstack.shrink(1);

						if (itemstack.isEmpty()) {
							playerIn.setHeldItem(hand, itemstack3);
						} else if (!playerIn.inventory.addItemStackToInventory(itemstack3)) {
							playerIn.dropItem(itemstack3, false);
						} else if (playerIn instanceof EntityPlayerMP) {
							((EntityPlayerMP) playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
						}
					}

					worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS,
							1.0F, 1.0F);
				}

				return true;
			} else if (item == Items.POTIONITEM && PotionUtils.getPotionFromItem(itemstack) == PotionTypes.WATER) {
				if (!worldIn.isRemote) {
					if (!playerIn.capabilities.isCreativeMode) {
						ItemStack itemstack2 = new ItemStack(Items.GLASS_BOTTLE);
						playerIn.addStat(StatList.CAULDRON_USED);
						playerIn.setHeldItem(hand, itemstack2);

						if (playerIn instanceof EntityPlayerMP) {
							((EntityPlayerMP) playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
						}
					}

					worldIn.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS,
							1.0F, 1.0F);
				}

				return true;
			} else {
				return false;
			}
		}

	}
}
