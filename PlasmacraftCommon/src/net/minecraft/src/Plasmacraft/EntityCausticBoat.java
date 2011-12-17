package net.minecraft.src.Plasmacraft;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityBoat;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityCausticBoat extends Entity
{
    private int my_boatPosRotationIncrements;
    private double my_boatX;
    private double my_boatY;
    private double my_boatZ;
    private double my_boatYaw;
    private double my_boatPitch;
    private double my_velocityX;
    private double my_velocityY;
    private double my_velocityZ;

    public EntityCausticBoat(World world)
    {
        super(world);
        preventEntitySpawning = true;
        setSize(1.5F, 0.6F);
        yOffset = height / 2.0F;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
        dataWatcher.addObject(17, new Integer(0));
        dataWatcher.addObject(18, new Integer(1));
        dataWatcher.addObject(19, new Integer(0));
    }

    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

    public AxisAlignedBB getBoundingBox()
    {
        return boundingBox;
    }

    public boolean canBePushed()
    {
        return true;
    }

    public EntityCausticBoat(World world, double d, double d1, double d2)
    {
        this(world);
        setPosition(d, d1 + (double)yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }

    public double getMountedYOffset()
    {
        return (double)height * 0.0D - 0.30000001192092896D;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        if(PlasmaCraftCore.proxy.isMultiplayerWorld(worldObj) || isDead)
        {
            return true;
        }
        setForwardDirection(-getForwardDirection());
        setTimeSinceHit(10);
        setDamageTaken(getDamageTaken() + i * 10);
        setBeenAttacked();
        if(getDamageTaken() > 40)
        {
            if(riddenByEntity != null)
            {
                riddenByEntity.mountEntity(this);
            }
            for(int j = 0; j < 2; j++)
            {
                dropItemWithOffset(PlasmaCraftCore.ingotRadionite.shiftedIndex, 1, 0.0F);
            }

            setEntityDead();
        }
        return true;
    }

    public void performHurtAnimation()
    {
        setForwardDirection(-getForwardDirection());
        setTimeSinceHit(10);
        setDamageTaken(getDamageTaken() * 11);
    }

    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f, 
            float f1, int i)
    {
        my_boatX = d;
        my_boatY = d1;
        my_boatZ = d2;
        my_boatYaw = f;
        my_boatPitch = f1;
        my_boatPosRotationIncrements = i + 4;
        motionX = my_velocityX;
        motionY = my_velocityY;
        motionZ = my_velocityZ;
    }

    public void setVelocity(double d, double d1, double d2)
    {
        my_velocityX = motionX = d;
        my_velocityY = motionY = d1;
        my_velocityZ = motionZ = d2;
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(getTimeSinceHit() > 0)
        {
            setTimeSinceHit(getTimeSinceHit() - 1);
        }
        if(getDamageTaken() > 0)
        {
            setDamageTaken(getDamageTaken() - 1);
        }
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        int i = 5;
        double d = 0.0D;
        for(int j = 0; j < i; j++)
        {
            double d4 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 0)) / (double)i) - 0.125D;
            double d8 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 1)) / (double)i) - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBoxFromPool(boundingBox.minX, d4, boundingBox.minZ, boundingBox.maxX, d8, boundingBox.maxZ);
            boolean flag = worldObj.isAABBInMaterial(axisalignedbb, Material.water);
            flag |= worldObj.isAABBInMaterial(axisalignedbb, PlasmaCraftCore.caustic);
            if(flag)
            {
                d += 1.0D / (double)i;
            }
        }

        double d1 = Math.sqrt(motionX * motionX + motionZ * motionZ);
        if(d1 > 0.14999999999999999D)
        {
            double d3 = Math.cos(((double)rotationYaw * 3.1415926535897931D) / 180D);
            double d9 = Math.sin(((double)rotationYaw * 3.1415926535897931D) / 180D);
            for(int i1 = 0; (double)i1 < 1.0D + d1 * 60D; i1++)
            {
                double d16 = rand.nextFloat() * 2.0F - 1.0F;
                double d19 = (double)(rand.nextInt(2) * 2 - 1) * 0.69999999999999996D;
                if(rand.nextBoolean())
                {
                    double d21 = (posX - d3 * d16 * 0.80000000000000004D) + d9 * d19;
                    double d23 = posZ - d9 * d16 * 0.80000000000000004D - d3 * d19;
                    worldObj.spawnParticle("splash", d21, posY - 0.125D, d23, motionX, motionY, motionZ);
                } else
                {
                    double d22 = posX + d3 + d9 * d16 * 0.69999999999999996D;
                    double d24 = (posZ + d9) - d3 * d16 * 0.69999999999999996D;
                    worldObj.spawnParticle("splash", d22, posY - 0.125D, d24, motionX, motionY, motionZ);
                }
            }

        }
        if(PlasmaCraftCore.proxy.isMultiplayerWorld(worldObj))
        {
            if(my_boatPosRotationIncrements > 0)
            {
                double d4 = posX + (my_boatX - posX) / (double)my_boatPosRotationIncrements;
                double d10 = posY + (my_boatY - posY) / (double)my_boatPosRotationIncrements;
                double d13 = posZ + (my_boatZ - posZ) / (double)my_boatPosRotationIncrements;
                double d17;
                for(d17 = my_boatYaw - (double)rotationYaw; d17 < -180D; d17 += 360D) { }
                for(; d17 >= 180D; d17 -= 360D) { }
                rotationYaw += d17 / (double)my_boatPosRotationIncrements;
                rotationPitch += (my_boatPitch - (double)rotationPitch) / (double)my_boatPosRotationIncrements;
                my_boatPosRotationIncrements--;
                setPosition(d4, d10, d13);
                setRotation(rotationYaw, rotationPitch);
            } else
            {
                double d5 = posX + motionX;
                double d11 = posY + motionY;
                double d14 = posZ + motionZ;
                setPosition(d5, d11, d14);
                if(onGround)
                {
                    motionX *= 0.5D;
                    motionY *= 0.5D;
                    motionZ *= 0.5D;
                }
                motionX *= 0.99000000953674316D;
                motionY *= 0.94999998807907104D;
                motionZ *= 0.99000000953674316D;
            }
            return;
        }
        if(d < 1.0D)
        {
            double d6 = d * 2D - 1.0D;
            motionY += 0.039999999105930328D * d6;
        } else
        {
            if(motionY < 0.0D)
            {
                motionY /= 2D;
            }
            motionY += 0.0070000002160668373D;
        }
        if(riddenByEntity != null)
        {
            motionX += riddenByEntity.motionX * 0.20000000000000001D;
            motionZ += riddenByEntity.motionZ * 0.20000000000000001D;
        }
        double d7 = 0.40000000000000002D;
        if(motionX < -d7)
        {
            motionX = -d7;
        }
        if(motionX > d7)
        {
            motionX = d7;
        }
        if(motionZ < -d7)
        {
            motionZ = -d7;
        }
        if(motionZ > d7)
        {
            motionZ = d7;
        }
        if(onGround)
        {
            motionX *= 0.5D;
            motionY *= 0.5D;
            motionZ *= 0.5D;
        }
        moveEntity(motionX, motionY, motionZ);
        if(isCollidedHorizontally && d1 > 0.20000000000000001D)
        {
            if(!PlasmaCraftCore.proxy.isMultiplayerWorld(worldObj))
            {
                setEntityDead();
                for(int j = 0; j < 2; j++)
                {
                    dropItemWithOffset(PlasmaCraftCore.ingotRadionite.shiftedIndex, 1, 0.0F);
                }

            }
        } else
        {
            motionX *= 0.99000000953674316D;
            motionY *= 0.94999998807907104D;
            motionZ *= 0.99000000953674316D;
        }
        rotationPitch = 0.0F;
        double d14 = rotationYaw;
        double d16 = prevPosX - posX;
        double d17 = prevPosZ - posZ;
        if(d16 * d16 + d17 * d17 > 0.001D)
        {
            d14 = (float)((Math.atan2(d17, d16) * 180D) / 3.1415926535897931D);
        }
        double d19;
        for(d19 = d14 - (double)rotationYaw; d19 >= 180D; d19 -= 360D) { }
        for(; d19 < -180D; d19 += 360D) { }
        if(d19 > 20D)
        {
            d19 = 20D;
        }
        if(d19 < -20D)
        {
            d19 = -20D;
        }
        rotationYaw += d19;
        setRotation(rotationYaw, rotationPitch);
        List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if(list != null && list.size() > 0)
        {
            for(int j1 = 0; j1 < list.size(); j1++)
            {
                Entity entity = (Entity)list.get(j1);
                if(entity != riddenByEntity && entity.canBePushed() && (entity instanceof EntityBoat))
                {
                    entity.applyEntityCollision(this);
                }
            }

        }
        for(int k1 = 0; k1 < 4; k1++)
        {
            int l1 = MathHelper.floor_double(posX + ((double)(k1 % 2) - 0.5D) * 0.80000000000000004D);
            int i2 = MathHelper.floor_double(posY);
            int j2 = MathHelper.floor_double(posZ + ((double)(k1 / 2) - 0.5D) * 0.80000000000000004D);
            if(worldObj.getBlockId(l1, i2, j2) == Block.snow.blockID)
            {
                worldObj.setBlockWithNotify(l1, i2, j2, 0);
            }
        }
        
        if(riddenByEntity != null && riddenByEntity.isDead)
        {
            riddenByEntity = null;
        }
    }

    public void updateRiderPosition()
    {
        if(riddenByEntity == null)
        {
            return;
        } else
        {
            double d = Math.cos(((double)rotationYaw * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
            double d1 = Math.sin(((double)rotationYaw * 3.1415926535897931D) / 180D) * 0.40000000000000002D;
            riddenByEntity.setPosition(posX + d, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ + d1);
            return;
        }
    }

    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }

    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        if(riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer)
        {
            return true;
        }
        if(!PlasmaCraftCore.proxy.isMultiplayerWorld(worldObj))
        {
            entityplayer.mountEntity(this);
        }
        return true;
    }

    public void setDamageTaken(int i)
    {
        dataWatcher.updateObject(19, Integer.valueOf(i));
    }

    public int getDamageTaken()
    {
        return dataWatcher.getWatchableObjectInt(19);
    }

    public void setTimeSinceHit(int i)
    {
        dataWatcher.updateObject(17, Integer.valueOf(i));
    }

    public int getTimeSinceHit()
    {
        return dataWatcher.getWatchableObjectInt(17);
    }

    public void setForwardDirection(int i)
    {
        dataWatcher.updateObject(18, Integer.valueOf(i));
    }

    public int getForwardDirection()
    {
        return dataWatcher.getWatchableObjectInt(18);
    }
}