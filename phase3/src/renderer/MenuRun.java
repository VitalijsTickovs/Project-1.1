package renderer;

import renderer.shapes.AlgorithmType.AlgorithmsTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuRun{

    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;

    private static JTextField timeToTake;
    private static JTextField triesToTake;
    private static JCheckBox isBoxes;

    private static JTextField lPackage;
    private static JTextField pPackage;
    private static JTextField tPackage;
    private static JTextField lValues;
    private static JTextField pValues;
    private static JTextField tValues;


    private static JFrame frame;


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

    public static JPanel createHeuristic(){
        JPanel pan = new JPanel();

        JLabel LText = new JLabel("How much L packages?");
        lPackage = new JTextField(6);

        JLabel PText = new JLabel("How much P packages?");
        pPackage = new JTextField(6);

        JLabel TText = new JLabel("How much T packages?");
        tPackage = new JTextField(6);

        JLabel ValLText = new JLabel("What is the value of L packages?");
        lValues = new JTextField(6);

        JLabel ValPText = new JLabel("What is the value of P packages?");
        pValues = new JTextField(6);

        JLabel ValTText = new JLabel("What is the value of T packages?");
        tValues = new JTextField(6);

        pan.add(LText);
        pan.add(lPackage);
        pan.add(PText);
        pan.add(pPackage);
        pan.add(TText);
        pan.add(tPackage);

        pan.add(ValLText);
        pan.add(lValues);
        pan.add(ValPText);
        pan.add(pValues);
        pan.add(ValTText);
        pan.add(tValues);

        return pan;
    }

    public static void main(String[] args) {
        frame = new JFrame("Menu");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGTH));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel Panel = new JPanel(new BorderLayout());

        Panel.add(createDancingLayout(), BorderLayout.CENTER);

        String[] Algorithms = {"Dancing Links Algorithm", "Heuristics Algorithm"};
        JComboBox optionOfAlgorithm = new JComboBox(Algorithms);

        JButton startRenderer = new JButton("Generate");

        Panel.add(optionOfAlgorithm, BorderLayout.NORTH);
        Panel.add(startRenderer, BorderLayout.SOUTH);

        frame.add(Panel);

        ActionListener comboListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(optionOfAlgorithm.getSelectedItem().equals("Dancing Links Algorithm")){
                    Panel.removeAll();
                    Panel.add(createDancingLayout(), BorderLayout.CENTER);
                    Panel.add(optionOfAlgorithm, BorderLayout.NORTH);
                    Panel.add(startRenderer, BorderLayout.SOUTH);
                }else{
                    Panel.removeAll();
                    Panel.add(optionOfAlgorithm, BorderLayout.NORTH);
                    Panel.add(startRenderer, BorderLayout.SOUTH);
                    Panel.add(createHeuristic(),BorderLayout.CENTER);
                }
                frame.remove(Panel);
                frame.add(Panel);
                frame.pack();
                frame.repaint();
            }
        };

        ActionListener butListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if(optionOfAlgorithm.getSelectedItem().equals("Dancing Links Algorithm")) {
                    if (!timeToTake.getText().equals("") && !triesToTake.getText().equals("")) {
                        Visualisor.init(Integer.parseInt(timeToTake.getText()), Integer.parseInt(triesToTake.getText()), isBoxes.isSelected(), AlgorithmsTypes.DancingLinksAlgorithm);
                    }
                }else{
                    if(!lPackage.getText().equals("") && !pPackage.getText().equals("") && !tPackage.getText().equals("") && !lValues.getText().equals("") && !pValues.getText().equals("") && !tValues.getText().equals("")){
                        Visualisor.init(Integer.parseInt(lPackage.getText()), Integer.parseInt(lPackage.getText()), Integer.parseInt(lPackage.getText()), Integer.parseInt(lValues.getText()), Integer.parseInt(pValues.getText()), Integer.parseInt(tValues.getText()), AlgorithmsTypes.HeuristicAlgorithm);
                    }
                }
            }
        };
        startRenderer.addActionListener(butListener);
        optionOfAlgorithm.addActionListener(comboListener);

        frame.pack();
        frame.setVisible(true);
    }
}
