package units.droidAbilities.defenderDroidAbilities;

import units.droidAbilities.Ability;
import units.droids.Droid;

public class Endure implements Ability {

    @Override
    public String getAbilityName() {
        return "<Endure>";
    }

    @Override
    public String getAbilityDescription() {
        return "<Reduces target's damage (DMG) by 25%>";
    }

    @Override
    public void useAbility(Droid targetDroid) {
        int targetDMG = targetDroid.getDroidDMG();
        int damageReduce = targetDMG / 4;
        targetDroid.setDroidDMG(targetDMG - damageReduce);
    }
}