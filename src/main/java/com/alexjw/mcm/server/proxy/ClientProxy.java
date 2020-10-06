package com.alexjw.mcm.server.proxy;

import com.alexjw.mcm.MCM;
import com.alexjw.mcm.client.gui.GuiHandler;
import com.alexjw.mcm.client.render.item.RenderItemWillRing;
import com.alexjw.mcm.server.items.ModItems;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy {

    public void preInit() {
        super.preInit();
    }

    public void init() {
        super.init();
        MinecraftForgeClient.registerItemRenderer(ModItems.willpowerRing, new RenderItemWillRing());
    }

    public void postInit() {
        super.postInit();
    }

    public void registerEventHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(MCM.instance, new GuiHandler());
    }
}
