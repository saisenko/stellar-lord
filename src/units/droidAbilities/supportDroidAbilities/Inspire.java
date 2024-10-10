package units.droidAbilities.supportDroidAbilities;

import units.droidAbilities.Ability;
import units.droids.Droid;

public class Inspire implements Ability {

    @Override
    public String getAbilityName() {
        return "<Inspire>";
    }

    @Override
    public String getAbilityDescription() {
        return "<Increase target's DMG by 10 points>";
    }

    @Override
    public void useAbility(Droid targetDroid) {
        int dmgIncrease = 10;
        int targetDMG = targetDroid.getDroidDMG();
        targetDroid.setDroidDMG(targetDMG + dmgIncrease);
    }
}