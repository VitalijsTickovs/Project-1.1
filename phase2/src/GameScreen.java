import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;


public class GameScreen extends GameMenu{
    int size = 30;                                                                  //size of the fields for pentominoes
    Font scoreFnt = new Font("Arial", Font.BOLD, size);                      //init font
    Font namesScores = new Font("Arial", Font.PLAIN, size-10);

    HashMap<String, Integer> leaderboard = new SaveFile().ReadFromFile();             //reading stored logs in Save.txt file

    /**
     * Constructs game scene
     * @param g Graphics parameter used by RunGame instance
     */
    public void renderGScreen(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        //displaying score of the user
        g2d.setColor(Color.WHITE);
        g2d.setFont(scoreFnt);
        g2d.drawString("Score: " + RunGame.playerScore, 20, 100);

        //displaying leaderboard
        int offset = 0;                                                             //used to change the position of each line on y-axis
        g2d.drawString("Leaderboard", 20, 300);
        g2d.setFont(namesScores);
        for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) {
            g2d.drawString(entry.getKey() + ": " + entry.getValue(), 30, 350 + offset);
            offset += 50;
        }

        //displaying pause button
        g2d.setColor(RunGame.pauseColor);
        g2d.setFont(scoreFnt);
        String pauseSelection;
        if(RunGame.pause) pauseSelection = "Start"; else pauseSelection = "Pause";
        g2d.drawString(pauseSelection, 350,80);

        //Draw lines for field
        g2d.setColor(Color.white);
        for (int i = 0; i <= RunGame.getField()[i].length; i++){
            g2d.drawLine((i * size) + 350, 100, (i * size) + 350, RunGame.getField().length * size +100);
        }
        for (int i = 0; i <= RunGame.getField().length; i++){
            g2d.drawLine(350, i * size +100, (RunGame.getField()[0].length * size)+350, i * size +100);
        }
        //draw blocks of dropping pentominoes
        for (int i = 0; i < RunGame.getField()[0].length; i++) {
            for (int j = 0; j < RunGame.getField().length; j++) {
                g2d.setColor(GetColorOfID(RunGame.getField()[j][i]));
                g2d.fill(new Rectangle2D.Double(i * size + 351, j * size + 101, size - 1, size - 1));
            }
        }

        //displaying next piece
        g2d.setColor(Color.white);
        g2d.setFont(scoreFnt);
        g2d.drawString("Next Piece:", 20, 150);

        int[][] newPiece = PentominoDatabase.data[CharToID.characterToID(RunGame.nextpiece)][0];

        for (int i = 0; i <= newPiece[0].length; i++){
            g2d.drawLine((i * size) + 200, 135, (i * size) + 200, newPiece.length * size +135);
        }

        for (int i = 0; i <= newPiece.length; i++){
            g2d.drawLine(200, i * size +135, (newPiece[0].length * size)+200, i * size +135);
        }
        //draw blocks
        for (int i = 0; i < newPiece[0].length; i++) {
            for (int j = 0; j < newPiece.length; j++) {
                g2d.setColor(GetColorOfID(newPiece[j][i]));
                g2d.fill(new Rectangle2D.Double(i * size + 201, j * size + 136, size - 1, size - 1));
            }
        }



    }

    /**
     * Used to determine what should piece be colored
     * @param i ID of the character
     * @return Color of the shape
     */
    private Color GetColorOfID(int i){
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
