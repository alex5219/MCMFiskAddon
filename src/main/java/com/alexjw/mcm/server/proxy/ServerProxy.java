package com.alexjw.mcm.server.proxy;

import com.alexjw.mcm.server.blocks.ModBlocks;
import com.alexjw.mcm.server.event.CommonEventHandler;
import com.alexjw.mcm.server.items.ModItems;
import com.alexjw.mcm.server.modifiers.AbilityManager;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy {
    public void preInit() {
        AbilityManager.register();
    }

    public void init() {
        ModItems.register();
        ModBlocks.register();
        // register items
    }


    public void postInit() {
    }

    public void registerEventHandler() {
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
        FMLCommonHandler.instance().bus().register(new CommonEventHandler());
    }
}
