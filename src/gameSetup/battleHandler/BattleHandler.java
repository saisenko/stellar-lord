package gameSetup.battleHandler;

import java.io.IOException;

public interface BattleHandler {
    void setUpBattle();
    void runBattle() throws IOException;
}