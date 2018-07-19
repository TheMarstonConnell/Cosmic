package com.mic.testmod.items.tools;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;

public class ToolWarAxe extends ToolAxe {

	public ToolWarAxe(String name, ToolMaterial material) {
		super(name, material);
		this.attackDamage = 88.0f;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

		attacker.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1, 1, true, false));

		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
