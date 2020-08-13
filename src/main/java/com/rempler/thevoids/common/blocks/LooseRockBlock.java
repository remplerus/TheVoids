/*
 * Part of the No Tree Punching mod by AlcatrazEscapee.
 * Work under copyright. See the project LICENSE.md for details.
 */

package com.alcatrazescapee.notreepunching.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class LooseRockBlock extends Block
{
    public static final VoxelShape SHAPE = makeCuboidShape(6, 0, 6, 10, 1, 10);

    public LooseRockBlock()
    {
        super(Properties.create(Material.EARTH).sound(SoundType.STONE).hardnessAndResistance(0.15f).doesNotBlockMovement().notSolid());
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
    {
        if (!state.isValidPosition(worldIn, pos) && !worldIn.isRemote)
        {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockState stateUnder = worldIn.getBlockState(pos.down());
        return stateUnder.isSolidSide(worldIn, pos.down(), Direction.UP);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        ItemStack stack = getPickBlock(state, hit, worldIn, pos, player);
        ItemHandlerHelper.giveItemToPlayer(player, stack);
        worldIn.removeBlock(pos, false);
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canSpawnInBlock()
    {
        return true;
    }
}
