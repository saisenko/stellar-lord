import gameSetup.inputHandler.InputHandler;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();

        String battleMode = inputHandler.getBattleMode();

        switch (battleMode) {
            case "a":
                System.out.println("A");
                break;
            case "b":
                System.out.println("B");
                break;
        }
    }
}