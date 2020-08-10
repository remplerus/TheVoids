package com.rempler.thevoids.block;

import javax.annotation.Nullable;

import com.rempler.thevoids.TheVoids;
import com.rempler.thevoids.container.TheVoidsContainer;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * @author p455w0rd
 *
 */
@SuppressWarnings("deprecation")
public class TheVoidsBlock extends Block {

	protected static final ResourceLocation REG_NAME = new ResourceLocation(TheVoids.MODID, "stone_crafting_table");

	public TheVoidsBlock() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(5.0F).sound(SoundType.STONE));
		setRegistryName(REG_NAME);
	}

	public static ResourceLocation getRegName() {
		return REG_NAME;
	}

	@Override
	public ActionResultType onBlockActivated(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult rtr) {
		if (!world.isRemote) {
			final TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TheVoidsTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, new TheVoidsContainer.Provider((TheVoidsTileEntity) tile), tile.getPos());
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void harvestBlock(final World world, final PlayerEntity player, final BlockPos pos, final BlockState state, @Nullable final TileEntity te, @Nullable ItemStack stack) {
		player.addStat(Stats.BLOCK_MINED.get(this));
		player.addExhaustion(0.025F);
		stack = getItemBlockWithNBT(te);
		spawnAsEntity(world, pos, stack);
	}

	private ItemStack getItemBlockWithNBT(final TileEntity te) {
		final ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
		final CompoundNBT nbttagcompound = new CompoundNBT();
		if (te instanceof TheVoidsTileEntity && !((TheVoidsTileEntity) te).isEmpty()) {
			te.write(nbttagcompound);
			stack.setTagInfo("BlockEntityTag", nbttagcompound);
		}
		return stack;
	}

	@Override
	public boolean hasTileEntity(final BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		return new TheVoidsTileEntity();
	}

}
