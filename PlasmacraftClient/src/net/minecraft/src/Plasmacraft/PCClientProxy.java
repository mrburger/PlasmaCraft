package net.minecraft.src.Plasmacraft;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;

public class PCClientProxy implements IPCProxy
{
	public PCClientProxy()
	{
	}
	
	@Override
	public float callWorldMethodA(World worldObj, Vec3D vec3d,
			AxisAlignedBB axisalignedbb)
	{
		return worldObj.func_675_a(vec3d, axisalignedbb);
	}

	@Override
	public boolean isMultiplayerWorld(World world)
	{
		return (world.multiplayerWorld);
	}

	@Override
	public void setFire(Entity entity, int i)
	{
		entity.setFire(i);
	}
	
	@Override
	public String getMinecraftDirectory()
	{
		File dir = Minecraft.getAppDir("minecraft");
		return dir.getAbsolutePath();
	}

	@Override
	public BiomeGenBase getHillBiome() {
		return BiomeGenBase.hills;
	}

	@Override
	public boolean getEntityInstanceOf(Entity entity) {
		return entity instanceof EntityPlayerSP;
	}

	@Override
	public int getLightBrightnessForSkyBlocks(IBlockAccess iblockaccess, int i,
			int j, int k, int l) {
		return iblockaccess.getLightBrightnessForSkyBlocks(i, j, k, l);
	}

	@Override
	public float getLightBrightess(IBlockAccess iblockaccess, int i, int j,
			int k) {
		return iblockaccess.getLightBrightness(i, j, k);
	}

	@Override
	public ItemStack armorItemInSlot(EntityPlayer entityplayer, int i) {
		return entityplayer.inventory.armorItemInSlot(i);
	}
	
	@Override
	public boolean getCanBurn(World world, int i, int j, int k)

	{
        return world.getBlockMaterial(i, j, k).getCanBurn();
	}
	

	@Override
	public int getPotionId(Potion moveslowdown) {
		return moveslowdown.id;
	}

	@Override
	public MovingObjectPosition callAxisAlignedMethodA(
			AxisAlignedBB axisalignedbb, Vec3D vec3d, Vec3D vec3d1) {
		return axisalignedbb.func_1169_a(vec3d, vec3d1);
	}

	@Override
	public void playSoundEffect(World world, int i, int j, int k, Block block) {
		world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, block.stepSound.stepSoundDir2(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
	}

	@Override
	public double getAverageEdgeLength(AxisAlignedBB boundingBox) {
		return boundingBox.getAverageEdgeLength();
	}

	@Override
	public void OpenGUI(EntityPlayer entityplayer,
			TileEntityPlasmaBench tileentityplasmabench) {
		ModLoader.OpenGUI(entityplayer, new GuiPlasmaBench(entityplayer.inventory, tileentityplasmabench));
	}
	
}