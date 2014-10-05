package com.malkierian.plasmacraft.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.malkierian.plasmacraft.core.ContainerPlasmaBench;
import com.malkierian.plasmacraft.core.PlasmaCraft;
import com.malkierian.plasmacraft.core.TilePlasmaBench;

public class GuiPlasmaBench extends GuiContainer
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(PlasmaCraft.MOD_ID, "textures/gui/plasmabench.png");

	private TilePlasmaBench bench;

	public GuiPlasmaBench(InventoryPlayer inventory, TilePlasmaBench tileplasmabench)
	{
		super(new ContainerPlasmaBench(inventory, tileplasmabench));
		bench = tileplasmabench;
		xSize = 176;
		ySize = 200;
	}

	protected void drawGuiContainerForegroundLayer()
	{
		fontRendererObj.drawString("Plasmificator", 60, 10, 32768 /*GL_ABGR_EXT*/);
		fontRendererObj.drawString("Queue", 10, 75, 32768 /*GL_ABGR_EXT*/);
		fontRendererObj.drawString("Inventory", 10, 106, 32768 /*GL_ABGR_EXT*/);
	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(TEXTURE);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
		if(bench.isBurning())
		{
			int j1 = bench.getBurnTimeRemainingScaled(80);
			if(j1 >= 60)
			{
				drawGradientRect(l + 78, i1 + 56 + (19 - (j1 - 60)), l + 78 + 2, i1 + 56 + (19 - (j1 - 60)) + (j1 - 60) + 1, -256, -256);
				drawGradientRect(l + 80, i1 + 74, l + 80 + 20, i1 + 74 + 2, -256, -256);
				drawGradientRect(l + 98, i1 + 54, l + 98 + 2, i1 + 54 + 20, -256, -256);
				drawGradientRect(l + 78, i1 + 54, l + 78 + 20, i1 + 54 + 2, -256, -256);
			}
			else if(j1 >= 40)
			{
				drawGradientRect(l + 80 + (19 - (j1 - 40)), i1 + 74, l + 80 + (19 - (j1 - 40)) + (j1 - 40) + 1, i1 + 74 + 2, -256, -256);
				drawGradientRect(l + 98, i1 + 54, l + 98 + 2, i1 + 54 + 20, -256, -256);
				drawGradientRect(l + 78, i1 + 54, l + 78 + 20, i1 + 54 + 2, -256, -256);
			}
			else if(j1 >= 20)
			{
				drawGradientRect(l + 98, i1 + 54, l + 98 + 2, i1 + 54 + (j1 - 20) + 1, -256, -256);
				drawGradientRect(l + 78, i1 + 54, l + 78 + 20, i1 + 54 + 2, -256, -256);
			}
			else
			{
				drawGradientRect(l + 78, i1 + 54, l + 78 + j1 + 1, i1 + 54 + 2, -256, -256);
			}
		}
		int k1 = bench.getCookProgressScaled(24);
		drawTexturedModalRect(l + 76, i1 + 24, 176, 14, k1 + 1, 16);
	}
}
