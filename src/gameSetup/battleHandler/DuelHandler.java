package gameSetup.battleHandler;

import gameSetup.droidGenerator.DroidGenerator;
import gameSetup.inputHandler.InputHandler;
import logger.Logger;
import units.droidAbilities.Ability;
import units.droidTypes.DroidType;
import units.droids.Droid;

import java.io.IOException;
import java.util.Random;

public class DuelHandler implements BattleHandler {
    private final Random random = new Random();

    private final InputHandler inputHandler = new InputHandler();
    private final DroidGenerator droidGenerator = new DroidGenerator();

    private final Logger logger = new Logger("./battleRecords/Duel.txt");
    private int currentTurn = 1;

    private Droid droidA;
    private Droid droidB;

    public DuelHandler() throws IOException {}

    @Override
    public void setUpBattle() {
        String droidTypeA = inputHandler.getDroidType();
        String droidSubtypeA = inputHandler.getDroidSubtype(droidTypeA);

        String droidTypeB = inputHandler.getDroidType();
        String droidSubtypeB = inputHandler.getDroidSubtype(droidTypeB);

        droidA = droidGenerator.produceDroid(droidTypeA, droidSubtypeA);
        droidB = droidGenerator.produceDroid(droidTypeB, droidSubtypeB);
    }

    @Override
    public void runBattle() throws IOException {
        try {
            Droid first, second;

            logger.recordAction("COMBATANT 1: " + droidA.toString() + "\n");
            logger.recordAction("COMBATANT 1, params: " + droidA.toStringLong() + "\n\n");
            logger.recordAction("COMBATANT 2: " + droidB.toString() + "\n");
            logger.recordAction("COMBATANT 2, params: " + droidB.toStringLong() + "\n\n");

            // set droid order
            if (droidA.getDroidSPD() > droidB.getDroidSPD()) {
                first = droidA;
                second = droidB;
            } else {
                first = droidB;
                second = droidA;
            }

            // run battle simulation
            while (droidA.isIntact() && droidB.isIntact()) {
                double firstAbilityCheck = random.nextDouble();
                double secondAbilityCheck = random.nextDouble();

                String firstDroidMove, secondDroidMove;

                // droid type check, because DEFENDER and SUPPORT cannot use abilities on themselves or enemies
                if (firstAbilityCheck > 0.5 && first.getDroidType() == DroidType.COMBAT) {
                    Ability firstDroidAbility = first.getAbility();
                    firstDroidAbility.useAbility(second);

                    firstDroidMove = "Droid "
                            + first.toStringShort() +
                            " used " +
                            firstDroidAbility.getAbilityName() +
                            " on " + second.toStringShort();
                } else {
                    first.attackEnemy(second);

                    firstDroidMove = "Droid "
                            + first.toStringShort() +
                            " attacked " +
                            second.toStringShort();
                }

                if (secondAbilityCheck > 0.5 && second.getDroidType() == DroidType.COMBAT) {
                    Ability secondDroidAbility = second.getAbility();
                    secondDroidAbility.useAbility(first);

                    secondDroidMove = "Droid "
                            + second.toStringShort() +
                            " used " +
                            secondDroidAbility.getAbilityName() +
                            " on " + first.toStringShort();
                } else {
                    second.attackEnemy(first);

                    secondDroidMove = "Droid "
                            + second.toStringShort() +
                            " attacked " +
                            first.toStringShort();
                }

                String turnRecord = "TURN " + currentTurn + ":\n";
                turnRecord += firstDroidMove + "\n" + secondDroidMove + "\n";

                logger.recordAction(turnRecord + "\n");
                currentTurn++;
            }
        }
        finally {
            if (droidA.isIntact()) {
                logger.recordAction("Droid " + droidA.toStringShort() + " emerged victorious!");
            } else {
                logger.recordAction("Droid " + droidB.toStringShort() + " emerged victorious!");
            }
            logger.closeLogger();
        }
    }
}