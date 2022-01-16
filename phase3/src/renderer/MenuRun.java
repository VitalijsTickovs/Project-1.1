package renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuRun{
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGTH));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel textFields = new JPanel();

        JLabel time = new JLabel("How much time to take? (ms)");
        JTextField timeToTake = new JTextField(6);

        JLabel tries = new JLabel("How many tries to take?");
        JTextField triesToTake = new JTextField(6);

        JCheckBox isBoxes = new JCheckBox("Boxes?");

        textFields.add(time);
        textFields.add(timeToTake);
        textFields.add(tries);
        textFields.add(triesToTake);
        textFields.add(isBoxes);

        JButton startRenderer = new JButton("Generate");

        panel.add(textFields, BorderLayout.CENTER);

        panel.add(startRenderer, BorderLayout.SOUTH);
        frame.add(panel);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!timeToTake.getText().equals("") && !triesToTake.getText().equals("")){
                    frame.setVisible(false);
                    Visualisor.init(Integer.parseInt(timeToTake.getText()), Integer.parseInt(triesToTake.getText()), isBoxes.isSelected());
                }
            }
        };
        startRenderer.addActionListener(listener);
        frame.pack();
        frame.setVisible(true);
    }
}
