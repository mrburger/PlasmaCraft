package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.src.Block;
import net.minecraft.src.BlockOre;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Malkierian_PlasmaCraft", name = "PlasmaCraft", version = "0.3.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class PlasmaCraft
{
	public static Block orePlasma;
	public static Block oreLeadBlock;
	public static Block glowCloth;

	public static Item goopAcid;
	public static Item goopCryonite;
	public static Item goopNeptunium;
	public static Item goopNetherflow;
	public static Item goopObsidium;
	public static Item goopPlutonium;
	public static Item goopRadionite;
	public static Item goopUranium;
	public static Item plasma;

	public static Item ingotCryonite;
	public static Item ingotLead;
	public static Item ingotNeptunium;
	public static Item ingotNetherflow;
	public static Item ingotObsidium;
	public static Item ingotPlutonium;
	public static Item ingotRadionite;
	public static Item ingotUranium;
	
	public static int oreNeptuniumIndex = 8;
	public static int oreObsidiumIndex = 9;
	public static int orePlutoniumIndex = 13;
	public static int oreRadioniteIndex = 14;
	public static int oreUraniumIndex = 4;
	public static int oreLeadIndex = 23;

	public static final int plutoniumMeta = 0;
	public static final int radioniteMeta = 1;
	public static final int neptuniumMeta = 2;
    public static final int obsidiumMeta = 3; 
    public static final int uraniumMeta = 4;
    
	public static int oreBlockID = 2500;
	public static int oreLeadBlockID = 2501;
	public static int glowClothBlockID = 2502;

	public static int goopAcidID = 2700;
	public static int goopCryoniteID = 2701;
	public static int goopNeptuniumID = 2702;
	public static int goopNetherflowID = 2703;
	public static int goopObsidiumID = 2704;
	public static int goopPlutoniumID = 2705;
	public static int goopRadioniteID = 2706;
	public static int goopUraniumID = 2707;
	public static int plasmaID = 2708;

	public static int ingotCryoniteID = 2709;
	public static int ingotLeadID = 2716;
	public static int ingotNeptuniumID = 2710;
	public static int ingotNetherflowID = 2711;
	public static int ingotObsidiumID = 2712;
	public static int ingotPlutoniumID = 2713;
	public static int ingotRadioniteID = 2714;
	public static int ingotUraniumID = 2715;

	public static int acidLakeYCutoff = 48;
	public static int acidSpoutCount = 20;
	public static int acidSpoutYRange = 30;
	public static int acidSpoutYStart = 8;
	public static int leadOreVeinCount = 6;
	public static int leadOreVeinSize = 6;
	public static int leadOreYRange = 80;
	public static int leadOreYStart = 4;
	public static int neptuniumOreVeinCount = 6;
	public static int neptuniumOreVeinSize = 10;
	public static int neptuniumOreYRange = 64;
	public static int neptuniumOreYStart = 32;
	public static int neptuniumSpoutCount = 20;
	public static int neptuniumSpoutYRange = 64;
	public static int neptuniumSpoutYStart = 8;
	public static int netherflowLakeChance = 32;
	public static int netherflowLakeYCutoff = 96;
	public static int netherflowLakeYRange = 16;
	public static int netherflowLakeYStart = 56;
	public static int netherflowSpoutCount = 20;
	public static int netherflowSpoutYRange = 96;
	public static int netherflowSpoutYStart = 16;
	public static int obsidiumOreVeinCount = 4;
	public static int obsidiumOreVeinSize = 10;
	public static int obsidiumOreYRange = 64;
	public static int obsidiumOreYStart = 32;
	public static int plutoniumOreVeinCount = 4;
	public static int plutoniumOreVeinSize = 6;
	public static int plutoniumOreYRange = 16;
	public static int plutoniumOreYStart = 4;
	public static int radioniteOreVeinCount = 4;
	public static int radioniteOreVeinSize = 6;
	public static int radioniteOreYRange = 24;
	public static int radioniteOreYStart = 4;
	public static int uraniumOreVeinCount = 4;
	public static int uraniumOreVeinSize = 6;
	public static int uraniumOreYRange = 16;
	public static int uraniumOreYStart = 4;

	public static int goopAcidIndex = 34;
	public static int glowClothAcidIndex = 6;
	public static int glowClothCryoniteIndex = 21;
	public static int glowClothNeptuniumIndex = 18;
	public static int glowClothNetherflowIndex = 17;
	public static int glowClothObsidiumIndex = 22;
	public static int glowClothPlutoniumIndex = 20;
	public static int glowClothRadioniteIndex = 7;
	public static int glowClothUraniumIndex = 19;
	public static int plasmaIndex = 33;

	public static int goopCryoniteIndex = 9;
	public static int goopNeptuniumIndex = 23;
	public static int goopNetherflowIndex = 26;
	public static int goopObsidiumIndex = 29;
	public static int goopPlutoniumIndex = 39;
	public static int goopRadioniteIndex = 42;
	public static int goopUraniumIndex = 47;

	public static int ingotCryoniteIndex = 10;
	public static int ingotLeadIndex = 50;
	public static int ingotNeptuniumIndex = 24;
	public static int ingotNetherflowIndex = 27;
	public static int ingotObsidiumIndex = 30;
	public static int ingotPlutoniumIndex = 40;
	public static int ingotRadioniteIndex = 43;
	public static int ingotUraniumIndex = 48;
    
    public static final int glowClothAcidMeta = 0;
    public static final int glowClothRadioniteMeta = 1;
    public static final int glowClothNetherflowMeta = 2;
    public static final int glowClothNeptuniumMeta = 3;
    public static final int glowClothUraniumMeta = 4;
    public static final int glowClothPlutoniumMeta = 5;
    public static final int glowClothCryoniteMeta = 6;
    public static final int glowClothObsidiumMeta = 7;
	
	public static boolean generateUranium = true;
	public static boolean generatePlutonium = true;
	
	// The instance of your mod that Forge uses.
	@Instance("PlasmaCraft")
	public static PlasmaCraft instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="com.elvenwater.malkierian.Plasmacraft.client.ClientProxy", serverSide="com.elvenwater.malkierian.Plasmacraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		registerBlocks();
		
		registerItems();
		
		registerRecipes();
		
		GameRegistry.registerWorldGenerator(new WorldGenerator());
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
	private void registerBlocks()
	{
		orePlasma = new BlockPlasmaOre(oreBlockID, orePlutoniumIndex).setLightValue(0.5334f);
		GameRegistry.registerBlock(orePlasma, com.elvenwater.malkierian.Plasmacraft.common.ItemPlasmaOre.class);
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, plutoniumMeta), 	"Plutonium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, radioniteMeta), 	"Radionite Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, uraniumMeta), 		"Uranium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, neptuniumMeta), 	"Neptunium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, obsidiumMeta), 	"Obsidium Ore");
		
		oreLeadBlock = new BlockOre(oreLeadBlockID, oreLeadIndex).setLightValue(0.0f).setBlockName("Lead Ore");
		oreLeadBlock.setTextureFile(CommonProxy.BLOCK_PNG);
		GameRegistry.registerBlock(oreLeadBlock);
		LanguageRegistry.addName(oreLeadBlock, "Lead Ore");
		
		MinecraftForge.setBlockHarvestLevel(orePlasma, obsidiumMeta, 	"pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(orePlasma, uraniumMeta, 	"pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, radioniteMeta, 	"pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, plutoniumMeta, 	"pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, neptuniumMeta, 	"pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(oreLeadBlock, "pickaxe", 1);
        //MinecraftForge.setBlockHarvestLevel(frozenCryonite, "pickaxe", 1);
        
        glowCloth = new BlockGlowCloth(glowClothBlockID, glowClothAcidIndex);
        GameRegistry.registerBlock(glowCloth, com.elvenwater.malkierian.Plasmacraft.common.ItemGlowCloth.class);
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothAcidMeta),		"Acid Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothRadioniteMeta),	"Radionite Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNetherflowMeta),	"Netherflow Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta),	"Neptunium Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothUraniumMeta),		"Uranium Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta),	"Plutonium Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothCryoniteMeta),	"Cryonite Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothObsidiumMeta),	"Obsidium Glowcloth");
	}
	
	private void registerItems()
	{
		goopAcid = (new ItemPlasma(goopAcidID)).setIconIndex(goopAcidIndex).setItemName("goopAcid").setCreativeTab(CreativeTabs.tabMisc);
		goopCryonite = (new ItemPlasma(goopCryoniteID)).setIconIndex(goopCryoniteIndex).setItemName("goopCryonite").setCreativeTab(CreativeTabs.tabMisc);
		goopNeptunium = (new ItemPlasma(goopNeptuniumID)).setIconIndex(goopNeptuniumIndex).setItemName("goopNeptunium").setCreativeTab(CreativeTabs.tabMisc);
		goopNetherflow = (new ItemPlasma(goopNetherflowID)).setIconIndex(goopNetherflowIndex).setItemName("goopNetherflow").setCreativeTab(CreativeTabs.tabMisc);
		goopObsidium = (new ItemPlasma(goopObsidiumID)).setIconIndex(goopObsidiumIndex).setItemName("goopObsidium").setCreativeTab(CreativeTabs.tabMisc);
		goopPlutonium = (new ItemPlasma(goopPlutoniumID)).setIconIndex(goopPlutoniumIndex).setItemName("goopPlutonium").setCreativeTab(CreativeTabs.tabMisc);
		goopRadionite = (new ItemPlasma(goopRadioniteID)).setIconIndex(goopRadioniteIndex).setItemName("goopRadionite").setCreativeTab(CreativeTabs.tabMisc);
		goopUranium = (new ItemPlasma(goopUraniumID)).setIconIndex(goopUraniumIndex).setItemName("goopUranium").setCreativeTab(CreativeTabs.tabMisc);
        plasma = (new ItemPlasma(plasmaID)).setIconIndex(plasmaIndex).setItemName("plasma").setCreativeTab(CreativeTabs.tabMisc);
        
        ingotCryonite = (new ItemPlasma(ingotCryoniteID)).setIconIndex(ingotCryoniteIndex).setItemName("ingotCryonite").setCreativeTab(CreativeTabs.tabMaterials);
        ingotLead = (new ItemPlasma(ingotLeadID)).setIconIndex(ingotLeadIndex).setItemName("ingotLead").setCreativeTab(CreativeTabs.tabMaterials);
        ingotNeptunium = (new ItemPlasma(ingotNeptuniumID)).setIconIndex(ingotNeptuniumIndex).setItemName("ingotNeptunium").setCreativeTab(CreativeTabs.tabMaterials);
        ingotNetherflow = (new ItemPlasma(ingotNetherflowID)).setIconIndex(ingotNetherflowIndex).setItemName("ingotNetherflow").setCreativeTab(CreativeTabs.tabMaterials);
        ingotObsidium = (new ItemPlasma(ingotObsidiumID)).setIconIndex(ingotObsidiumIndex).setItemName("ingotObsidium").setCreativeTab(CreativeTabs.tabMaterials);
        ingotPlutonium = (new ItemPlasma(ingotPlutoniumID)).setIconIndex(ingotPlutoniumIndex).setItemName("ingotPlutonium").setCreativeTab(CreativeTabs.tabMaterials);
        ingotRadionite = (new ItemPlasma(ingotRadioniteID)).setIconIndex(ingotRadioniteIndex).setItemName("ingotRadionite").setCreativeTab(CreativeTabs.tabMaterials);
        ingotUranium = (new ItemPlasma(ingotUraniumID)).setIconIndex(ingotUraniumIndex).setItemName("ingotUranium").setCreativeTab(CreativeTabs.tabMaterials);
        
        LanguageRegistry.addName(goopAcid, "Acid Goop");
        LanguageRegistry.addName(goopCryonite, "Cryonite Goop");
        LanguageRegistry.addName(goopNeptunium, "Neptunium Goop");
        LanguageRegistry.addName(goopNetherflow, "Netherflow Goop");
        LanguageRegistry.addName(goopObsidium, "Obsidium Goop");
        LanguageRegistry.addName(goopPlutonium, "Plutonium Goop");
        LanguageRegistry.addName(goopRadionite, "Radionite Goop");
        LanguageRegistry.addName(goopUranium, "Uranium Goop");
        LanguageRegistry.addName(ingotCryonite, "Cryonite Ingot");
        LanguageRegistry.addName(ingotLead, "Lead Ingot");
        LanguageRegistry.addName(ingotNeptunium, "Neptunium Ingot");
        LanguageRegistry.addName(ingotNetherflow, "Netherflow Ingot");
        LanguageRegistry.addName(ingotObsidium, "Obsidium Ingot");
        LanguageRegistry.addName(ingotPlutonium, "Plutonium Ingot");
        LanguageRegistry.addName(ingotRadionite, "Radionite Ingot");
        LanguageRegistry.addName(ingotUranium, "Uranium Ingot");
        LanguageRegistry.addName(plasma, "Plasma");
	}
	
	private void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothAcidMeta), goopAcid, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta), goopPlutonium, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothRadioniteMeta), goopRadionite, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta), goopNeptunium, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothNetherflowMeta), goopNetherflow, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothObsidiumMeta), goopObsidium, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothCryoniteMeta), goopCryonite, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothUraniumMeta), goopUranium, new ItemStack(Block.cloth, 1, 0));
		
		GameRegistry.addSmelting(oreLeadBlock.blockID, new ItemStack(ingotLead, 1), 0.1f);
	}
}