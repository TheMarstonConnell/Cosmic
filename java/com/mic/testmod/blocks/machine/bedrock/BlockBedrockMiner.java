package com.mic.testmod.blocks.machine.bedrock;

import java.util.Random;

import com.mic.testmod.Main;
import com.mic.testmod.blocks.BlockBase;
import com.mic.testmod.blocks.HardBlock;
import com.mic.testmod.init.ModBlocks;
import com.mic.testmod.util.Reference;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBedrockMiner extends HardBlock implements ITileEntityProvider {

	public BlockBedrockMiner(String name) {
		super(name, Material.IRON, 2.0f, 0, 0);

		this.setSoundType(SoundType.GLASS);		

	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(ModBlocks.BEDROCK_MINER);
	}
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state){
		return new ItemStack(ModBlocks.BEDROCK_MINER);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand and, EnumFacing facing, float hitX, float hitY, float hitZ){


		if(!worldIn.isRemote){
			playerIn.openGui(Main.instance, Reference.GUI_BEDROCK, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;

	}


	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state){

	}

	public static void setState(boolean active, World worldIn, BlockPos pos){
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);



		if(tileentity != null){
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}



	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBedrockMiner();
	}



	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityBedrockMiner tileentity = (TileEntityBedrockMiner)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
		return EnumBlockRenderType.MODEL;
	}



}
