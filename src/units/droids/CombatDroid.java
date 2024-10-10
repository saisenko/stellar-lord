package units.droids;

import java.util.ArrayList;
import java.util.List;

import units.droidTypes.DroidType;
import units.droidTypes.droidSubtypes.CombatDroidSubtype;
import units.droidTypes.droidSubtypes.DroidSubtype;

import units.droidAbilities.Ability;
import units.droidAbilities.combatDroidAbilities.*;

public class CombatDroid extends Droid {
    protected DroidSubtype droidSubtype;
    protected List<Ability> abilities = new ArrayList<>();
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

                abilities.add(new Grenade());
                break;
            case SUICIDE_BOMBER:
                this.maxHP = 50;
                this.droidDMG = 0;
                this.droidSPD = 6;

                abilities.add(new Explode());
                break;
        }

        this.droidHP = this.maxHP;
        this.droidSHD = 0;
    }
}