package units.droidAbilities.combatDroidAbilities;

import units.droidAbilities.Ability;
import units.droids.Droid;

public class Grenade implements Ability {

    @Override
    public String getAbilityName() {
        return "<Grenade>";
    }

    @Override
    public String getAbilityDescription() {
        return "<Throws a grenade, dealing 50 points of damage>";
    }

    @Override
    public void useAbility(Droid targetDroid) {
        int damage = 50;
        targetDroid.processReceivedDMG(damage);
    }
}