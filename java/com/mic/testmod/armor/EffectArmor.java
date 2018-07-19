package com.mic.testmod.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EffectArmor extends ArmorBase{

	private final PotionEffect potionEffect;


	public EffectArmor(String name, ArmorMaterial materialIn, PotionEffect potionEffect, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
		this.potionEffect = potionEffect;

	}

	@Override
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
		if (!player.isPotionActive(potionEffect.getPotion())) { // If the Potion isn't currently active,
			player.addPotionEffect(new PotionEffect(potionEffect)); // Apply a copy of the PotionEffect to the player
		}
	}
	
	
}
