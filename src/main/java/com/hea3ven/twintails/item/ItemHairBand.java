package com.hea3ven.twintails.item;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.hea3ven.twintails.TwinTailType;
import com.hea3ven.twintails.TwinTailsMod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHairBand extends ItemArmor {

    public static final ArmorMaterial hairBandArmorMaterial = ArmorMaterial.CHAIN;

    private TwinTailType[] twinTailTypes = new TwinTailType[] {
            new TwinTailType(0, "normal", new Potion[] {}),
            new TwinTailType(1, "red", new Potion[] {Potion.moveSpeed, Potion.jump}),
            new TwinTailType(2, "blue", new Potion[] {Potion.waterBreathing, Potion.damageBoost}),
            new TwinTailType(3, "yellow", new Potion[] {Potion.resistance, Potion.fireResistance})
    };

    public ItemHairBand() {
        super(hairBandArmorMaterial, 0, 0);
        setUnlocalizedName("hairBand");
        setCreativeTab(CreativeTabs.tabCombat);
        setHasSubtypes(true);
        setMaxDamage(0);
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
                player.addPotionEffect(new PotionEffect(potion.id, 80, 0));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return twinTailTypes[getTypeOffset(itemStack)].getModel();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        for (TwinTailType twinTailType : twinTailTypes) {
            twinTailType.registerIcon(iconRegister);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return twinTailTypes[getTypeOffset(meta)].getIcon();
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
        ItemStack normalHairTie = new ItemStack(this, 1, 0);
        ItemStack redHairTie = new ItemStack(this, 1, 1);
        ItemStack anyHairTie = new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE);

        GameRegistry.addRecipe(new ShapedOreRecipe(normalHairTie, " s ", "sbs", " s ", 's', Items.string, 'b', "slimeball"));

        GameRegistry.addRecipe(new ShapelessOreRecipe(normalHairTie, anyHairTie, "dyeWhite"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(redHairTie, anyHairTie, "dyeRed"));
    }
}
