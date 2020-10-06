package com.alexjw.mcm.server.network;

import com.alexjw.mcm.server.helper.LanternHelper;
import com.alexjw.mcm.server.items.ItemWillpowerRing;
import com.alexjw.mcm.server.items.constructs.ItemConstructWeapon;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MessageConstructItem implements IMessageHandler<MessageConstructItem, IMessage>, IMessage {
    private String itemStackID, itemStackName;

    public MessageConstructItem() {
    }

    public MessageConstructItem(ItemStack itemStack) {
        this.itemStackName = GameRegistry.findUniqueIdentifierFor(itemStack.getItem()).name;
        this.itemStackID = GameRegistry.findUniqueIdentifierFor(itemStack.getItem()).modId;
    }

    public void fromBytes(ByteBuf buf) {
        this.itemStackName = ByteBufUtils.readUTF8String(buf);
        this.itemStackID = ByteBufUtils.readUTF8String(buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.itemStackName);
        ByteBufUtils.writeUTF8String(buf, this.itemStackID);
    }

    public IMessage onMessage(MessageConstructItem message, MessageContext ctx) {
        if (ctx.side.isServer()) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            Item item = GameRegistry.findItem(message.itemStackID, message.itemStackName);
            ItemConstructWeapon itemConstructWeapon = (ItemConstructWeapon) item;
            ItemStack itemStackRing = null;

            for (ItemStack itemStack1 : player.inventory.mainInventory) {
                if (itemStack1.getItem() instanceof ItemWillpowerRing) {
                    itemStackRing = itemStack1;
                    break;
                }
            }
            if (itemStackRing != null) {
                ItemStack itemStackConstruct = new ItemStack(item);

                if (itemConstructWeapon.getCost() < (itemStackRing.getMaxDamage() - itemStackRing.getItemDamage())) {
                    if(!player.capabilities.isCreativeMode)
                        LanternHelper.consumePower(itemStackRing, player, itemConstructWeapon.getCost());
                    player.inventory.addItemStackToInventory(itemStackConstruct);
                }
            }
        }

        return null;
    }
}