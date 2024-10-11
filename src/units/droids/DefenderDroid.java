package units.droids;

import units.droidAbilities.defenderDroidAbilities.Endure;
import units.droidAbilities.defenderDroidAbilities.GrantShield;
import units.droidTypes.DroidType;
import units.droidTypes.droidSubtypes.DroidSubtype;
import units.droidTypes.droidSubtypes.DefenderDroidSubtype;

public class DefenderDroid extends Droid {
    protected DroidSubtype droidSubtype;

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

                this.addAbility(new Endure());
                break;
            case GUARDIAN:
                this.maxHP = 300;
                this.droidDMG = 0;
                this.droidSHD = 0;
                this.droidSPD = 3;

                this.addAbility(new GrantShield());
                break;
        }

        this.droidHP = this.maxHP;
    }

    public DroidSubtype getDroidSubtype() {return this.droidSubtype;}
}