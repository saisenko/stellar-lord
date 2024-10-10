package gameSetup.battleHandler;

import gameSetup.droidGenerator.DroidGenerator;
import gameSetup.inputHandler.InputHandler;
import units.droids.Droid;

public class DuelHandler implements BattleHandler {
    private final InputHandler inputHandler = new InputHandler();
    private final DroidGenerator droidGenerator = new DroidGenerator();

    private Droid droidA;
    private Droid droidB;

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
    public void runBattle() {
        while (droidA.isIntact() || droidB.isIntact()) {
            System.out.println("Ongoing battle...");
        }
    }
}