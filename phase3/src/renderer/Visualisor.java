package renderer;

import renderer.mouseInput.Mouse;
import renderer.shapes.AlgorithmType.AlgorithmsTypes;
import renderer.shapes.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static renderer.heuristic.*;

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
    private int lPackages;
    private int pPackages;
    private int tPackages;
    private int lValues;
    private int pValues;
    private int tValues;

    private AlgorithmsTypes algorithm;

    private EntityManager entityManager;

    private Mouse mouse;

    public Visualisor(int timeToTake, int triesToTake, boolean isBoxes, AlgorithmsTypes algorithm){
        this.timeToTake = timeToTake;
        this.triesToTake = triesToTake;
        this.isBoxes = isBoxes;
        this.algorithm = algorithm;

        this.frame = new JFrame(title);

        Dimension size = new Dimension(WIDTH, HEIGTH);
        this.frame.setPreferredSize(size);

        this.mouse = new Mouse();

        this.entityManager = new EntityManager();

        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
    }

    public Visualisor(int lPackages, int pPackages, int tPackages, int lValues, int pValues, int tValues, AlgorithmsTypes algorithm){
        this.lPackages = lPackages;
        this.pPackages = pPackages;
        this.tPackages = tPackages;
        this.lValues = lValues;
        this.pValues = pValues;
        this.tValues = tValues;
        this.algorithm = algorithm;

        this.frame = new JFrame(title);

        Dimension size = new Dimension(WIDTH, HEIGTH);
        this.frame.setPreferredSize(size);

        this.mouse = new Mouse();

        this.entityManager = new EntityManager();

        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
    }

    public static void init(int timeToTake, int triesToTake, boolean isBoxes, AlgorithmsTypes algorithm){
        Visualisor visualise = new Visualisor(timeToTake, triesToTake, isBoxes, algorithm);

        visualise.frame.add(visualise);
        visualise.frame.pack();
        visualise.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualise.frame.setLocationRelativeTo(null);
        visualise.frame.setResizable(false);
        visualise.frame.setVisible(true);

        visualise.start();
    }
    public static void init(int lPackages, int pPackages, int tPackages, int lValues, int pValues, int tValues, AlgorithmsTypes algorithm){
        Visualisor visualise = new Visualisor(lPackages, pPackages, tPackages, lValues, pValues, tValues, algorithm);

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
        final double ns = 1000000000/ 60;
        double delta = 0;
        int[][][] arr;

        if(algorithm == AlgorithmsTypes.DancingLinksAlgorithm) arr = DancingRun3D.getSolution(timeToTake, triesToTake, isBoxes);
        else arr = heuristic.finaresult(lPackages, pPackages, tPackages);

        // first parameter is number of milliseconds per search, second parameter is number of tries to search
        //third parameter is true if boxes, false if pentominoes.
        this.entityManager.init(arr, algorithm);

        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                update();
                render();
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
