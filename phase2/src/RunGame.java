import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RunGame extends Canvas implements Runnable {
    public static final int height = 250;                   //game screen variables
    public static final int width = 400;
    public static final int scale = 2;
    public static final String title = "Tetris game";
    public static JFrame window = new JFrame(title);
    public static RunGame screen = new RunGame();

    public static Color playColor = Color.white;
    public static Color helpColor = Color.white;
    public static Color quitColor = Color.white;

    private final GameMenu gMenu = new GameMenu();          //objects for other scenes
    private final GameScreen gScreen = new GameScreen();
    private final GameOver oScreen = new GameOver();
    public static Field field = new Field(17, 12);
    public static pieceBag bag = new pieceBag();
    public static SaveFile save;

    public static boolean endGame = false;

    private boolean running = false;                        //this is for thread methods
    public static Thread thread;

    public BufferedImage bckgrd = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);      //the background image
    public BufferedImage menuTitle = null;

    public static int playerScore;

    public void KeyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            field.right();
        } else if (key == KeyEvent.VK_LEFT) {
            field.left();
        } else if (key == KeyEvent.VK_DOWN) {
            field.rotate();
        } else if (key == KeyEvent.VK_SPACE) {
            field.down();
        }
    }


    public static enum STATE {                               //this is made to keep at what states the user is on
        menu, help, game, gameOver
    }

    public static STATE scene = STATE.menu;                 //start with menu scene


    public void render() {                                  //this will output graphics, firstly adding bufferStrategy, to have it load 3 images ahead
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.drawImage(bckgrd, 0, 0, getWidth(), getHeight(), this);

        if (scene == STATE.menu) {                                              //this is the menu output
            g.drawImage(menuTitle, width * scale / 3, 0, this);
            gMenu.render(g);
        } else if (scene == STATE.game){                                        //this is the game screen
            gScreen.render(g);
        } else if (scene == STATE.gameOver) {
            oScreen.render(g);
            endGame = true;
            g.dispose();
            bs.show();
        }else if(scene == STATE.help){
            //add Help menu
        }
        g.dispose();
        bs.show();
        if(endGame){
            int input = JOptionPane.showConfirmDialog(null, "Save score?", "Game Over",0,0);
            if(input == 0) {
                String playerName = JOptionPane.showInputDialog(null, "Enter your name");
                save = new SaveFile(playerName ,playerScore, window);
            }else{
                window.dispatchEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSING));
            }
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

    public void init() {                                                          //initializing the game background and adding mouse listeners
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            bckgrd = loader.loadBufferedImage("GameBckrd.png");
            menuTitle = loader.loadBufferedImage("GameTitle.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addKeyListener(new KeyInput(this));
        addMouseListener(new MouseInput());
        addMouseMotionListener(new MouseDragging());
    }

    public synchronized void stop() throws InterruptedException {
        if (!running) return;
        running = false;
        thread.join();
        System.exit(0);
    }

    public void run() {
        init();
        long x1 = System.nanoTime();                //the time is needed to keep the threads 'under control' and to update the game screen not as frequently
        final double ammount_of_ticks = 60.0;
        double ms = 1000 / ammount_of_ticks;
        double delta = 0.0;
        int counter = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - x1) / ms;
            x1 = now;
            if (delta >= 1 && scene == STATE.game) {
                delta--;
                if (field.pieceID == -1) {
                    if (!field.AddPiece(bag.nextPiece())) {
                        scene = STATE.gameOver;
                        render();
                        try {
                            stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (counter % 300 == 0) {
                    if (!field.down()) {
                        field.setPiece();
                        playerScore += field.checkRows();
                    }
                }


                }
                counter++;
                render(); //this method will display everything
            }
        }


        public static void main (String[]args){
            screen.setPreferredSize(new Dimension(width * scale, width * scale));       //initializing game screen
            window.add(screen);
            window.pack();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            window.setResizable(false);
            window.setVisible(true);

            screen.start();                                                                         //Starting thread, meaning the game
        }


    }
