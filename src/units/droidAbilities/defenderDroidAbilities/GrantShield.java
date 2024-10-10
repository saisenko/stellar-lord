package units.droidAbilities.defenderDroidAbilities;

import units.droidAbilities.Ability;
import units.droids.Droid;

public class GrantShield implements Ability {

    @Override
    public String getAbilityName() {
        return "<Grant Shield>";
    }

    @Override
    public String getAbilityDescription() {
        return "<Gives ally 50 points of shield (SHD)>";
    }

    @Override
    public void useAbility(Droid targetDroid) {
        int shieldValue = 50;
        targetDroid.setDroidSHD(shieldValue);
    }
}