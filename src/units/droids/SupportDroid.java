package units.droids;

import units.droidAbilities.supportDroidAbilities.Inspire;
import units.droidAbilities.supportDroidAbilities.RaiseMorale;
import units.droidAbilities.supportDroidAbilities.Repair;
import units.droidTypes.DroidType;
import units.droidTypes.droidSubtypes.DroidSubtype;
import units.droidTypes.droidSubtypes.SupportDroidSubtype;

public class SupportDroid extends Droid {
    protected DroidSubtype droidSubtype;

    public SupportDroid(
        String droidName,
        DroidType droidType,
        SupportDroidSubtype droidSubtype
    ) {
        super(droidName, droidType);
        this.droidSubtype = droidSubtype;

        switch (droidSubtype) {
            case MEDIC:
                this.maxHP = 80;
                this.droidDMG = 15;

                this.addAbility(new Repair());
                break;
            case TACTICIAN:
                this.maxHP = 85;
                this.droidDMG = 15;

                this.addAbility(new RaiseMorale());
                break;
            case ENHANCER:
                this.maxHP = 75;
                this.droidDMG = 15;

                this.addAbility(new Inspire());
                break;
        }

        this.droidHP = this.maxHP;
        this.droidSHD = 0;
        this.droidSPD = 4;
    }

    public DroidSubtype getDroidSubtype() {return this.droidSubtype;}
}