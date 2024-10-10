package units.droidAbilities.combatDroidAbilities;

import units.droidAbilities.Ability;
import units.droids.Droid;

public class Explode implements Ability {

    @Override
    public String getAbilityName() {
        return "<Explode>";
    }

    @Override
    public String getAbilityDescription() {
        return "<Attacks target, dealing 250 points of damage. Attacker is eliminated at once>";
    }

    @Override
    public void useAbility(Droid targetDroid) {
        int damage = 250;
        targetDroid.processReceivedDMG(damage);
    }
}