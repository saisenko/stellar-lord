package units.droidAbilities;

import units.droids.Droid;

public interface Ability {
    String getAbilityName();
    String getAbilityDescription();
    void useAbility(Droid targetDroid);
}