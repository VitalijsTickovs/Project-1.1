package renderer;

import dancingLinks.DancingRun3D;
import heuristicAlgorithm.heuristic;
import renderer.mouseInput.Mouse;
import renderer.shapes.AlgorithmType.AlgorithmsTypes;
import renderer.shapes.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Visualisor extends Canvas implements Runnable{

    private Thread thread;
    private static boolean running = false;

    private JFrame frame;                           //our main frame onto which 3d shape will be projected
    private static String title = "3D Render";      //JFrames title and size
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;


    private int timeToTake, triesToTake;            //values to be used for Dancing links algorithm
    private boolean isBoxes;

    private int lPackages, pPackages, tPackages;    //values to be used for heuristics algorithm
    private int lValues, pValues, tValues;

    private AlgorithmsTypes algorithm;              //variable to track which algortihm was selected by the user

    private EntityManager entityManager;            //object to render the shape

    private Mouse mouse;

    /**
     * Object initializer for Dancing links algorithm and which also creates a new frame and adding mouse listeners
     * @param timeToTake - stores time for Dancing links algorithm to take for a certain branch
     * @param triesToTake   - stores how many branches of tree it accesses in Dancing Links
     * @param isBoxes - stores if we want solution to be found by Monte Carlo method
     * @param algorithm - stores which algorithm it is
     */
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

    /**
     * Object initializer for Dancing links algorithm and which also creates a new frame and adding mouse listeners
     * @param lPackages - amount of "L" type packages
     * @param pPackages - amount of "P" type packages
     * @param tPackages - amount of "T" type packages
     * @param lValues - values of "L" type packages
     * @param pValues - values of "P" type packages
     * @param tValues - values of "T" type packages
     * @param algorithm - stores which algorithm it is
     */
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

    /**
     * Method which will be called after user clicks generate and which will initialize display frame and store values
     * @param timeToTake - stores time for Dancing links algorithm to take for a certain branch
     * @param triesToTake - stores how many branches of tree it accesses in Dancing Links
     * @param isBoxes - stores if we want solution to be found by Monte Carlo method
     * @param algorithm - stores which algorithm it is
     */
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

    /**
     * Method which will be called after user clicks generate and which will initialize display frame and store values
     * @param lPackages - amount of "L" type packages
     * @param pPackages - amount of "P" type packages
     * @param tPackages - amount of "T" type packages
     * @param lValues - values of "L" type packages
     * @param pValues - values of "P" type packages
     * @param tValues - values of "T" type packages
     * @param algorithm - stores which algorithm it is
     */
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

    /**
     * Method for multithreading
     */
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

    /**
     * Main loop for the rendering of the shape
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();  //variables to count how much time has passed
        final double ns = 1000000000/ 60;
        double delta = 0;

        int[][][] shapeLayout;

        // first parameter is number of milliseconds per search, second parameter is number of tries to search
        //third parameter is true if boxes, false if pentominoes.
        if(algorithm == AlgorithmsTypes.DancingLinksAlgorithm) shapeLayout = DancingRun3D.getSolution(timeToTake, triesToTake, isBoxes);    //goes through the specified algorithm and assigns it to local variable
        else shapeLayout = heuristic.finaresult(lPackages,tPackages,pPackages, lValues, pValues, tValues);

        this.entityManager.init(shapeLayout, algorithm);                //run through the decoding of each array to the cubes

        while(running){                                                 //loop to display the final image and let the user look into the shape
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

    /**
     * Method to display the finished shape
     */
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

    /**
     * MouseListener method that update the image based on the mouse commands
     */
    private void update() {
        this.entityManager.update(this.mouse);
    }
}
