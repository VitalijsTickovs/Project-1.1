package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RunGame extends Canvas implements Runnable{
    public static final int height = 250;                   //game screen variables
    public static final int width = 400;
    public static final int scale = 2;
    public static final String title = "Tetris game";
    public static JFrame window = new JFrame(title);
    public static RunGame screen = new RunGame();

    public static int [][] pentLoc = new int[15][5];        //this is game matrix, where pentominos will be

    public static Color playColor = Color.white;
    public static Color helpColor = Color.white;
    public static Color quitColor = Color.white;

    private final GameMenu gMenu = new GameMenu();          //objects for other scenes
    private final GameScreen gScreen = new GameScreen();

    private boolean running = false;                        //this is for thread methods
    public static Thread thread;

    public BufferedImage bckgrd = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);      //the bacground image
    public BufferedImage menuTitle = null;


    public enum STATE{                               //this is made to keep at what states the user is on
        menu, help, game
    }
    public static STATE state = STATE.menu;                 //start with menu scene



    public void render() {                                  //this will output graphics, firstly adding bufferStrategy, to have it load 3 images ahead
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.drawImage(bckgrd,0,0,getWidth(),getHeight(),this);

        if (state == STATE.menu) {                                              //this is the menu output
            g.drawImage(menuTitle,width*scale/3,0,this);
            gMenu.render(g);
        } else if (state == STATE.game) {                                       //this is the game screen
            gScreen.render(g);
        }
        g.dispose();
        bs.show();
    }

    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void init(){                                                          //initializing the game background and adding mouse listeners
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            bckgrd = loader.loadBufferedImage("GameBckrd.png");
            menuTitle = loader.loadBufferedImage("GameTitle.png");
        }catch (IOException e){
            e.printStackTrace();
        }
        for (int i = 0; i < pentLoc[i].length; i++)
        {
            for (int j = 0; j < pentLoc.length; j++)
            {
                pentLoc[j][i] = -1;
            }
        }
        addMouseListener(new MouseInput());
        addMouseMotionListener(new MouseDragging());
    }

    public synchronized void stop() throws InterruptedException {
        if(!running) return;
        running = false;
        thread.join();
        System.exit(0);
    }

    public void run(){
        init();
        long x1 = System.nanoTime();                //the time is needed to keep the threads 'under control' and to update the game screen not as frequently
        final double ammount_of_ticks = 60.0;
        double ns = 1000000000/ammount_of_ticks;
        double delta = 0.0;
        while(running){
            long now = System.nanoTime();
            delta += (now-x1)/ns;
            x1=now;
            if(delta>=1){
                delta--;
                //The game physics should be here
            }
            render(); //this method will display everything
        }
        try {           //when the game finishes it will close all the threads
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        screen.setPreferredSize(new Dimension(width * scale, width * scale));       //initializing game screen
        window.add(screen);
        window.getContentPane().setBackground(Color.black);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);

        screen.start();                                                                         //Starting thread, meaning the game
    }



}
