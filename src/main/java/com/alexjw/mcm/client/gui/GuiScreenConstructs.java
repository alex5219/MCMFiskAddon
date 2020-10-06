package com.alexjw.mcm.client.gui;

import com.alexjw.mcm.MCM;
import com.alexjw.mcm.server.items.ModItems;
import com.alexjw.mcm.server.network.MessageConstructItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiScreenConstructs extends GuiScreen {
    private final String title;
    private String name = "";

    public GuiScreenConstructs() {
        this.title = "Constructs";
    }

    @Override
    public void initGui() {
        buttonList.clear();
        int buttonID = 0;
        int constructCount = 0;
        int widthIncrease = 0;
        int heightDecrease = 0;
        for (Item item : ModItems.constructs) {
            buttonList.add(new GuiButtonConstruct(buttonID, new ItemStack(item),
                    width / 2 - 81 + widthIncrease, height / 2 - 13 + heightDecrease));
            buttonID++;

            widthIncrease = (widthIncrease + 18);

            constructCount++;
            if (constructCount == 9) {
                widthIncrease = 0;
                constructCount = 0;
                heightDecrease = (heightDecrease + 20);
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);

        if (button instanceof GuiButtonConstruct) {
            GuiButtonConstruct guiButtonConstruct = (GuiButtonConstruct) button;
            MCM.packetHandler.sendToServer(new MessageConstructItem(guiButtonConstruct.itemStack));
        }
    }


    @Override
    protected void keyTyped(char character, int key) {
        super.keyTyped(character, key);
        Keyboard.enableRepeatEvents(true);

        if (ChatAllowedCharacters.isAllowedCharacter(character) && fontRendererObj.getStringWidth(name + character + "_") < 170) {
            name += character;
        }

        if (name.length() > 0 && Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
            if (isCtrlKeyDown()) {
                name = name.substring(0, name.contains(" ") ? name.lastIndexOf(" ") : 0);
            } else {
                name = name.substring(0, name.length() - 1);
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int x = width / 2 - 45;
        int y = height / 2 - 30;
        int additionalWidth = 40;
        drawCenteredString(fontRendererObj, title, width / 2, y - 15, 16777215);
        mc.renderEngine.bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_item_search.png"));
        drawTexturedModalRect(x, y, 80, 4, 90, 12);
        drawTexturedModalRect(x - additionalWidth, y, 80, 4, 89, 12);
        drawTexturedModalRect(x + additionalWidth, y, 81, 4, 89, 12);
        drawString(fontRendererObj, name + (mc.thePlayer.ticksExisted % 20 >= 10 ? "" : "_"), x + 2 - additionalWidth, y + 2, 0xffffff);
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (Object guiButton : this.buttonList) {
            if (guiButton instanceof GuiButtonConstruct) {
                GuiButtonConstruct guiButtonConstruct = (GuiButtonConstruct) guiButton;
                guiButtonConstruct.setActive(guiButtonConstruct.itemStack.getUnlocalizedName().contains(this.name));
                if(guiButtonConstruct.isActive()) {
                    if (mouseX >= guiButtonConstruct.xPosition && mouseY >= guiButtonConstruct.yPosition && mouseX < guiButtonConstruct.xPosition + guiButtonConstruct.width && mouseY < guiButtonConstruct.yPosition + guiButtonConstruct.height) {
                        this.renderToolTip(guiButtonConstruct.itemStack, mouseX, mouseY + 28);
                    }
                }
            }
        }
    }
}