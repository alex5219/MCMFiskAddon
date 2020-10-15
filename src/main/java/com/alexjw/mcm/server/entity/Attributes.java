package com.alexjw.mcm.server.entity;

import net.minecraft.entity.ai.attributes.RangedAttribute;

import java.util.UUID;

public interface Attributes {
    /**
     * How much damage can penetrate armor, i.e rapiers from TIC
     */
    UUID penetrateArmourUUID = UUID.fromString("db3f55d3-645c-4f38-a497-9c13a33db5cf");
    /**
     * Additional "generic" damage by-passing armor applied when an entity attack another (including players)
     */
    RangedAttribute armourPenetrate = new RangedAttribute("weapon.penetrateArmor", 0.0D, 0.0D, Double.MAX_VALUE);

    /**
     * Daze an entity when attacked
     */
    UUID dazeUUID = UUID.fromString("927f0df6-946e-4e78-a479-c2c13034edb5");

    /**
     * Base value is 0, must be added
     */
    RangedAttribute daze = new RangedAttribute("weapon.daze", 0.0D, 0.0D, 1.0D);

    /**
     * Used to determine the reach
     */
    UUID extendReachUUID = UUID.fromString("fb557a05-866e-4017-990b-aab8450bf41b");
    /**
     * Distance added to the default maximum reach for attacks, it can reduce or increase said range
     */
    RangedAttribute extendedReach = (RangedAttribute) new RangedAttribute("weapon.extendedReach", 0.0D, -5.0D, Double.MAX_VALUE).setShouldWatch(true);

    /**
     * How fast you can swing with the weapon
     */
    UUID attackSpeedUUID = UUID.fromString("4833af8b-40f2-44c5-8405-735f7003b1be");
    /**
     * Base value is 0, so at least one modifier with "add" (ie index=0) operation is required for other modifiers to do anything
     */
    RangedAttribute attackSpeed = new RangedAttribute("weapon.attackSpeed", 0.0D, -10.0D, 10.0D);
}
