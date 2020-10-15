package com.alexjw.mcm.server.entity;

import net.minecraft.item.ItemStack;

public interface IExtendedReach {
    /**
     * Distance in blocks to add to the weapon
     * @param itemStack
     * @return
     */
    public float getReachModifier(ItemStack itemStack);
}
