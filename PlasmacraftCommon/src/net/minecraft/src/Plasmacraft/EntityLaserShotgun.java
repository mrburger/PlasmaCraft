package net.minecraft.src.Plasmacraft;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;

public class EntityLaserShotgun extends Entity
{
    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private boolean inGround;
    public int arrowShake;
    public EntityLiving owner;
    private int ticksInAir;

    public EntityLaserShotgun(World world)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        arrowShake = 0;
        ticksInAir = 0;
        setSize(0.5F, 0.5F);
    }

    public EntityLaserShotgun(World world, double d, double d1, double d2)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        arrowShake = 0;
        ticksInAir = 0;
        setSize(0.5F, 0.5F);
        setPosition(d, d1, d2);
        yOffset = 0.0F;
    }

    public EntityLaserShotgun(World world, EntityLiving entityliving)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        arrowShake = 0;
        ticksInAir = 0;
        owner = entityliving;
        setSize(0.5F, 0.5F);
        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
        setArrowHeading(motionX, motionY, motionZ, 5F, 1.0F);
    }

    protected void entityInit()
    {
    }

    public void setArrowHeading(double d, double d1, double d2, float f, 
            float f1)
    {
        float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += rand.nextGaussian() * 0.017499999832361935D * (double)f1;
        d1 += rand.nextGaussian() * 0.027499999832361937D * (double)f1;
        d2 += rand.nextGaussian() * 0.097499999832361933D * (double)f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        motionX = d;
        motionY = d1;
        motionZ = d2;
        float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
    }

    public void setVelocity(double d, double d1, double d2)
    {
        motionX = d;
        motionY = d1;
        motionZ = d2;
        if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(d * d + d2 * d2);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f) * 180D) / 3.1415927410125732D);
        }
    }

    public int tickRate()
    {
        return 10;
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D);
        }
        if(arrowShake > 0)
        {
            arrowShake--;
        }
        if(inGround)
        {
            setEntityDead();
        } else
        {
            ticksInAir++;
        }
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        Vec3D vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
        vec3d = Vec3D.createVector(posX, posY, posZ);
        vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        if(movingobjectposition != null)
        {
            vec3d1 = Vec3D.createVector(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
        Entity entity = null;
        List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity1 = (Entity)list.get(i);
            if(!entity1.canBeCollidedWith() || entity1 == owner && ticksInAir < 5)
            {
                continue;
            }
            float f3 = 0.3F;
            AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f3, f3, f3);
            MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
            if(movingobjectposition1 == null)
            {
                continue;
            }
            double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
            if(d1 < d || d == 0.0D)
            {
                entity = entity1;
                d = d1;
            }
        }

        if(entity != null)
        {
            movingobjectposition = new MovingObjectPosition(entity);
        }
        if(movingobjectposition != null)
        {
            if(movingobjectposition.entityHit != null)
            {
                if(movingobjectposition.entityHit == owner)
                {
                    return;
                }
                if(movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, owner), 4))
                {
                    int j = MathHelper.floor_double(movingobjectposition.entityHit.boundingBox.minX);
                    int l = MathHelper.floor_double(movingobjectposition.entityHit.boundingBox.minY);
                    int j1 = MathHelper.floor_double(movingobjectposition.entityHit.boundingBox.minZ);
                    worldObj.setBlockWithNotify(j, l, j1, Block.fire.blockID);
                    entity.setFire(1);
                    setEntityDead();
                }
            } else
            {
                int k = movingobjectposition.blockX;
                int i1 = movingobjectposition.blockY;
                int k1 = movingobjectposition.blockZ;
                boolean flag = true;
                if(worldObj.getBlockId(k, i1, k1) == Block.ice.blockID)
                {
                    worldObj.setBlockWithNotify(k, i1, k1, Block.waterMoving.blockID);
                    flag = false;
                }
                if(worldObj.getBlockId(k, i1, k1) == Block.tallGrass.blockID)
                {
                    worldObj.setBlockWithNotify(k, i1, k1, Block.fire.blockID);
                    flag = false;
                }
                if(worldObj.getBlockId(k, i1, k1) == Block.snow.blockID)
                {
                    worldObj.setBlockWithNotify(k, i1, k1, Block.fire.blockID);
                    flag = false;
                }
                if(worldObj.getBlockId(k, i1, k1) == Block.plantRed.blockID)
                {
                    worldObj.setBlockWithNotify(k, i1, k1, Block.fire.blockID);
                    flag = false;
                }
                if(worldObj.getBlockId(k, i1, k1) == Block.plantYellow.blockID)
                {
                    worldObj.setBlockWithNotify(k, i1, k1, Block.fire.blockID);
                    flag = false;
                }
                if(worldObj.isAirBlock(k, i1 + 1, k1)&& flag)
                {
                    worldObj.setBlockWithNotify(k, i1 + 1, k1, Block.fire.blockID);
                }
                if(worldObj.isAirBlock(k, i1, k1 + 1)&& flag)
                {
                    worldObj.setBlockWithNotify(k, i1, k1 + 1, Block.fire.blockID);
                }
                if(worldObj.isAirBlock(k, i1, k1 - 1)&& flag)
                {
                    worldObj.setBlockWithNotify(k, i1, k1 - 1, Block.fire.blockID);
                }
                if(worldObj.isAirBlock(k + 1, i1, k1)&& flag)
                {
                    worldObj.setBlockWithNotify(k + 1, i1, k1, Block.fire.blockID);
                }
                if(worldObj.isAirBlock(k - 1, i1, k1)&& flag)
                {
                    worldObj.setBlockWithNotify(k - 1, i1, k1, Block.fire.blockID);
                }
                xTile = movingobjectposition.blockX;
                yTile = movingobjectposition.blockY;
                zTile = movingobjectposition.blockZ;
                inTile = worldObj.getBlockId(xTile, yTile, zTile);
                motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
                motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
                motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
                float f5 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
                posX -= (motionX / (double)f5) * 0.05000000074505806D;
                posY -= (motionY / (double)f5) * 0.05000000074505806D;
                posZ -= (motionZ / (double)f5) * 0.05000000074505806D;
                inGround = true;
                arrowShake = 7;
            }
        }
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        for(rotationPitch = (float)((Math.atan2(motionY, f1) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f2 = 0.99F;
        //float f4 = 0.03F;
        if(isInWater())
        {
            for(int l1 = 0; l1 < 4; l1++)
            {
                float f6 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f6, posY - motionY * (double)f6, posZ - motionZ * (double)f6, motionX, motionY, motionZ);
            }

            f2 = 0.8F;
        }
        motionX *= f2;
        motionY *= f2;
        motionZ *= f2;
        setPosition(posX, posY, posZ);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("shake", (byte)arrowShake);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        arrowShake = nbttagcompound.getByte("shake") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
        if(worldObj.isRemote)
        {
            return;
        }
        if(inGround && owner == entityplayer && arrowShake <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Item.arrow, 1)))
        {
            setEntityDead();
        }
    }

    public float getShadowSize()
    {
        return 0.0F;
    }
}
