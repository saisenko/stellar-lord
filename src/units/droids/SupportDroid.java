package units.droids;

import units.droidAbilities.Ability;
import units.droidAbilities.supportDroidAbilities.Inspire;
import units.droidAbilities.supportDroidAbilities.RaiseMorale;
import units.droidAbilities.supportDroidAbilities.Repair;
import units.droidTypes.DroidType;
import units.droidTypes.droidSubtypes.DroidSubtype;
import units.droidTypes.droidSubtypes.SupportDroidSubtype;

import java.util.ArrayList;
import java.util.List;

public class SupportDroid extends Droid {
    protected DroidSubtype droidSubtype;
    protected List<Ability> abilities = new ArrayList<>();

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

                abilities.add(new Repair());
                break;
            case TACTICIAN:
                this.maxHP = 85;
                this.droidDMG = 15;

                abilities.add(new RaiseMorale());
                break;
            case ENHANCER:
                this.maxHP = 75;
                this.droidDMG = 15;

                abilities.add(new Inspire());
                break;
        }

        this.droidHP = this.maxHP;
        this.droidSHD = 0;
        this.droidSPD = 4;
    }
}