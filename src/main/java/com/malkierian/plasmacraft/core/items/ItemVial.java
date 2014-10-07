package com.malkierian.plasmacraft.core.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.core.PlasmaCraft;
import com.malkierian.plasmacraft.core.entities.EntityMutantCow;

public class ItemVial extends ItemPlasma
{
	private Block isFull;

	public ItemVial(Block block)
	{
		super();
		maxStackSize = 8;
		isFull = block;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		float f = 1.0F;
		float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
		float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
		double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)f;
		double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)f + 1.6200000000000001D) - (double)entityplayer.yOffset;
		double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f8 = f6;
		float f9 = f3 * f5;
		double d3 = 5D;
		ItemStack returnStack = null;
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f8 * d3, (double)f9 * d3);
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, vec31, isFull == Blocks.air);
		if(movingobjectposition == null)
		{
			return itemstack;
		}
		if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
		{
			int i = movingobjectposition.blockX;
			int j = movingobjectposition.blockY;
			int k = movingobjectposition.blockZ;
			if(!world.canMineBlock(entityplayer, i, j, k))
			{
				return itemstack;
			}
			if(isFull == Blocks.air)
			{
				Block l = world.getBlock(i, j, k);
				if(l == PlasmaCraft.acidBlock && world.getBlockMetadata(i, j, k) == 0)
				{
					if(world.setBlockToAir(i, j, k))
					{
						returnStack = new ItemStack(PlasmaCraft.acidVial);
					}
				}
				else
				{
					if(l == PlasmaCraft.plutoniumBlock && world.getBlockMetadata(i, j, k) == 0)
					{
						world.setBlockToAir(i, j, k);
						returnStack = new ItemStack(PlasmaCraft.plutoniumVial);
					}
					if(l == PlasmaCraft.radioniteBlock && world.getBlockMetadata(i, j, k) == 0)
					{
						world.setBlockToAir(i, j, k);
						returnStack = new ItemStack(PlasmaCraft.radioniteVial);
					}
					if(l == PlasmaCraft.uraniumBlock && world.getBlockMetadata(i, j, k) == 0)
					{
						world.setBlockToAir(i, j, k);
						returnStack = new ItemStack(PlasmaCraft.uraniumVial);
					}
					if(l == PlasmaCraft.neptuniumBlock && world.getBlockMetadata(i, j, k) == 0)
					{
						world.setBlockToAir(i, j, k);
						returnStack = new ItemStack(PlasmaCraft.neptuniumVial);
					}
					if(l == PlasmaCraft.netherflowBlock && world.getBlockMetadata(i, j, k) == 0)
					{
						world.setBlockToAir(i, j, k);
						returnStack = new ItemStack(PlasmaCraft.netherflowVial);
					}
					if(l == PlasmaCraft.obsidiumBlock && world.getBlockMetadata(i, j, k) == 0)
					{
						world.setBlockToAir(i, j, k);
						returnStack = new ItemStack(PlasmaCraft.obsidiumVial);
					}
					if(l == PlasmaCraft.cryoniteBlock && world.getBlockMetadata(i, j, k) == 0)
					{
						world.setBlockToAir(i, j, k);
						returnStack = new ItemStack(PlasmaCraft.cryoniteVial);
					}
				}
				if(returnStack != null)
				{
					if(!entityplayer.capabilities.isCreativeMode)
						itemstack.stackSize--;
					entityplayer.inventory.addItemStackToInventory(returnStack);
					return itemstack;
				}
			} else
			{
				if(movingobjectposition.sideHit == 0)
				{
					j--;
				}
				if(movingobjectposition.sideHit == 1 && world.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) != Blocks.snow)
				{
					j++;
				}
				if(movingobjectposition.sideHit == 2)
				{
					k--;
				}
				if(movingobjectposition.sideHit == 3)
				{
					k++;
				}
				if(movingobjectposition.sideHit == 4)
				{
					i--;
				}
				if(movingobjectposition.sideHit == 5)
				{
					i++;
				}
				if(world.isAirBlock(i, j, k) || !world.getBlock(i, j, k).getMaterial().isSolid())
				{
					world.setBlock(i, j, k, isFull);
					if(!entityplayer.capabilities.isCreativeMode)
					{
						itemstack.stackSize--;
						entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.causticVial));
					}
					return itemstack;
				}
			}
		}
		if(isFull == Blocks.air && (movingobjectposition.entityHit instanceof EntityMutantCow))
		{
			if(!entityplayer.capabilities.isCreativeMode)
				itemstack.stackSize--;
			entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.acidVial));
			return itemstack;
		} else
		{
			return itemstack;
		}
	}
}
