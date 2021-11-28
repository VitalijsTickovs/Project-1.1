import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    RunGame game;

    public KeyInput(RunGame game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.KeyPressed(e);
    }

}
