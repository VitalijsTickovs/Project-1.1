package renderer;

import renderer.mouseInput.Mouse;
import renderer.shapes.EntityManager;

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

    private int timeToTake;
    private int triesToTake;
    private boolean isBoxes;

    private EntityManager entityManager;

    private Mouse mouse;

    public Visualisor(int timeToTake, int triesToTake, boolean isBoxes){
        this.timeToTake = timeToTake;
        this.triesToTake = triesToTake;
        this.isBoxes = isBoxes;

        this.frame = new JFrame(title);

        Dimension size = new Dimension(WIDTH, HEIGTH);
        this.frame.setPreferredSize(size);

        this.mouse = new Mouse();

        this.entityManager = new EntityManager();

        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
    }

    public static void init(int timeToTake, int triesToTake, boolean isBoxes){
        Visualisor visualise = new Visualisor(timeToTake, triesToTake, isBoxes);

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

        int[][][] arr = DancingRun3D.getSolution(timeToTake, triesToTake, isBoxes);
        // first parameter is number of milliseconds per search, second parameter is number of tries to search
        //third parameter is true if boxes, false if pentominoes.
        this.entityManager.init(arr);

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
