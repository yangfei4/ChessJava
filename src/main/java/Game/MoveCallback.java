package Game;

import javax.swing.JPanel;

public interface MoveCallback {
    void Action(JPanel square);
    void restartGame();
}