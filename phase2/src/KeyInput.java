import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_RIGHT) {
                RunGame.field.right();
            } else if (key == KeyEvent.VK_LEFT) {
                RunGame.field.left();
            } else if (key == KeyEvent.VK_DOWN) {
                RunGame.field.lenientRotate();
            }else if(key == KeyEvent.VK_SPACE && !RunGame.pause){
                RunGame.field.down(15);
            }
    }

}
