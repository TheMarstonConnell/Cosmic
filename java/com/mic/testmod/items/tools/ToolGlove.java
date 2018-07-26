package com.mic.testmod.items.tools;

import com.mic.testmod.Main;
import com.mic.testmod.init.ModItems;
import com.mic.testmod.util.IHasModel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ToolGlove extends ItemSword implements IHasModel {

	public ToolGlove(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modTab);

		ModItems.ITEMS.add(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if (!worldIn.isRemote) {

			// Minecraft minecraft = Minecraft.getMinecraft();

			// EntityPlayerSP player = minecraft.thePlayer;

			// par2World.spawnEntityInWorld(new
			// EntityLargeFireball(par2World, par3EntityPlayer, vec3.xCoord,
			// vec3.yCoord, vec3.zCoord));

			Vec3d vec3 = playerIn.getLookVec();

			EntityLargeFireball entitylargefireball = new EntityLargeFireball(worldIn, playerIn, vec3.x, vec3.y,
					vec3.z);

			entitylargefireball.posY += 1;

			entitylargefireball.accelerationX = vec3.x;

			entitylargefireball.accelerationY = vec3.y;

			entitylargefireball.accelerationZ = vec3.z;

			worldIn.spawnEntity(entitylargefireball);

		}

		return super.onItemRightClick(worldIn, playerIn, handIn);

	}

}
