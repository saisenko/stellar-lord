package gameSetup.battleHandler;

import gameSetup.droidGenerator.DroidGenerator;
import gameSetup.inputHandler.InputHandler;
import logger.Logger;

import units.droidAbilities.Ability;

import units.droids.DefenderDroid;
import units.droids.Droid;
import units.droids.SupportDroid;

import units.droidTypes.droidSubtypes.*;
import units.droidTypes.droidSubtypes.SupportDroidSubtype;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class CombatHandler implements BattleHandler {
    private final Random random = new Random();

    private final InputHandler inputHandler = new InputHandler();
    private final DroidGenerator droidGenerator = new DroidGenerator();

    private final Logger logger = new Logger("./battleRecords/Combat.txt");
    private int currentTurn = 1;
    private String turnRecord = "";

    private final List<Droid> droidTeamA = new ArrayList<>();
    private final List<Droid> droidTeamB = new ArrayList<>();
    private final BattleOptions battleOptions = new BattleOptions();

    public CombatHandler() throws IOException {}

    public boolean teamIntact(String team) {
        List<Droid> teamToCheck = switch (team) {
            case "A" -> droidTeamA;
            case "B" -> droidTeamB;
            default -> throw new IllegalArgumentException("Invalid team");
        };

        for (Droid droid : teamToCheck) {
            if (!droid.isIntact()) {
                return false;
            }
        }

        return true;
    }

    public String getActionRecord(Droid droid, Droid target, Ability droidAbility, boolean usedAbility) {
        if (usedAbility) {
            return "Droid " +
                    droid.toStringShort() +
                    " used " +
                    droidAbility.getAbilityName() +
                    " on " + target.toStringShort();
        } else {
            return "Droid " +
                    droid.toStringShort() +
                    " attacked " +
                    target.toStringShort();
        }
    }


    public void combatAction(Droid droid, List<Droid> targetTeam) {
        double droidAbilityCheck = random.nextDouble();
        Droid weakestTarget = battleOptions.findWeakestDroid(targetTeam);
        if (droidAbilityCheck > 0.5) {
            Ability droidAbility = droid.getAbility();
            droidAbility.useAbility(weakestTarget);

            this.turnRecord = getActionRecord(droid, weakestTarget, droidAbility, true);
        } else {
            droid.attackEnemy(weakestTarget);

            this.turnRecord = getActionRecord(droid, weakestTarget, null, false);
        }
    }

    public void defenderAction(DefenderDroid droid, List<Droid> targetTeam) {
        Droid target;
        Ability droidAbility;
        double droidAbilityCheck = random.nextDouble();

        if (droidAbilityCheck > 0.5) {
            switch (droid.getDroidSubtype()) {
                case DefenderDroidSubtype.ARMORED:
                    target = battleOptions.findMostAggressiveDroid(targetTeam);
                    droidAbility = droid.getAbility();
                    droidAbility.useAbility(target);

                    this.turnRecord = getActionRecord(droid, target, droidAbility, true);
                    break;
                case DefenderDroidSubtype.GUARDIAN:
                    target = battleOptions.findMostEndangeredDroid(targetTeam);
                    droidAbility = droid.getAbility();
                    droidAbility.useAbility(target);

                    this.turnRecord = getActionRecord(droid, target, droidAbility, true);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + droid.getDroidSubtype());
            }
        } else {
            Droid mostAliveTarget = battleOptions.findStrongestDroid(targetTeam);
            droid.attackEnemy(mostAliveTarget);
            this.turnRecord = getActionRecord(droid, mostAliveTarget, null, false);
        }

    }

    public void supportAction(SupportDroid droid, List<Droid> targetTeam) {
        Droid target;
        Ability droidAbility = droid.getAbility();
        double droidAbilityCheck = random.nextDouble();

        if (droidAbilityCheck > 0.5) {
            switch (droid.getDroidSubtype()) {
                case SupportDroidSubtype.MEDIC:
                    target = battleOptions.findWeakestDroid(targetTeam);
                    droidAbility.useAbility(target);
                    break;
                case SupportDroidSubtype.TACTICIAN, SupportDroidSubtype.ENHANCER:
                    for (Droid d : targetTeam) {
                        droidAbility.useAbility(d);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + droid.getDroidSubtype());
            }
        } else {
            Droid weakestTarget = battleOptions.findWeakestDroid(targetTeam);
            droid.attackEnemy(weakestTarget);
            this.turnRecord = getActionRecord(droid, weakestTarget, null, false);
        }
    }

    @Override
    public void setUpBattle() {
        System.out.println("Assembling team A...");
        for (int i = 0; i < 5; i++) {
            String droidType = inputHandler.getDroidType();
            String droidSubtype = inputHandler.getDroidSubtype(droidType);
            droidTeamA.add(droidGenerator.produceDroid(droidType, droidSubtype));
        }

        System.out.println("Assembling team B...");
        for (int i = 0; i < 5; i++) {
            String droidType = inputHandler.getDroidType();
            String droidSubtype = inputHandler.getDroidSubtype(droidType);
            droidTeamB.add(droidGenerator.produceDroid(droidType, droidSubtype));
        }
    }

    @Override
    public void runBattle() throws IOException {
        try {
            List<Droid> droids = new ArrayList<>(droidTeamA);
            droids.addAll(droidTeamB);
            droids.sort(Comparator.comparing(Droid::getDroidSPD));

            while (teamIntact("A") && teamIntact("B")) {
                logger.recordAction("TURN " + currentTurn + "\n");
                for (Droid droid : droids) {
                    String droidTeam = (droidTeamA.contains(droid)) ? "A" : "B";

                    List<Droid> targetTeam;
                    switch (droid.getDroidType()) {
                        case COMBAT:
                            targetTeam = (droidTeam.equals("A")) ? droidTeamB : droidTeamA;
                            combatAction(droid, targetTeam);
                            break;
                        case DEFENDER:
                            DefenderDroidSubtype droidSubtype = (DefenderDroidSubtype) ((DefenderDroid) droid).getDroidSubtype();
                            targetTeam = switch (droidSubtype) {
                                case GUARDIAN -> (droidTeam.equals("A")) ? droidTeamB : droidTeamA;
                                case ARMORED -> (droidTeam.equals("A")) ? droidTeamA : droidTeamB;
                            };
                            defenderAction((DefenderDroid) droid, targetTeam);
                            break;
                        case SUPPORT:
                            targetTeam = (droidTeam.equals("A")) ? droidTeamA : droidTeamB;
                            supportAction((SupportDroid) droid, targetTeam);
                    }
                    logger.recordAction("Team" + droidTeam + ": " + this.turnRecord + "\n");
                }
                currentTurn++;
            }
        } finally {
            if (teamIntact("A")) {
                logger.recordAction("Team A emerged victorious!");
            } else {
                logger.recordAction("Team B emerged victorious!");
            }
            logger.closeLogger();
        }
    }
}
