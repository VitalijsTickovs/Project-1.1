import java.awt.*;

public class GameOver extends HelpScreen {
    private Font fnt = new Font("Arial", Font.BOLD, 100);

    /**
     * Constructs game over scene
     * @param g Graphics parameter used by RunGame instance
     */
    public void renderOver(Graphics g){
        g.setFont(fnt);
        g.setColor(Color.WHITE);
        g.drawString("Game Over", (RunGame.height*RunGame.scale)/2 -150, (RunGame.width*RunGame.scale)/2);
        g.dispose();
    }
}
