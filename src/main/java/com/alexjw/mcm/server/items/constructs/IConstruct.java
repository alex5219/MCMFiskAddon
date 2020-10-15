package com.alexjw.mcm.server.items.constructs;

public interface IConstruct {
    /**
     * Calculate the cost for a weapon construct.
     *
     * These values should change between tools & weapons
     *
     * @return The overall cost after the math
     */
    public int getCost();
}
