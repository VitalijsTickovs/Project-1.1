package renderer;

import renderer.mouseInput.Mouse;
import renderer.point.Converter;
import renderer.point.Points;
import renderer.shapes.EntityManager;
import renderer.shapes.MyPolygon;
import renderer.shapes.TetraHedron;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Visualisor extends Canvas implements Runnable{

    private Thread thread;
    private JFrame frame;
    private static String title = "3D Render";
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;
    private static boolean running = false;

    private EntityManager entityManager;

    private Mouse mouse;

    public Visualisor(){
        this.frame = new JFrame(title);

        Dimension size = new Dimension(WIDTH, HEIGTH);
        this.frame.setPreferredSize(size);

        this.mouse = new Mouse();

        this.entityManager = new EntityManager();

        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
    }

    public static void main(String[] args){
        Visualisor visualise = new Visualisor();

        visualise.frame.add(visualise);
        visualise.frame.pack();
        visualise.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualise.frame.setLocationRelativeTo(null);
        visualise.frame.setResizable(false);
        visualise.frame.setVisible(true);

        visualise.start();
    }

    public synchronized void start(){
        running = true;
        this.thread = new Thread(this,"renderer.Visualisor");
        this.thread.start();
    }

    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000/ 60;
        double delta = 0;
        int frames = 0;

        int[][][] arr = new int[8][5][33];                  //Just wrote it for an example
        this.entityManager.init();                       //Pass 3d array through here

        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                update();
                render();
                frames++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >1000){
                timer+=1000;
                this.frame.setTitle(title + " | " + frames + " fps");
                frames = 0;
            }
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGTH);

        this.entityManager.render(g);

        g.dispose();
        bs.show();

    }

    private void update() {
        this.entityManager.update(this.mouse);
    }
}
