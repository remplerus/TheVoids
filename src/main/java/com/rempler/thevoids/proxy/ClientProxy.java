package com.rempler.thevoids.proxy;

import com.rempler.thevoids.client.TheVoidsScreen;
import com.rempler.thevoids.container.TheVoidsContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

/**
 * @author p455w0rd
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		ScreenManager.registerFactory(TheVoidsContainer.TYPE, TheVoidsScreen::new);
	}

	@Override
	public PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}

}
