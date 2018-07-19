package com.mic.testmod;

import com.mic.testmod.client.ModTab;
import com.mic.testmod.proxy.CommonProxy;
import com.mic.testmod.util.*;
import com.mic.testmod.util.handlers.RegistryHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final ModTab modTab = new ModTab();


	
	//Event Handlers
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		RegistryHandler.preInitRegistries();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event){
		RegistryHandler.initRegistries();
		
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event){
		
	}
	
	
	
	
}
