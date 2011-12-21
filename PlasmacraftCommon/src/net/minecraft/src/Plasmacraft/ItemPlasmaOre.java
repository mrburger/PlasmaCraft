package net.minecraft.src.Plasmacraft;

import net.minecraft.src.Block;
import net.minecraft.src.BlockCloth;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class ItemPlasmaOre extends ItemBlock
{
	public static String[] blockNames = { "Plutonium Ore",
        "Radionite Ore",
        "Neptunium Ore",
        "Obsidium Ore",
        "Uranium Ore"
        };

	public ItemPlasmaOre(int id) {
		super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
	}

    public int getIconFromDamage(int i)
    {
        return PlasmaCraftCore.orePlasma.getBlockTextureFromSideAndMetadata(0, i);
    }
    
    public int getPlacedBlockMetadata(int damage) {
            return damage;
    }
    
    public String getItemNameIS(ItemStack itemstack) {
        return (new StringBuilder())
                .append(super.getItemName())
                .append(".")
                .append(blockNames[itemstack.getItemDamage()])
                .toString();
    }
	
}