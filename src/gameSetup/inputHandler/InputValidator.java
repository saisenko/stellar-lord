package gameSetup.inputHandler;

public class InputValidator {
    public boolean battleModeIsValid(String userChoice) {
        return userChoice.equals("a") || userChoice.equals("b");
    }

    /**
     * Assert that DroidType/DroidSubtype matches one of the 3 possible options
     */
    public boolean droidIsValid(String userChoice) {
        return userChoice.equals("a") || userChoice.equals("b") || userChoice.equals("c");
    }
}