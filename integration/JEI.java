package com.rempler.thevoids.integration;

import com.rempler.thevoids.TheVoids;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.*;
import net.minecraft.util.ResourceLocation;
import com.rempler.thevoids.init.Constants;
import com.rempler.thevoids.init.ModObjects;
import com.rempler.thevoids.client.TheVoidsScreen;
import com.rempler.thevoids.container.TheVoidsContainer;

import javax.annotation.Nonnull;

/**
 * @author p455w0rd
 *
 */
@JeiPlugin
public class JEI implements IModPlugin {

	private static final ResourceLocation ID = new ResourceLocation(TheVoids.MODID, TheVoids.MODID);

	@Override
	public void registerRecipeTransferHandlers(final IRecipeTransferRegistration r) {
		r.addRecipeTransferHandler(TheVoidsContainer.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
	}

	@Override
	public void registerGuiHandlers(final IGuiHandlerRegistration r) {
		r.addRecipeClickArea(TheVoidsScreen.class, 88, 32, 28, 23, VanillaRecipeCategoryUid.CRAFTING);
	}

	@Override
	public void registerRecipeCatalysts(final IRecipeCatalystRegistration r) {
		r.addRecipeCatalyst(ModObjects.ITEM_BLOCK_STONE_WORKBENCH.asStack(), VanillaRecipeCategoryUid.CRAFTING);
	}

	@Nonnull
	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

}
