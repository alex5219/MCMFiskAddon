package com.alexjw.mcm.server.data;

public class ModData {
    public String modName, modID, modVersion, guiClassName, modSize;

    public ModData(String modName, String modID, String modVersion, String guiClassName, String modSize) {
        this.modName = modName;
        this.modID = modID;
        this.modVersion = modVersion;
        this.guiClassName = guiClassName;
        this.modSize = modSize;
    }

    public String toString() {
        return modName + '|' + modID + '|' + modVersion + '|' + guiClassName + '|' + modSize;
    }
}
