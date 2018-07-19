package com.mic.testmod.armor;

import java.util.List;
import java.util.Random;

import com.mic.testmod.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class FlightArmor extends ArmorBase {

	Random rand;
	
	public FlightArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
		rand = new Random();
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack) {

		List<ItemStack> armor = (List<ItemStack>) player.getArmorInventoryList();

		Item boots = armor.get(0).getItem();
		Item legs = armor.get(1).getItem();
		Item chest = armor.get(2).getItem();
		Item helm = armor.get(3).getItem();

		if (helm.equals(ModItems.HELMET_GODLY) && legs.equals(ModItems.LEGGINGS_GODLY)
				&& chest.equals(ModItems.CHESTPLATE_GODLY) && boots.equals(ModItems.BOOTS_GODLY)) {
			if (!player.isCreative()) {
				player.capabilities.allowFlying = true;

			} else {
				player.capabilities.allowFlying = true;

			}

		} else {
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
		}


		player.world.spawnParticle(EnumParticleTypes.PORTAL,
				player.posX + (rand.nextDouble() - 0.5D) * (double) player.width,
				player.posY + rand.nextDouble() * (double) player.height - 0.25D,
				player.posZ + (rand.nextDouble() - 0.5D) * (double) player.width, (rand.nextDouble() - 0.5D) * 2.0D,
				-rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);

	}

}
