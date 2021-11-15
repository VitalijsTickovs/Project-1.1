package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends Canvas implements Runnable{
    public static final int height = 250;
    public static final int width = 400;
    public static final int scale = 2;
    public static final String title = "Tetris game";
    public static int [][] pentLoc = new int[15][5];
    public boolean inf = true;

    public static Color playColor = Color.white;
    public static Color helpColor = Color.white;
    public static Color quitColor = Color.white;

    private final GameMenu gMenu = new GameMenu();
    private final GameScreen gScreen = new GameScreen();

    private boolean running = false;
    public static Thread thread;

    public static JFrame window = new JFrame(title);
    public static Menu screen = new Menu();

    public BufferedImage bckgrd = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    public BufferedImage menuTitle = null;


    public static enum STATE{
        menu, help, game
    }
    public static STATE state = STATE.menu;



    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.drawImage(bckgrd,0,0,getWidth(),getHeight(),this);

        if (state == STATE.menu) {
            g.drawImage(menuTitle,width*scale/3,0,this);
            gMenu.render(g);
        } else if (state == STATE.game) {
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

    public void init(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            bckgrd = loader.loadBufferedImage("GameScreen.png");
            menuTitle = loader.loadBufferedImage("Images.png");
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
        long x1 = System.nanoTime();
        final double ammount_of_ticks = 60.0;
        double ns = 1000000000/ammount_of_ticks;
        double delta = 0.0;
        //Graphics g = getGraphics();
        while(running){
            long now = System.nanoTime();
            delta += (now-x1)/ns;
            x1=now;
            if(delta>=1){
                delta--;

            }
            render();
        }
        try {
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        screen.setPreferredSize(new Dimension(width * scale, width * scale));
        window.add(screen);
        window.getContentPane().setBackground(Color.black);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);

        screen.start();
        /*Menu screen = new Menu();

        screen.setPreferredSize(new Dimension(width * scale, height * scale));

        JFrame window = new JFrame(Menu.title);
        window.add(screen);
        window.getContentPane().setBackground(Color.black);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);*/
    }



}
