package com.mic.testmod.items.tools;

import com.google.common.collect.Sets;
import com.mic.testmod.Main;
import com.mic.testmod.init.ModItems;
import com.mic.testmod.util.IHasModel;
import com.mic.testmod.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.Set;

import javax.tools.Tool;

/**
 * Kinda stole a lot of this from SparksHammers so I'm gonna give a huge thanks
 * right here.
 * 
 * @author Milomaz1
 *
 */
public class ItemAOE extends ItemTool implements IHasModel {

	private static final Set<Block> PickaxeBlocks = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE,
			Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE,
			Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE,
			Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE,
			Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE,
			Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON,
			Blocks.STONE_PRESSURE_PLATE);
	private static final Set<Material> PickaxeMats = Sets.newHashSet(Material.ANVIL, Material.GLASS, Material.ICE,
			Material.IRON, Material.PACKED_ICE, Material.PISTON, Material.ROCK);

	private static final Set<Block> ShovelBlocks = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND,
			Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND,
			Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER);
	private static final Set<Material> ShovelMats = Sets.newHashSet(Material.GRASS, Material.GROUND, Material.SAND,
			Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY);

	/**
	 * Swaps between 1x1 and 3x3
	 */
	private boolean toolType = true;
	private boolean isExcavator;

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (playerIn.isSneaking()) {
			NBTTagCompound tags = stack.getTagCompound();
			if (tags == null) {
				tags = new NBTTagCompound();
				stack.setTagCompound(tags);
			}
			tags.setBoolean("hammer", !tags.getBoolean("hammer"));
			toolType = !toolType;
			playerIn.swingArm(handIn);

			if (!worldIn.isRemote) {
				if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("hammer")) {
					playerIn.sendMessage(new TextComponentString("Breaking 3x3 blocks..."));
				} else {
					playerIn.sendMessage(new TextComponentString("Breaking single blocks..."));
				}
			}
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	// This constructor is used when registering tools from the custom json file
	public ItemAOE() {
		this("titan_hammer", ModItems.MATERIAL_GOD, false, false);

	}

	public ItemAOE(String name, ToolMaterial material) {
		this(name, material, false, false);

	}

	public ItemAOE(String name, ToolMaterial material, boolean isExcavator, boolean isInfiniteUse) {
		// super(isExcavator ? 0f : 2f, isExcavator ? -3f : -3.2f, material,
		// isExcavator ? ShovelBlocks : PickaxeBlocks);

		super(material, isExcavator ? ShovelBlocks : PickaxeBlocks);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.isExcavator = isExcavator;

		setHarvestLevel(isExcavator ? "shovel" : "pickaxe", material.getHarvestLevel());

	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	// Overriding this just to make it public
	@Override
	public RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
		return super.rayTrace(worldIn, playerIn, useLiquids);
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		String tool = state.getBlock().getHarvestTool(state);
		if (state.getMaterial().isToolNotRequired() || tool == null)
			return true;
		return getHarvestLevel(stack, tool, null, state) >= state.getBlock().getHarvestLevel(state);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return isEffective(stack, state) ? efficiency : 1F;
	}

	public boolean isEffective(ItemStack stack, IBlockState state) {
		for (String type : getToolClasses(stack))
			if (state.getBlock().isToolEffective(type, state))
				return true;

		return isExcavator ? ShovelMats.contains(state.getMaterial()) || ShovelBlocks.contains(state.getBlock())
				: PickaxeMats.contains(state.getMaterial()) || PickaxeBlocks.contains(state.getBlock());
	}

	public static void getBreakArea(ItemStack hammerStack, BlockPos pos, EnumFacing sideHit, EntityPlayer player) {
		ItemAOE hammerItem = (ItemAOE) hammerStack.getItem();
		// Rotate if player is holding shift

		int mineWidth = 1;
		int mineHeight = 1;

		World worldIn = player.getEntityWorld();
		IBlockState state = worldIn.getBlockState(pos);
		ItemStack stack = hammerStack;
		EntityPlayer entityLiving = player;
		switch (sideHit) {

		case DOWN:
		case UP:

			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, -1, 0, -1);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, -1, 0, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, -1, 0, 1);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 1, 0, -1);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 1, 0, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 1, 0, 1);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 0, 0, 1);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 0, 0, -1);

			break;
		case NORTH:
		case SOUTH:

			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 0, 1, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 0, -1, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, -1, 0, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, -1, 1, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, -1, -1, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 1, 0, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 1, 1, 0);
			breakBlockIfEqual(stack, worldIn, pos, entityLiving, state, 1, -1, 0);

			break;
		case WEST:
		case EAST:

			breakBlockIfEqual(hammerStack, worldIn, pos, player, state, 0, 1, 0);
			breakBlockIfEqual(hammerStack, worldIn, pos, player, state, 0, -1, 0);
			breakBlockIfEqual(hammerStack, worldIn, pos, player, state, 0, 0, -1);
			breakBlockIfEqual(hammerStack, worldIn, pos, player, state, 0, 1, -1);
			breakBlockIfEqual(hammerStack, worldIn, pos, player, state, 0, -1, -1);
			breakBlockIfEqual(hammerStack, worldIn, pos, player, state, 0, 0, 1);
			breakBlockIfEqual(hammerStack, worldIn, pos, player, state, 0, 1, 1);
			breakBlockIfEqual(hammerStack, worldIn, pos, player, state, 0, -1, 1);

			break;
		}

	}

	public static void breakBlockIfEqual(ItemStack stack, World worldIn, BlockPos pos, EntityLivingBase entityLiving,
			IBlockState state, int x, int y, int z) {
		BlockPos pos2 = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);

		if (worldIn.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z)).getBlock()
				.getBlockHardness(worldIn.getBlockState(pos2), worldIn, pos2) <= worldIn.getBlockState(pos).getBlock()
						.getBlockHardness(state, worldIn, pos) + 0.2f) {

			worldIn.getBlockState(pos2).getBlock().harvestBlock(worldIn, (EntityPlayer) entityLiving, pos2,
					worldIn.getBlockState(pos2), null, stack);
			worldIn.setBlockToAir(pos2);

		}
	}

	// <<<< Also made with some help from Tinkers Construct >>>>
	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		// Block being mined
		if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("hammer")) {
			RayTraceResult ray = rayTrace(player.world, player, false);
			if (ray == null)
				return super.onBlockStartBreak(stack, pos, player);

			getBreakArea(stack, pos, ray.sideHit, player);
		} else {

		}
		return super.onBlockStartBreak(stack, pos, player);
	}

}