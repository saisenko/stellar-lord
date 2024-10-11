import gameSetup.battleHandler.CombatHandler;
import gameSetup.battleHandler.DuelHandler;
import gameSetup.inputHandler.InputHandler;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void displayRecord(String recordName) throws IOException {
        String fullPath = "./battleRecords/" + recordName;

        Scanner recordReader = new Scanner(Paths.get(fullPath));
        while (recordReader.hasNextLine()) {System.out.println(recordReader.nextLine());}
        recordReader.close();
    }

    public static void main(String[] args) throws IOException {
        InputHandler inputHandler = new InputHandler();

        String battleMode = inputHandler.getBattleMode();

        switch (battleMode) {
            case "a":
                DuelHandler duelHandler = new DuelHandler();
                duelHandler.setUpBattle();
                duelHandler.runBattle();

                System.out.println("BATTLE MODE: <DUEL>");
                displayRecord("Duel.txt");
                break;
            case "b":
                CombatHandler combatHandler = new CombatHandler();
                combatHandler.setUpBattle();
                combatHandler.runBattle();

                System.out.println("BATTLE MODE: <COMBAT>");
                System.out.println("=========================================================");
                displayRecord("Combat.txt");
                break;
        }
    }
}