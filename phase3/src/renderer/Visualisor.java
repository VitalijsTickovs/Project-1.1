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

    public Visualisor(int lPackages, int pPackages, int tPackages, AlgorithmsTypes algorithm){
        this.lPackages = lPackages;
        this.pPackages = pPackages;
        this.tPackages = tPackages;
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
    public static void init(int lPackages, int pPackages, int tPackages, AlgorithmsTypes algorithm){
        Visualisor visualise = new Visualisor(lPackages, pPackages, tPackages, algorithm);

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
        int[][] T={  {1,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0} };
        int[][] L={  {1,0,0,0},{1,0,0,0},{1,1,1,0},{0,0,0,0}};
        int[][] I={  {1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};
        int[][][] field=new int[33][5][8];
        for(int kl=0;kl<197;kl++){
            int[][][][] result1 = allposibillities(field, L);
            int[][][][] result2 = allposibillities(field, T);
            int[][][][] result3 = allposibillities(field, I);
            if(result1.length==0 && result2.length==0 && result3.length==0 ){
                System.out.println("oh-oh,too much "+kl);
                break;
            }
            else if(result1.length==0 && result2.length==0){
                field=takebest(result3, result3,numberOfI,numberOfI);
            }
            else if(result1.length==0 && result3.length==0){
                field=takebest(result2, result2,numberOfT,numberOfT);
            }
            else if(result2.length==0 && result3.length==0){
                field=takebest(result1, result1,numberOfL,numberOfL);
            }
            else if(result1.length==0 ){
                field=takebest(result2, result3,numberOfT,numberOfI);
            }
            else if(result2.length==0){
                field=takebest(result1, result3,numberOfL,numberOfT);
            }
            else if(result3.length==0){
                field=takebest(result1, result2,numberOfL,numberOfI);
            }
            else{
                field=takebest(result1, result2, result3);
            }
        }
        int[][][] arr;
        if(algorithm == AlgorithmsTypes.DancingLinksAlgorithm) arr = DancingRun3D.getSolution(timeToTake, triesToTake, isBoxes);
        else arr = heuristic.finaresult();
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
