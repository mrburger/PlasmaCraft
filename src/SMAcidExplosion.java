package net.minecraft.src;


import java.util.*;

public class SMAcidExplosion
{
    public SMAcidExplosion(World world, Entity entity, double d, double d1, double d2, float f)
    {
        isFlaming = false;
        destroyedBlockPositions = new HashSet();
        ExplosionRNG = new Random();
        worldObj = world;
        exploder = entity;
        radius = f;
        posX = d;
        posY = d1;
        posZ = d2;
    }

    public void doExplosionA()
    {
        float f = radius;
        int i = 16;
        for(int j = 0; j < i; j++)
        {
            for(int l = 0; l < i; l++)
            {
label0:
                for(int j1 = 0; j1 < i; j1++)
                {
                    if(j != 0 && j != i - 1 && l != 0 && l != i - 1 && j1 != 0 && j1 != i - 1)
                    {
                        continue;
                    }
                    double d = ((float)j / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d1 = ((float)l / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d2 = ((float)j1 / ((float)i - 1.0F)) * 2.0F - 1.0F;
                    double d3 = Math.sqrt(d * d + d1 * d1 + d2 * d2);
                    d /= d3;
                    d1 /= d3;
                    d2 /= d3;
                    float f1 = radius * 0.8f;
                    double d5 = posX;
                    double d7 = posY;
                    double d9 = posZ;
                    float f2 = 0.3F;
                    do
                    {
                        if(f1 <= 0.0F)
                        {
                            continue label0;
                        }
                        int j4 = MathHelper.floor_double(d5);
                        int k4 = MathHelper.floor_double(d7);
                        int l4 = MathHelper.floor_double(d9);
                        int i5 = worldObj.getBlockId(j4, k4, l4);
                        if(i5 > 0)
                        {
                            f1 -= (Block.blocksList[i5].getExplosionResistance(exploder) + 0.3F) * f2;
                        }
                        if(f1 > 0.0F)
                        {
                            destroyedBlockPositions.add(new ChunkPosition(j4, k4, l4));
                        }
                        d5 += d * (double)f2;
                        d7 += d1 * (double)f2;
                        d9 += d2 * (double)f2;
                        f1 -= f2 * 0.75F;
                    } while(true);
                }

            }

        }

        radius *= 2.0F;
        int k = MathHelper.floor_double(posX - (double)radius - 1.0D);
        int i1 = MathHelper.floor_double(posX + (double)radius + 1.0D);
        int k1 = MathHelper.floor_double(posY - (double)radius - 1.0D);
        int l1 = MathHelper.floor_double(posY + (double)radius + 1.0D);
        int i2 = MathHelper.floor_double(posZ - (double)radius - 1.0D);
        int j2 = MathHelper.floor_double(posZ + (double)radius + 1.0D);
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(exploder, AxisAlignedBB.getBoundingBoxFromPool(k, k1, i2, i1, l1, j2));
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        for(int k2 = 0; k2 < list.size(); k2++)
        {
            Entity entity = (Entity)list.get(k2);
            double d4 = entity.getDistance(posX, posY, posZ) / (double)radius;
            if(d4 <= 1.0D)
            {
                double d6 = entity.posX - posX;
                double d8 = entity.posY - posY;
                double d10 = entity.posZ - posZ;
                double d11 = MathHelper.sqrt_double(d6 * d6 + d8 * d8 + d10 * d10);
                d6 /= d11;
                d8 /= d11;
                d10 /= d11;
                double d12 = worldObj.func_675_a(vec3d, entity.boundingBox);
                double d13 = (1.0D - d4) * d12;
                entity.attackEntityFrom(DamageSource.explosion, (int)(((d13 * d13 + d13) / 2D) * 8D * (double)radius + 1.0D));
                double d14 = d13;
                entity.motionX += d6 * d14;
                entity.motionY += d8 * d14;
                entity.motionZ += d10 * d14;
            }
        }

        radius = f;
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(destroyedBlockPositions);
        if(isFlaming)
        {
            for(int l2 = arraylist.size() - 1; l2 >= 0; l2--)
            {
                ChunkPosition chunkposition = (ChunkPosition)arraylist.get(l2);
                int i3 = chunkposition.x;
                int j3 = chunkposition.y;
                int k3 = chunkposition.z;
                int l3 = worldObj.getBlockId(i3, j3, k3);
                int i4 = worldObj.getBlockId(i3, j3 - 1, k3);
                if(l3 == 0 && Block.opaqueCubeLookup[i4] && ExplosionRNG.nextInt(3) == 0)
                {
                    worldObj.setBlockWithNotify(i3, j3, k3, Block.fire.blockID);
                }
            }

        }
    }

	private boolean isFlowable(int id)
	{
		return (id == 0 || id == Block.doorWood.blockID || id == Block.doorSteel.blockID || id == Block.signPost.blockID || id == Block.ladder.blockID || id == Block.reed.blockID || id == Block.redstoneWire.blockID);
	}

    public void doExplosionB()
    {
        worldObj.playSoundEffect(posX, posY, posZ, "random.explode", 4F, (1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(destroyedBlockPositions);
        for(int i = arraylist.size() - 1; i >= 0; i--)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(i);
            int j = chunkposition.x;
            int k = chunkposition.y;
            int l = chunkposition.z;
            int i1 = worldObj.getBlockId(j, k, l);
            for(int j1 = 0; j1 < 1; j1++)
            {
                double d = (float)j + worldObj.rand.nextFloat();
                double d1 = (float)k + worldObj.rand.nextFloat();
                double d2 = (float)l + worldObj.rand.nextFloat();
                double d3 = d - posX;
                double d4 = d1 - posY;
                double d5 = d2 - posZ;
                double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
                d3 /= d6;
                d4 /= d6;
                d5 /= d6;
                double d7 = 0.5D / (d6 / (double)radius + 0.10000000000000001D);
                d7 *= worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3F;
                d3 *= d7;
                d4 *= d7;
                d5 *= d7;
                worldObj.spawnParticle("explode", (d + posX * 1.0D) / 2D, (d1 + posY * 1.0D) / 2D, (d2 + posZ * 1.0D) / 2D, d3, d4, d5);
                worldObj.spawnParticle("smoke", d, d1, d2, d3, d4, d5);
            }

            if(i1 > 0)
            {
                Block.blocksList[i1].dropBlockAsItemWithChance(worldObj, j, k, l, worldObj.getBlockMetadata(j, k, l), 0.3F, i1);
                worldObj.setBlockWithNotify(j, k, l, 0);
                Block.blocksList[i1].onBlockDestroyedByExplosion(worldObj, j, k, l);
            }
        }

		// Convert space to acid
        for(int i = arraylist.size() - 1; i >= 0; i--)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(i);
            int j = chunkposition.x;
            int k = chunkposition.y;
            int l = chunkposition.z;
            int i1 = worldObj.getBlockId(j, k, l);
			double d4 = (double)k - posY;
			if(d4 < -2.0f && i1 == 0)
			{
                worldObj.setBlockWithNotify(j, k, l, mod_PlasmaCraft.acidMoving.blockID);
			}
        }

        // Fill in blocks around the acid
        for(int j = (int)(posX - (radius + 1.0f)); j <= (int)(posX + (radius + 1.0f)); j++)
        {
			for(int k = (int)(posY - (radius + 1.0f)); k <= (int)(posY + (radius + 1.0f)); k++)
			{
				for(int l = (int)(posZ - (radius + 1.0f)); l <= (int)(posZ + (radius + 1.0f)); l++)
				{
					int i1 = worldObj.getBlockId(j, k, l);
					if(i1 == mod_PlasmaCraft.acidMoving.blockID || i1 == mod_PlasmaCraft.acidStill.blockID)
					{
						boolean east = isFlowable(worldObj.getBlockId(j - 1, k, l));
						boolean west = isFlowable(worldObj.getBlockId(j + 1, k, l));
						boolean north = isFlowable(worldObj.getBlockId(j, k, l + 1));
						boolean south = isFlowable(worldObj.getBlockId(j, k, l - 1));
						boolean down = isFlowable(worldObj.getBlockId(j, k - 1, l));
						if(east || west || north || south || down)
						{
							worldObj.setBlockWithNotify(j, k, l, Block.cobblestone.blockID);
						}
					}
				}
			}
		}
    }

    public boolean isFlaming;
    protected Random ExplosionRNG;
    protected World worldObj;
    public double posX;
    public double posY;
    public double posZ;
    public Entity exploder;
    public float radius;
    public Set destroyedBlockPositions;
}