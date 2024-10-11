package units.droids;

import units.droidTypes.DroidType;
import units.droidTypes.droidSubtypes.CombatDroidSubtype;
import units.droidTypes.droidSubtypes.DroidSubtype;

import units.droidAbilities.combatDroidAbilities.*;

public class CombatDroid extends Droid {
    protected DroidSubtype droidSubtype;
    public CombatDroid(
            String droidName,
            DroidType droidType,
            CombatDroidSubtype droidSubtype
    ) {
        super(droidName, droidType);
        this.droidSubtype = droidSubtype;

        switch (droidSubtype) {
            case ASSAULT:
                this.maxHP = 100;
                this.droidDMG = 30;
                this.droidSPD = 5;

                addAbility(new Grenade());
                break;
            case SUICIDE_BOMBER:
                this.maxHP = 50;
                this.droidDMG = 0;
                this.droidSPD = 6;

                addAbility(new Explode());
                break;
        }

        this.droidHP = this.maxHP;
        this.droidSHD = 0;
    }
}