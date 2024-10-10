package units.droidAbilities.supportDroidAbilities;

import units.droidAbilities.Ability;
import units.droids.Droid;

public class Repair implements Ability {

    @Override
    public String getAbilityName() {
        return "<Repair>";
    }

    @Override
    public String getAbilityDescription() {
        return "<Repairs target, restoring 35 points of HP>";
    }

    @Override
    public void useAbility(Droid targetDroid) {
        int healAmmount = 35;
        int droidCurrentHP = targetDroid.getDroidHP();
        int droidMaxHP = targetDroid.getMaxHP();
        targetDroid.setDroidHP(Math.min(droidMaxHP, droidCurrentHP + healAmmount));
    }
}