import java.awt.*;
import java.awt.geom.Rectangle2D;


public class GameScreen{
    Font scoreFnt = new Font("Arial", Font.BOLD, 30);
    public Rectangle playArea = new Rectangle((RunGame.height*4/2) +100,100,25,15*5);
    public Dimension gameScreen = new Dimension(RunGame.height * 4, RunGame.width * RunGame.scale);
    int size = 30;

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        for (int i = 0; i <= RunGame.field.getField()[i].length; i++){
            g2d.drawLine((i * size) + 300, 100, (i * size) + 300, RunGame.field.getField().length * size +100);
        }
        for (int i = 0; i <= RunGame.field.getField().length; i++){
            g2d.drawLine(300, i * size +100, (RunGame.field.getField()[0].length * size)+300, i * size +100);
        }
        //draw blocks
        for (int i = 0; i < RunGame.field.getField()[0].length; i++) {
            for (int j = 0; j < RunGame.field.getField().length; j++) {
                g2d.setColor(GetColorOfID(RunGame.field.getField()[j][i]));
                g2d.fill(new Rectangle2D.Double(i * size + 301, j * size + 101, size - 1, size - 1));
            }
        }

        //displaying score of the user
        g2d.setColor(Color.WHITE);
        g2d.setFont(scoreFnt);
        g2d.drawString("Score: " + RunGame.playerScore, 20, 100);

        int[][] newPiece = PentominoDatabase.data[CharToID.characterToID(RunGame.bag.nextPiece())][0];

        for (int i = 0; i < newPiece[0].length; i++){
            g2d.drawLine((i * size) + 150, 50, (i * size) + 150, newPiece.length * size +50);
        }

        for (int i = 0; i < newPiece.length; i++){
            g2d.drawLine(150, i * size +50, (newPiece[0].length * size)+150, i * size +50);
        }
        System.out.println(RunGame.bag.nextPiece());
        //draw blocks
        for (int i = 0; i < newPiece[0].length; i++) {
            for (int j = 0; j < newPiece.length; j++) {
                g2d.setColor(GetColorOfID(newPiece[j][i]));
                g2d.fill(new Rectangle2D.Double(i * size + 101, j * size + 51, size - 1, size - 1));
            }
        }

    }
    private Color GetColorOfID(int i)
    {
        if(i==0) {return Color.BLUE;}
        else if(i==1) {return Color.ORANGE;}
        else if(i==2) {return Color.CYAN;}
        else if(i==3) {return Color.GREEN;}
        else if(i==4) {return Color.MAGENTA;}
        else if(i==5) {return Color.PINK;}
        else if(i==6) {return Color.RED;}
        else if(i==7) {return Color.YELLOW;}
        else if(i==8) {return new Color(0, 0, 0);}
        else if(i==9) {return new Color(0, 0, 100);}
        else if(i==10) {return new Color(100, 0,0);}
        else if(i==11) {return new Color(0, 100, 0);}
        else {return Color.LIGHT_GRAY;}
    }
}
