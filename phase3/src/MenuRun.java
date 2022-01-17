import renderer.Visualisor;
import renderer.shapes.AlgorithmType.AlgorithmsTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuRun{
    public static final int WIDTH = 800;        //size of the frame
    public static final int HEIGTH = 600;

    private static JTextField timeToTake;       //all the necessary variables for the menu
    private static JTextField triesToTake;
    private static JCheckBox isBoxes;

    private static JTextField lPackage;
    private static JTextField pPackage;
    private static JTextField tPackage;
    private static JTextField lValues;
    private static JTextField pValues;
    private static JTextField tValues;

    private static JFrame frame;

    /**
     * Method that adds objects to be specified for Dancing links algorithm
     * @return JPanel of added contents
     */
    public static JPanel createDancingLayout(){
        JPanel dancingPanel = new JPanel();
        JPanel textFields = new JPanel();

        isBoxes = new JCheckBox("Boxes?");

        JLabel time = new JLabel("How much time to take? (ms)");
        timeToTake = new JTextField(6);

        JLabel tries = new JLabel("How many tries to take?");
        triesToTake = new JTextField(6);

        textFields.add(time);
        textFields.add(timeToTake);
        textFields.add(tries);
        textFields.add(triesToTake);
        textFields.add(isBoxes);

        dancingPanel.add(textFields);

        return dancingPanel;
    }

    /**
     * Method that adds objects to be specified for heuristic algorithm
     * @return JPanel of added contents
     */
    public static JPanel createHeuristic(){
        JPanel fullPanel = new JPanel(new BorderLayout());
        JPanel pan = new JPanel();
        JPanel pan2 = new JPanel();

        JLabel LText = new JLabel("How much L packages?");
        lPackage = new JTextField(4);

        JLabel PText = new JLabel("How much P packages?");
        pPackage = new JTextField(4);

        JLabel TText = new JLabel("How much T packages?");
        tPackage = new JTextField(4);

        JLabel ValLText = new JLabel("What is the value of L packages?");
        lValues = new JTextField(3);

        JLabel ValPText = new JLabel("What is the value of P packages?");
        pValues = new JTextField(3);

        JLabel ValTText = new JLabel("What is the value of T packages?");
        tValues = new JTextField(3);

        pan.add(LText);
        pan.add(lPackage);
        pan.add(PText);
        pan.add(pPackage);
        pan.add(TText);
        pan.add(tPackage);

        pan2.add(ValLText);
        pan2.add(lValues);
        pan2.add(ValPText);
        pan2.add(pValues);
        pan2.add(ValTText);
        pan2.add(tValues);

        fullPanel.add(pan, BorderLayout.NORTH);
        fullPanel.add(pan2, BorderLayout.CENTER);
        return fullPanel;
    }

    /**
     * Start of the phase 3 project program
     */
    public static void main(String[] args) {
        frame = new JFrame("Menu");                         //Initialization of the frame
        frame.setPreferredSize(new Dimension(WIDTH, HEIGTH));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel Panel = new JPanel(new BorderLayout());

        String[] Algorithms = {"Dancing Links Algorithm", "Heuristics Algorithm"};      //creating combo box to choose which algorithm user wants
        JComboBox optionOfAlgorithm = new JComboBox(Algorithms);

        Panel.add(optionOfAlgorithm, BorderLayout.NORTH);                               //adding combo box to the panel

        Panel.add(createDancingLayout(), BorderLayout.CENTER);                          //adding the field for dancing links to be displayed first

        JButton startRenderer = new JButton("Generate");                           //creating button to generate the shape
        Panel.add(startRenderer, BorderLayout.SOUTH);                                   //adding button the panel

        frame.add(Panel);                                                               //adding panel to the frame

        ActionListener comboListener = new ActionListener() {                           //creating action listener for combo box
            @Override
            public void actionPerformed(ActionEvent e) {
                if(optionOfAlgorithm.getSelectedItem().equals("Dancing Links Algorithm")){  //if the dancing links algorithm is chosen
                    Panel.removeAll();
                    Panel.add(createDancingLayout(), BorderLayout.CENTER);
                    Panel.add(optionOfAlgorithm, BorderLayout.NORTH);
                    Panel.add(startRenderer, BorderLayout.SOUTH);
                }else{                                                                      //if it is heuristic algorithm is chosen
                    Panel.removeAll();
                    Panel.add(optionOfAlgorithm, BorderLayout.NORTH);
                    Panel.add(startRenderer, BorderLayout.SOUTH);
                    Panel.add(createHeuristic(),BorderLayout.CENTER);
                }
                frame.remove(Panel);                                                        //adding changed panel to the frame
                frame.add(Panel);
                frame.pack();
                frame.repaint();
            }
        };

        ActionListener butListener = new ActionListener() {                                 //creating action listener for the button
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if(optionOfAlgorithm.getSelectedItem().equals("Dancing Links Algorithm")) {     //if the dancing links algorithm was chosen
                    if (!timeToTake.getText().equals("") && !triesToTake.getText().equals("")) {
                        Visualisor.init(Integer.parseInt(timeToTake.getText()), Integer.parseInt(triesToTake.getText()), isBoxes.isSelected(), AlgorithmsTypes.DancingLinksAlgorithm);
                    }
                }else{                                                                          //if heuristic algorithm was chosen

                    if(!lPackage.getText().equals("") && !pPackage.getText().equals("") && !tPackage.getText().equals("") && !lValues.getText().equals("") && !pValues.getText().equals("") && !tValues.getText().equals("")){
                        Visualisor.init(Integer.parseInt(lPackage.getText()), Integer.parseInt(pPackage.getText()), Integer.parseInt(tPackage.getText()),
                                Integer.parseInt(lValues.getText()), Integer.parseInt(pValues.getText()), Integer.parseInt(tValues.getText()), AlgorithmsTypes.HeuristicAlgorithm);
                    }
                }
            }
        };
        startRenderer.addActionListener(butListener);                                           //adding action listeners to the combo box and button
        optionOfAlgorithm.addActionListener(comboListener);

        frame.pack();
        frame.setVisible(true);
    }
}
