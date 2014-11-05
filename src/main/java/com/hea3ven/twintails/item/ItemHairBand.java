package com.hea3ven.twintails.item;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.hea3ven.twintails.TwinTailType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHairBand extends ItemArmor {

    public static final ArmorMaterial hairBandArmorMaterial = ArmorMaterial.CLOTH;

    private TwinTailType[] twinTailTypes = new TwinTailType[] {
            new TwinTailType(0, "normal"),
            new TwinTailType(0, "red")
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
        if (!world.isRemote) {
            PotionEffect effect = player
                    .getActivePotionEffect(Potion.moveSpeed);
            if (effect == null || effect.getDuration() < 10)
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,
                        80, 2));
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
}
