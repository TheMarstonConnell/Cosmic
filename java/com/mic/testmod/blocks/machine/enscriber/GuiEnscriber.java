package com.mic.testmod.blocks.machine.enscriber;

import com.mic.testmod.util.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiEnscriber extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(
			Reference.MOD_ID + ":textures/gui/bedrock_miner.png");
	private final InventoryPlayer player;
	private final TileEntityEnscriber tileentity;

	public GuiEnscriber(InventoryPlayer player, TileEntityEnscriber tileentity) {
		super(new ContainerEnscriber(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		
		

	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButton(1, this.width / 2 + 10, this.height / 2, 60, 20, "Enscribe"));

	}

	@Override
	protected void actionPerformed(GuiButton b) {
		// If the button id is different, or you have more buttons, create
		// another if block for that too!
		if (b.id == 1) {
			
//			ClientPacket.createGuiPacket("gui");
			
			ItemStack e = new ItemStack(Items.DIAMOND);
			e.setCount(1);
			
			
			this.tileentity.setInventorySlotContents(0, e);
			this.tileentity.markDirty();
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 8,
				4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 6, this.ySize - 96 + 2,
				4210752);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

}
