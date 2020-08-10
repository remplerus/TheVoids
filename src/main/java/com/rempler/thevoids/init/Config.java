package com.rempler.thevoids.init;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class Config {

	public static final ForgeConfigSpec CONFIG_SPEC;
	//private static final ModConfig CONFIG;

	public static BooleanValue INFINITE_USE;

	static {
		final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
		CONFIG_SPEC = specPair.getRight();
		//CONFIG = specPair.getLeft();
	}

	Config(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		INFINITE_USE = builder.define("infiniteUse", true);
		builder.pop();
	}

}
