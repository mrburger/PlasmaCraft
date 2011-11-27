package net.minecraft.src;


import java.util.Random;

public class SMItemAcidVial extends Item
{	
    public SMItemAcidVial(int i, int j)
    {
        super(i);
        maxStackSize = 1;
        isFull = j;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        float f = 1.0F;
        float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
        float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
        double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)f;
        double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)f + 1.6200000000000001D) - (double)entityplayer.yOffset;
        double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)f;
        Vec3D vec3d = Vec3D.createVector(d, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f8 = f6;
        float f9 = f3 * f5;
        double d3 = 5D;
        Vec3D vec3d1 = vec3d.addVector((double)f7 * d3, (double)f8 * d3, (double)f9 * d3);
        MovingObjectPosition movingobjectposition = world.rayTraceBlocks_do(vec3d, vec3d1, isFull == 0);
        if(movingobjectposition == null)
        {
            return itemstack;
        }
        if(movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
        {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;
            if(!world.canMineBlock(entityplayer, i, j, k))
            {
                return itemstack;
            }
            if(isFull == 0)
            {
				int id = world.getBlockId(i, j, k);
                if(id == mod_PlasmaCraft.acidStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                	EntityPlayer entityplayer1 = ModLoader.getMinecraftInstance().thePlayer;
                    if (world.setBlockWithNotify(i, j, k, 0)){
                    	return new ItemStack(mod_PlasmaCraft.fullAcidVial);
                    }
                }
                else if(id == mod_PlasmaCraft.plutoniumStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                    world.setBlockWithNotify(i, j, k, 0);
                    return new ItemStack(mod_PlasmaCraft.plutoniumVial);
                }
                else if(id == mod_PlasmaCraft.radioniteStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                    world.setBlockWithNotify(i, j, k, 0);
                    return new ItemStack(mod_PlasmaCraft.radioniteVial);
                }
                else if(id == mod_PlasmaCraft.uraniumStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                    world.setBlockWithNotify(i, j, k, 0);
                    return new ItemStack(mod_PlasmaCraft.uraniumVial);
                }
                else if(id == mod_PlasmaCraft.neptuniumStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                    world.setBlockWithNotify(i, j, k, 0);
                    return new ItemStack(mod_PlasmaCraft.neptuniumVial);
                }
                else if(id == mod_PlasmaCraft.netherflowStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                    world.setBlockWithNotify(i, j, k, 0);
                    return new ItemStack(mod_PlasmaCraft.netherflowVial);
                }
                else if(id == mod_PlasmaCraft.obsidiumStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                    world.setBlockWithNotify(i, j, k, 0);
                    return new ItemStack(mod_PlasmaCraft.obsidiumVial);
                }
                else if(id == mod_PlasmaCraft.cryoniteStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                    world.setBlockWithNotify(i, j, k, 0);
                    return new ItemStack(mod_PlasmaCraft.cryoniteVial);
                }
                //else if(tile.causticID == 7 && world.getBlockMetadata(i, j, k) == 0)
                //{
                //    world.setBlockWithNotify(i, j, k, 0);
                //    return new ItemStack(mod_PlasmaCraft.milkVial);
                //}
            }
            else
            {
                if(movingobjectposition.sideHit == 0)
                {
                    j--;
                }
                if(movingobjectposition.sideHit == 1)
                {
					if(world.getBlockId(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) != Block.snow.blockID)
					{
                    	j++;
					}
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
                if(world.isAirBlock(i, j, k) || !world.getBlockMaterial(i, j, k).isSolid())
                {
					world.setBlockAndMetadataWithNotify(i, j, k, isFull, 0);
                    return new ItemStack(mod_PlasmaCraft.acidVial);
                }
            }
        }
        if(isFull == 0 && (movingobjectposition.entityHit instanceof SMEntityMutantCow))
        {
            return new ItemStack(mod_PlasmaCraft.fullAcidVial);
        }
        return itemstack;
    }

    private int isFull;
}