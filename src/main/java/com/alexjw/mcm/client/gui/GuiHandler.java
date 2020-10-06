package com.alexjw.mcm.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, final EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case 0:
                return new GuiScreenConstructs();
        }
        return null;
    }
}
