import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_RIGHT) {
                RunGame.right();
            } else if (key == KeyEvent.VK_LEFT) {
                RunGame.left();
            } else if (key == KeyEvent.VK_DOWN) {
                RunGame.lenientRotate();
            }else if(key == KeyEvent.VK_SPACE && !RunGame.pause){
                RunGame.down(15);
            }
    }

}
