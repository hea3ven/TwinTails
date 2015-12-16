package com.hea3ven.twintails.item;

import java.util.List;

import com.hea3ven.twintails.TwinTailType;
import com.hea3ven.twintails.TwinTailsMod;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ItemHairBand extends ItemArmor {
    public static final ArmorMaterial hairBandArmorMaterial = ArmorMaterial.CHAIN;

    private TwinTailType[] twinTailTypes = new TwinTailType[] {
            new TwinTailType(0, "white", new Potion[] {}, "dyeWhite", 0),
            new TwinTailType(1, "red", new Potion[] {Potion.moveSpeed, Potion.jump}, "dyeRed", 14),
            new TwinTailType(2, "blue", new Potion[] {Potion.waterBreathing, Potion.damageBoost}, "dyeBlue", 11),
            new TwinTailType(3, "yellow", new Potion[] {Potion.resistance, Potion.fireResistance}, "dyeYellow", 4),
            new TwinTailType(4, "cyan", new Potion[] {}, "dyeCyan", 9),
            new TwinTailType(5, "pink", new Potion[] {}, "dyePink", 6),
            new TwinTailType(6, "black", new Potion[] {}, "dyeBlack", 15)
    };

    public ItemHairBand() {
        super(hairBandArmorMaterial, 0, 0);
        setUnlocalizedName("hairBand");
        setCreativeTab(TwinTailsMod.creativeTab);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

	public TwinTailType[] getTypes() {
		return twinTailTypes;
	}

    private int getTypeOffset(ItemStack itemStack) {
        return getTypeOffset(itemStack.getItemDamage());
    }

    private int getTypeOffset(int meta) {
        return (meta < twinTailTypes.length) ? meta : 0;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!world.isRemote && TwinTailsMod.config.twinTailsEffects) {
            refreshPotionsEffects(player,
                    twinTailTypes[getTypeOffset(itemStack)].getPotions());
        }
    }

    private void refreshPotionsEffects(EntityPlayer player, Potion[] potions) {
        for (Potion potion : potions) {
            PotionEffect effect = player.getActivePotionEffect(potion);
            if (effect == null || effect.getDuration() < 10)
                player.addPotionEffect(new PotionEffect(potion.id, 80, 1));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return twinTailTypes[getTypeOffset(itemStack)].getModel();
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName() + "."
                + twinTailTypes[getTypeOffset(itemStack)].getName();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List itemList) {
        for (TwinTailType twinTailType : twinTailTypes) {
            itemList.add(new ItemStack(item, 1, twinTailType.getOrdinal()));
        }
    }

    public void AddRecipes() {
        ItemStack anyHairTie = new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE);

        for (TwinTailType twinTailType : twinTailTypes) {
            ItemStack wool = new ItemStack(Blocks.wool, 1, twinTailType.getColorOrdinal());
            ItemStack typeHairTie = new ItemStack(this, 1, twinTailType.getOrdinal());
            GameRegistry.addRecipe(new ShapedOreRecipe(typeHairTie, " s ", "sbs",
                    " s ", 's', wool, 'b', "slimeball"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(typeHairTie,
                    anyHairTie, twinTailType.getRecipeIngredient()));
        }
    }
}
