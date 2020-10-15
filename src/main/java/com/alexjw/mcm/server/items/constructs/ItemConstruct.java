package com.alexjw.mcm.server.items.constructs;

import com.alexjw.mcm.MCM;
import com.alexjw.mcm.server.helper.LanternHelper;
import com.alexjw.mcm.server.items.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemConstruct extends Item implements IConstruct {

    public ItemConstruct(String name, int maxDurability) {
        super();
        this.setUnlocalizedName("construct_" + name);
        this.setTextureName(MCM.MODID + ":construct_" + name);
        this.setMaxDamage(maxDurability);
        this.setMaxStackSize(1);
        this.setCreativeTab(null);
        ModItems.constructs.add(this);
    }

    public int getCost() {
        return (int) (this.getMaxDamage() * 0.75);
    }

    // Everything from here on is the same for every construct.
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        LanternHelper.constructUpdate(itemStack, world, entity, p_77663_4_, p_77663_5_);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int p_82790_2_) {
        return LanternHelper.getColorFromLantern(LanternHelper.getLantern(itemStack));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean p_77624_4_) {
        list.add(LanternHelper.getChatColorFromLantern(LanternHelper.getLantern(itemStack))
                + "Durability: " + EnumChatFormatting.WHITE + this.getMaxDamage());
        list.add(LanternHelper.getChatColorFromLantern(LanternHelper.getLantern(itemStack))
                + "Cost: " + EnumChatFormatting.WHITE + getCost());
        list.add(LanternHelper.getChatColorFromLantern(LanternHelper.getLantern(itemStack))
                + "" + LanternHelper.getLantern(itemStack));
    }

    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack itemStack) {
        return LanternHelper.getChatColorFromLantern(LanternHelper.getLantern(itemStack)) +
                "" + LanternHelper.getLantern(itemStack) + " " + super.getItemStackDisplayName(itemStack);
    }
}
