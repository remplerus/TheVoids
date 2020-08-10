package com.rempler.thevoids.client;

import com.mojang.blaze3d.platform.GlStateManager;

import com.rempler.thevoids.container.TheVoidsContainer;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author p455w0rd
 *
 */
@OnlyIn(Dist.CLIENT)
public class TheVoidsScreen extends ContainerScreen<TheVoidsContainer> {

	private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/crafting_table.png");

	public TheVoidsScreen(final TheVoidsContainer container, final PlayerInventory inventory, final ITextComponent text) {
		super(container, inventory, text);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
		font.drawString(I18n.format("block.thevoids.stone_crafting_table"), 28, 6, 4210752);
		font.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
		GlStateManager.blendColor(1.0F, 1.0F, 1.0F, 1.0F);
		assert minecraft != null;
		minecraft.getTextureManager().bindTexture(CRAFTING_TABLE_GUI_TEXTURES);
		final int i = guiLeft;
		final int j = (height - ySize) / 2;
		this.blit(i, j, 0, 0, xSize, ySize);
	}

	@Override
	public void render(final int mouseX, final int mouseY, final float partialTicks) {
		if (minecraft != null) {
			renderBackground();
			super.render(mouseX, mouseY, partialTicks);
			renderHoveredToolTip(mouseX, mouseY);
		}
	}

}