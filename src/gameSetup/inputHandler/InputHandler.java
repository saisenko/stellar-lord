package gameSetup.inputHandler;

import java.util.Scanner;

public class InputHandler {
    Scanner scanner = new Scanner(System.in);
    InputValidator validator = new InputValidator();

    public String getBattleMode() {
        System.out.println("Select battle mode:");
        System.out.println("\ta) <Duel>");
        System.out.println("\tb) <Combat>");

        String userChoice = scanner.nextLine();
        while (!validator.battleModeIsValid(userChoice)) {
            userChoice = scanner.nextLine();
        }
        return userChoice;
    }

    public String getDroidType() {
        System.out.println("Select droid type:");
        System.out.println("\ta) <Combat droid>");
        System.out.println("\tb) <Defender droid>");
        System.out.println("\tc) <Support droid>");

        String typeChoice = scanner.nextLine();
        while (!validator.droidIsValid(typeChoice)) {
            System.out.println("Invalid option. Try again.");
            typeChoice = scanner.nextLine();
        }

        return typeChoice;
    }

    public String getDroidSubtype(String droidType) {
        System.out.println("Select droid sub-type:");

        switch (droidType) {
            case "a":
                System.out.println("\ta) <Assault droid>");
                System.out.println("\tb) <Suicide Bomber droid>");
                break;
            case "b":
                System.out.println("\ta) <Armored droid>");
                System.out.println("\tb) <Guardian droid>");
                break;
            case "c":
                System.out.println("\ta) <Medic droid>");
                System.out.println("\tb) <Tactician droid>");
                System.out.println("\tc) <Enhancer droid>");
                break;
        }

        String subtypeChoice = scanner.nextLine();
        while (!validator.droidIsValid(subtypeChoice)) {
            System.out.println("Invalid option. Try again.");
            subtypeChoice = scanner.nextLine();
        }

        return subtypeChoice;
    }
}