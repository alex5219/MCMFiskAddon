package com.alexjw.mcm;

import com.alexjw.mcm.server.network.MessageConstructItem;
import com.alexjw.mcm.server.proxy.ServerProxy;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.io.File;

@Mod(modid = MCM.MODID, name = MCM.NAME, version = MCM.VERSION)
public class MCM extends DummyModContainer {
    public static final String MODID = "mcm";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "MCM";
    public static final SimpleNetworkWrapper packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    public static File mcmDir, resourcePackDir;
    @Mod.Instance(MCM.MODID)
    public static MCM instance;
    @SidedProxy(clientSide = "com.alexjw.mcm.server.proxy.ClientProxy", serverSide = "com.alexjw.mcm.server.proxy.ServerProxy")
    public static ServerProxy proxy;

    public MCM() {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "mcm";
        meta.name = "MCM";
        meta.version = "1.0.0";
        meta.description = "";
        meta.url = "";
    }

    @Mod.EventHandler
    public void fmlPreInitializationEvent(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void fmlInitializationEvent(FMLInitializationEvent event) {
        proxy.init();
        proxy.registerEventHandler();
        packetHandler.registerMessage(MessageConstructItem.class, MessageConstructItem.class, 1, Side.SERVER);
    }

    @Mod.EventHandler
    public void fmlPostInitializationEvent(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @SideOnly(Side.SERVER)
    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {

    }
}
