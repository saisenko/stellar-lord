package units.droidAbilities.supportDroidAbilities;

import units.droidAbilities.Ability;
import units.droids.Droid;

public class RaiseMorale implements Ability {

    @Override
    public String getAbilityName() {
        return "<Raise Morale>";
    }

    @Override
    public String getAbilityDescription() {
        return "<Increases allies' morale (SPD) by 2 points>";
    }

    @Override
    public void useAbility(Droid targetDroid) {
        int increaseSPD = 2;
        int targetSPD = targetDroid.getDroidSPD();
        targetDroid.setDroidSPD(targetSPD + increaseSPD);
    }
}