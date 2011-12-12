package net.minecraft.src.Plasmacraft;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemCausticSupportBlock extends ItemBlock
{

    private int blockID;

    public ItemCausticSupportBlock(int i)
    {
        super(i);
        blockID = i + 256;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if(world.getBlockId(i, j, k) == Block.snow.blockID)
        {
            l = 0;
        } else
        {
            if(l == 0)
            {
                j--;
            }
            if(l == 1)
            {
                j++;
            }
            if(l == 2)
            {
                k--;
            }
            if(l == 3)
            {
                k++;
            }
            if(l == 4)
            {
                i--;
            }
            if(l == 5)
            {
                i++;
            }
        }
        if(itemstack.stackSize == 0)
        {
            return false;
        }
        int i1 = world.getBlockId(i, j, k);
        boolean flag = false;
        if(Block.blocksList[i1] != null)
        {
            //flag = Block.blocksList[i1].getRenderType() == PlasmaCraftCore.causticID;
        }
        if(world.canBlockBePlacedAt(blockID, i, j, k, false, 1) || flag)
        {
            Block block = Block.blocksList[blockID];
            if(world.setBlockAndMetadataWithNotify(i, j, k, blockID, 0))
            {
                Block.blocksList[blockID].onBlockPlaced(world, i, j, k, l);
                Block.blocksList[blockID].onBlockPlacedBy(world, i, j, k, entityplayer);
                PlasmaCraftCore.proxy.playSoundEffect(world, i, j, k, block);
                itemstack.stackSize--;
            }
        }
        return true;
    }

    public String getItemNameIS(ItemStack itemstack)
    {
        return Block.blocksList[blockID].getBlockName();
    }

    public String getItemName()
    {
        return Block.blocksList[blockID].getBlockName();
    }
}