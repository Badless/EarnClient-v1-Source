package earnclient.mod;

import earnclient.mod.impl.*;
import java.util.*;

public class ModManager
{
    public ToggleSprintMod toggleSprint;
    public ArrayList<Mod> mods;
    
    public ModManager() {
        (this.mods = new ArrayList<Mod>()).add(this.toggleSprint = new ToggleSprintMod());
    }
}
