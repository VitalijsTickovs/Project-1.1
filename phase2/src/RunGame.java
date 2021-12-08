import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RunGame extends Field implements Runnable {
    public static final int height = 250;                   //game screen variables
    public static final int width = 400;
    public static final int scale = 2;
    public static final String title = "Tetris game";
    public static JFrame window = new JFrame(title);
    public static RunGame game = new RunGame();

    public static Color playColor = Color.white;            //variables that will change the color of buttons
    public static Color helpColor = Color.white;
    public static Color quitColor = Color.white;
    public static Color pauseColor = Color.white;
    public static Color backColor = Color.white;

    private final GameOver Screens = new GameOver();        //game scene object for rendering

    public static char nextpiece;

    BufferedImageLoader loader = new BufferedImageLoader();

    public static boolean endGame = false;                  //booleans
    public static boolean pause = false;

    private boolean running = false;                        //this is for thread methods
    public static Thread thread;

    public BufferedImage bckgrd = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);      //images that will be used
    public BufferedImage menuTitle = null;
    public static BufferedImage rotate = null;
    public static BufferedImage moving = null;
    public static BufferedImage drop = null;

    public static int playerScore;

    public enum STATE {                               //this is made to keep at what states the user is on
        menu, help, game, gameOver
    }

    public static STATE scene = STATE.menu;                 //start with menu scene


    /**
     * Method used to output graphics, depending on switch scene is currently user in
     */
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.drawImage(bckgrd, 0, 0, getWidth(), getHeight(), this);

        if (scene == STATE.menu) {                                              //menu output
            g.drawImage(menuTitle, 200, 0,300, 250, this);
            Screens.renderMenu(g);
        } else if (scene == STATE.game){                                        //game screen
            Screens.renderGScreen(g);
        }else if(scene == STATE.help){                                          //help screen
            Screens.renderHelp(g);
        } else if (scene == STATE.gameOver) {                                   //endgame phase
            Screens.renderOver(g);
            endGame = true;
            bs.show();
        }
        g.dispose();
        bs.show();

        //outputting a window for user to save his score and name
        if(endGame){
            //storing user's decision
            int input = JOptionPane.showConfirmDialog(null, "Save score?", "Game Over",0,0);
            //if user chose yes, to save his progress
            if(input == 0) {
                String playerName = JOptionPane.showInputDialog(null, "Enter your name");
                //if user rethought his decision, so he left his playerName empty
                if(playerName != null)  new SaveFile(playerName, playerScore);
            }
                System.exit(0);         //quit on save
        }

    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Method to initialize game images, keyboard and mouse listeners, game field
     */
    public void init(){
        try {
            bckgrd = loader.loadBufferedImage("/Images/GameBckrd.png");
            menuTitle = loader.loadBufferedImage("/Images/GameTitle.png");
            rotate = loader.loadBufferedImage("/Images/Rotation.png");
            moving = loader.loadBufferedImage("/Images/Moving.png");
            drop = loader.loadBufferedImage("/Images/Drop.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Field(15,5);
        nextpiece = nextPiece();
        addKeyListener(new MouseDragging());
        addMouseListener(new MouseDragging());
        addMouseMotionListener(new MouseDragging());
    }


    /**
     * Stops the thread, when game is finished
     */
    public synchronized void stop() throws InterruptedException {
        if (!running) return;
        running = false;
        thread.join();
        System.exit(0);
    }

    /**
     * Method in which game loop is made
     */
    public void run() {
        init();
        long x1 = System.currentTimeMillis();                //the time is needed to keep the threads 'under control' and to update the game screen not as frequently
        final double amount_of_ticks = 60.0;
        double ms = 1000 / amount_of_ticks;
        double delta = 0.0;
        boolean nextPieceAdded = false;
        boolean pieceMoved = false;
        while (running) {
            long now = System.currentTimeMillis();          //counting how much time passed
            delta += (now - x1) / ms;
            x1 = now;
            if (delta >= 1 && scene == STATE.game && !pause) {
                delta--;
                if (pieceID == -1) {
                    if (!AddPiece(nextpiece)) {
                        scene = STATE.gameOver;
                        render();
                        try {
                            stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(!nextPieceAdded) {
                    nextpiece = nextPiece();
                    nextPieceAdded = true;
                }
                if (System.currentTimeMillis()% 1000 <= 40 && !pieceMoved) {
                    pieceMoved=true;
                    if (!down()) {
                        setPiece();
                        playerScore += checkRows();
                        nextPieceAdded = false;
                    }
                }
                if(pieceMoved && System.currentTimeMillis()%1000 > 50){
                    pieceMoved =  false;
                }
            }
                render(); //this method will display everything
            }
        }

    /**
     * Method used to launch the game
     */
    public static void main (String[]args){
            game.setPreferredSize(new Dimension(width * scale, width * scale));       //initializing game screen
            window.add(game);
            window.pack();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            window.setResizable(false);
            window.setVisible(true);

            game.start();                                                                         //Starting thread, meaning the game
        }


    }
