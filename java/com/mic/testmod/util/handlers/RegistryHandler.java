package com.mic.testmod.util.handlers;

import java.util.List;

import com.mic.testmod.Main;
import com.mic.testmod.init.ModBlocks;
import com.mic.testmod.init.ModEnchantments;
import com.mic.testmod.init.ModItems;
import com.mic.testmod.util.IHasModel;
import com.mic.testmod.util.compat.oredict.OreDictionaryCompat;
import com.mic.testmod.world.gen.WorldGenCustomStructures;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(ModEnchantments.Enchantments.toArray(new Enchantment[0]));
	}

	@SubscribeEvent
	public void playerTickEvent(PlayerTickEvent event, EntityPlayer player) //CHOOSE THE EVENT THAT BEST SUITS YOUR NEEDS
	{
	    List<ItemStack> armor = (List<ItemStack>) player.getArmorInventoryList();
	    if(!armor.get(2).getItem().equals(ModItems.CHESTPLATE_GODLY)){
	    	System.out.println("no fly");
	    	player.capabilities.allowFlying = false;
	    	player.capabilities.isFlying = false;
	    	
	    }
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : ModItems.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
		for (Block block : ModBlocks.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}
		}
	}
	
	
	public static void preInitRegistries(){
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
	}
	public static void initRegistries(){
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		OreDictionaryCompat.registerOres();
	}
}
