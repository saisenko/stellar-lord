package units.droids;

import units.CombatStatus;
import units.droidAbilities.Ability;
import units.droidTypes.DroidType;

import java.util.ArrayList;
import java.util.List;

public class Droid {
    protected String droidName;
    protected DroidType droidType;

    protected CombatStatus combatStatus;

    protected int maxHP;                          // droid's max health points
    protected int droidHP;                        // droid's current health points
    protected int droidDMG;                       // droid's damage
    protected int droidSHD;                       // droid's shield value
    protected int droidSPD;                       // droid's speed (a.k.a. initiative)

    public boolean canSupport = false;
    protected List<Ability> abilities;

    public Droid(
            String droidName,
            DroidType droidType
    ) {
        this.droidName = droidName;
        this.droidType = droidType;

        this.combatStatus = CombatStatus.INTACT;
        this.abilities = new ArrayList<>();
    }

    public boolean isIntact() {return this.combatStatus == CombatStatus.INTACT;}
    public void disconnect() {this.combatStatus = CombatStatus.OUT_OF_COMBAT;}

    public int getMaxHP() {return this.maxHP;}
    public int getDroidHP() {return this.droidHP;}
    public int getDroidDMG() {return droidDMG;}
    public int getDroidSHD() {return droidSHD;}
    public int getDroidSPD() {return droidSPD;}

    public DroidType getDroidType() {return this.droidType;}
    public Ability getAbility() {return this.abilities.getFirst();}

    public void setDroidHP(int droidHP) {this.droidHP = Math.min(droidHP, this.maxHP);}
    public void setDroidDMG(int droidDMG) {this.droidDMG = Math.max(droidDMG, 0);}
    public void setDroidSHD(int droidSHD) {this.droidSHD = Math.max(droidSHD, 0);}
    public void setDroidSPD(int droidSPD) {this.droidSPD = droidSPD;}

    public void addAbility(Ability ability) {this.abilities.add(ability);}
    /**
     * Process incoming damage. If shield is active, damage/destroy it.
     * If droid HP fell below 0, disconnect droid.
     *
     * @param incomeDMG incoming damage from basic attack and/or ability
     */
    public void processReceivedDMG(int incomeDMG) {
        int droidHP = getDroidHP();
        int droidSHD = getDroidSHD();

        if (incomeDMG >= droidSHD) {
            setDroidSHD(0);
            int leftoverDMG = Math.abs(droidSHD - incomeDMG);
            setDroidHP(droidHP - leftoverDMG);
        } else {
            setDroidSHD(droidSHD - incomeDMG);
        }

        int newHP = getDroidHP();
        if (newHP <= 0) {
            disconnect();
        }
    }

    public void attackEnemy(Droid targetDroid) {
        int damage = this.getDroidDMG();
        targetDroid.processReceivedDMG(damage);
    }

    public void supportAlly(Droid targetDroid) {
        if (!canSupport) return;

        Ability droidAbility = abilities.getFirst();
        droidAbility.useAbility(targetDroid);
    }

    @Override
    public String toString() {
        return "Battle Droid <" +
               this.droidName +
               "> | Additional Intel: {Droid Type: " +
               this.droidType +
               "}";
    }

    public String toStringShort() {
        return "Battle Droid <" + this.droidName + ">";
    }

    public String toStringLong() {
        String generalInfo = "Battle Droid <" +
                             this.droidName +
                             "> | Additional Intel: {Droid Type: " +
                             this.droidType +
                             "}\n";
        String droidParams = "Unit Stats: {MAX_HP: " +
                             this.maxHP +
                             " / HP: " +
                             this.droidHP +
                             " / DMG: " +
                             this.droidDMG +
                             " / SHD: " +
                             this.droidSHD +
                             " / SPD: " +
                             this.droidSPD + "}";

        return generalInfo + droidParams;
    }
}