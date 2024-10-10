package units.droids;

import java.util.List;
import java.util.ArrayList;

import units.droidAbilities.defenderDroidAbilities.Endure;
import units.droidAbilities.defenderDroidAbilities.GrantShield;
import units.droidTypes.DroidType;
import units.droidTypes.droidSubtypes.DroidSubtype;
import units.droidTypes.droidSubtypes.DefenderDroidSubtype;

import units.droidAbilities.Ability;

public class DefenderDroid extends Droid {
    protected DroidSubtype droidSubtype;
    protected List<Ability> abilities = new ArrayList<>();

    public DefenderDroid(
            String droidName,
            DroidType droidType,
            DefenderDroidSubtype droidSubtype
    ) {
        super(droidName, droidType);
        this.droidSubtype = droidSubtype;

        switch (droidSubtype) {
            case ARMORED:
                this.maxHP = 150;
                this.droidDMG = 20;
                this.droidSHD = 50;
                this.droidSPD = 4;

                abilities.add(new Endure());
                break;
            case GUARDIAN:
                this.maxHP = 300;
                this.droidDMG = 0;
                this.droidSHD = 0;
                this.droidSPD = 3;

                abilities.add(new GrantShield());
                break;
        }

        this.droidHP = this.maxHP;
    }
}