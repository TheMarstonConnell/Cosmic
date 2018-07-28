package com.mic.testmod.util.handlers;

import com.mic.testmod.blocks.machine.bedrock.ContainerBedrockMiner;
import com.mic.testmod.blocks.machine.bedrock.GuiBedrockMiner;
import com.mic.testmod.blocks.machine.bedrock.TileEntityBedrockMiner;
import com.mic.testmod.blocks.machine.compressor.ContainerCompressor;
import com.mic.testmod.blocks.machine.compressor.GuiCompressor;
import com.mic.testmod.blocks.machine.compressor.TileEntityCompressor;
import com.mic.testmod.blocks.machine.duster.ContainerDustCollector;
import com.mic.testmod.blocks.machine.duster.GuiDustCollector;
import com.mic.testmod.blocks.machine.duster.TileEntityDustCollector;
import com.mic.testmod.blocks.machine.enscriber.ContainerEnscriber;
import com.mic.testmod.blocks.machine.enscriber.GuiEnscriber;
import com.mic.testmod.blocks.machine.enscriber.TileEntityEnscriber;
import com.mic.testmod.blocks.machine.forge.ContainerCosmicForge;
import com.mic.testmod.blocks.machine.forge.GuiCosmicForge;
import com.mic.testmod.blocks.machine.forge.TileEntityCosmicForge;
import com.mic.testmod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_FORGE) return new ContainerCosmicForge(player.inventory, (TileEntityCosmicForge)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_BEDROCK) return new ContainerBedrockMiner(player.inventory, (TileEntityBedrockMiner)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_DUST) return new ContainerDustCollector(player.inventory, (TileEntityDustCollector)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_ENSCRIBER) return new ContainerEnscriber(player.inventory, (TileEntityEnscriber)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_COMPRESSOR) return new ContainerCompressor(player.inventory, (TileEntityCompressor)world.getTileEntity(new BlockPos(x,y,z)));

		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_FORGE) return new GuiCosmicForge(player.inventory, (TileEntityCosmicForge)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_BEDROCK) return new GuiBedrockMiner(player.inventory, (TileEntityBedrockMiner)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_DUST) return new GuiDustCollector(player.inventory, (TileEntityDustCollector)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_ENSCRIBER) return new GuiEnscriber(player.inventory, (TileEntityEnscriber)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == Reference.GUI_COMPRESSOR) return new GuiCompressor(player.inventory, (TileEntityCompressor)world.getTileEntity(new BlockPos(x,y,z)));

		
		return null;
	}

}
