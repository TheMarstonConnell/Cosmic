package com.mic.testmod.init;

import java.util.ArrayList;
import java.util.List;

import com.mic.testmod.armor.ArmorBase;
import com.mic.testmod.armor.EffectArmor;
import com.mic.testmod.armor.FlightArmor;
import com.mic.testmod.items.ClickToKillItem;
import com.mic.testmod.items.ItemBase;
import com.mic.testmod.items.ItemShiny;
import com.mic.testmod.items.tools.ItemAOE;
import com.mic.testmod.items.tools.ToolAxe;
import com.mic.testmod.items.tools.ToolGlove;
import com.mic.testmod.items.tools.ToolHammer;
import com.mic.testmod.items.tools.ToolHoe;
import com.mic.testmod.items.tools.ToolPickaxe;
import com.mic.testmod.items.tools.ToolSpade;
import com.mic.testmod.items.tools.ToolSplitter;
import com.mic.testmod.items.tools.ToolSword;
import com.mic.testmod.items.tools.ToolWarAxe;
import com.mic.testmod.util.Reference;

import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	//Materials 
	public static final ToolMaterial MATERIAL_METEOR = EnumHelper.addToolMaterial("material_meteor", 4, 1600, 9.0f, 4.0f, 8);
	public static final ToolMaterial MATERIAL_DSTEEL = EnumHelper.addToolMaterial("material_dense_steel", 6, 3000, 12.0f, 6.0f, 9);
	public static final ToolMaterial MATERIAL_GOD = EnumHelper.addToolMaterial("material_god", 8, 4200, 60.0f, 30.0f, 5);
	public static final ToolMaterial MATERIAL_COMET = EnumHelper.addToolMaterial("material_comet", 4, 1200, 15.0f, 5.0f, 10);
	public static final ArmorMaterial ARMOR_METEOR = EnumHelper.addArmorMaterial("armor_meteor", Reference.MOD_ID + ":meteor", 16, new int[]{4, 6, 9, 4}, 13, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.5f);
	public static final ArmorMaterial ARMOR_DSTEEl = EnumHelper.addArmorMaterial("armor_dense_steel", Reference.MOD_ID + ":dense_steel", 17, new int[]{6, 7, 11, 6}, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.0f);
	public static final ArmorMaterial ARMOR_GOD = EnumHelper.addArmorMaterial("armor_god", Reference.MOD_ID + ":god", 21, new int[]{8, 11, 15, 8}, 14, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.5f);
	public static final ArmorMaterial ARMOR_COMET = EnumHelper.addArmorMaterial("armor_comet", Reference.MOD_ID + ":comet", 15, new int[]{4, 5, 6, 4}, 14, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f);


	//Items
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	
	public static final Item METEOR_CHUNK = new ItemBase("meteorchunk");
	public static final Item METEOR_INGOT = new ItemBase("meteor_ingot");
	public static final Item DENSE_ROCK = new ItemBase("dense_rock");
	public static final Item DENSE_STEEL_INGOT = new ItemBase("dense_steel_ingot");
	public static final Item COMET_TAIL_DUST = new ItemBase("comet_tail_dust");
	public static final Item COMET_TAIL_INGOT = new ItemBase("comet_tail_ingot");
	public static final Item STONE_GEAR = new ItemBase("stone_gear");
	public static final Item PLACE_HELM = new ItemBase("place_helmet");
	public static final Item PLACE_CHEST = new ItemBase("place_chestplate");
	public static final Item PLACE_LEGS = new ItemBase("place_leggings");
	public static final Item PLACE_BOOTS = new ItemBase("place_boots");
	public static final Item COSMIC_GEMSTONE = new ItemBase("cosmic_gemstone");
	public static final Item IRON_ROD = new ItemBase("iron_rod");
	public static final Item PLANET_CRUST = new ItemBase("planet_crust");
	public static final Item PLANET_CORE = new ItemBase("planet_core");
	public static final Item HEART_GEM = new ItemBase("heart_gem");
	public static final Item EMPTY_HEART_GEM = new ClickToKillItem("empty_heart_gem", HEART_GEM);
	public static final Item COMPRESSED_GOLD = new ItemBase("compressed_gold");
	public static final Item STURDY_GOLD_INGOT = new ItemBase("sturdy_gold_ingot");
	
	//Tools
	public static final ItemSword METEOR_SWORD = new ToolSword("meteor_sword", MATERIAL_METEOR);
	public static final ItemPickaxe METEOR_PICKAXE = new ToolPickaxe("meteor_pickaxe", MATERIAL_METEOR);
	public static final ItemAxe METEOR_AXE = new ToolAxe("meteor_axe", MATERIAL_METEOR);
	public static final ItemSpade METEOR_SHOVEL = new ToolSpade("meteor_shovel", MATERIAL_METEOR);
	public static final ItemHoe METEOR_HOE = new ToolHoe("meteor_hoe", MATERIAL_METEOR);
	
	
	public static final ItemAOE TITAN_HAMMER = new ToolHammer("titan_hammer", MATERIAL_GOD);
	public static final Item HORIZON_AXE = new ToolWarAxe("horizon_breaker", MATERIAL_GOD);
	public static final ItemAOE COLOSSAL_SPLITTER = new  ToolSplitter("colossal_splitter", MATERIAL_GOD);
	public static final ItemSword COSMIC_GLOVE = new ToolGlove("glove", MATERIAL_GOD);
	

	public static final ItemSword DSTEEL_SWORD = new ToolSword("dense_steel_sword", MATERIAL_DSTEEL);
	public static final ItemPickaxe DSTEEL_PICKAXE = new ToolPickaxe("dense_steel_pickaxe", MATERIAL_DSTEEL);
	public static final ItemAxe DSTEEL_AXE = new ToolAxe("dense_steel_axe", MATERIAL_DSTEEL);
	public static final ItemSpade DSTEEL_SHOVEL = new ToolSpade("dense_steel_shovel", MATERIAL_DSTEEL);
	public static final ItemHoe DSTEEL_HOE = new ToolHoe("dense_steel_hoe", MATERIAL_DSTEEL);

	public static final ItemSword COMET_SWORD = new ToolSword("comet_sword", MATERIAL_COMET);
	public static final ItemPickaxe COMET_PICKAXE = new ToolPickaxe("comet_pickaxe", MATERIAL_COMET);
	public static final ItemAxe COMET_AXE = new ToolAxe("comet_axe", MATERIAL_COMET);
	public static final ItemSpade COMET_SHOVEL = new ToolSpade("comet_shovel", MATERIAL_COMET);
	public static final ItemHoe COMET_HOE = new ToolHoe("comet_hoe", MATERIAL_COMET);

	//Armor
	public static final Item HELMET_METEOR = new ArmorBase("helmet_meteor", ARMOR_METEOR, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHESTPLATE_METEOR = new ArmorBase("chestplate_meteor", ARMOR_METEOR, 1, EntityEquipmentSlot.CHEST);
	public static final Item LEGGINGS_METEOR = new ArmorBase("leggings_meteor", ARMOR_METEOR, 2, EntityEquipmentSlot.LEGS);
	public static final Item BOOTS_METEOR = new ArmorBase("boots_meteor", ARMOR_METEOR, 1, EntityEquipmentSlot.FEET);

	static PotionEffect fireRes = new PotionEffect(MobEffects.FIRE_RESISTANCE, 1, 1, false, false);
	public static final Item HELMET_DSTEEL = new EffectArmor("helmet_dense_steel", ARMOR_DSTEEl, fireRes, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHESTPLATE_DSTEEL = new EffectArmor("chestplate_dense_steel", ARMOR_DSTEEl, fireRes, 1, EntityEquipmentSlot.CHEST);
	public static final Item LEGGINGS_DSTEEL = new EffectArmor("leggings_dense_steel", ARMOR_DSTEEl, fireRes, 2, EntityEquipmentSlot.LEGS);
	public static final Item BOOTS_DSTEEL = new EffectArmor("boots_dense_steel", ARMOR_DSTEEl, fireRes, 1, EntityEquipmentSlot.FEET);

	static PotionEffect speed = new PotionEffect(MobEffects.SPEED, 1, 1, false, false);
	public static final Item HELMET_COMET = new EffectArmor("helmet_comet", ARMOR_COMET, speed, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHESTPLATE_COMET = new EffectArmor("chestplate_comet", ARMOR_COMET, speed, 1, EntityEquipmentSlot.CHEST);
	public static final Item LEGGINGS_COMET = new EffectArmor("leggings_comet", ARMOR_COMET, speed, 2, EntityEquipmentSlot.LEGS);
	public static final Item BOOTS_COMET = new EffectArmor("boots_comet", ARMOR_COMET, speed, 1, EntityEquipmentSlot.FEET);

	public static final Item HELMET_GODLY = new FlightArmor("helmet_god", ARMOR_GOD, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHESTPLATE_GODLY = new FlightArmor("chestplate_god", ARMOR_GOD, 1, EntityEquipmentSlot.CHEST);
	public static final Item LEGGINGS_GODLY = new FlightArmor("leggings_god", ARMOR_GOD, 2, EntityEquipmentSlot.LEGS);
	public static final Item BOOTS_GODLY = new FlightArmor("boots_god", ARMOR_GOD, 1, EntityEquipmentSlot.FEET);


}
