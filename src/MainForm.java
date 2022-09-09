import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel panel1;
    private JButton startButton;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSlider slider1;

    public MainForm() {
        $$$setupUI$$$();
        setContentPane(panel1);
        setSize(380, 450);
        setVisible(true);
        SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1);
        SpinnerModel model2 = new SpinnerNumberModel(1, 1, 10, 1);
        spinner1.setModel(model);
        spinner2.setModel(model2);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run_thread();
                startButton.setEnabled(false);
            }
        });
        spinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                run_thread();
            }
        });
        spinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                run_thread();
            }
        });

    }
    private volatile int curVal = 50;
    private Thread t1;
    private Thread t2;
    public void run_thread() {

        Integer priority_one = (Integer) spinner1.getValue();
        Integer priority_two = (Integer) spinner2.getValue();

        t1 = new Thread() {
            public void run() {
                while (true) {
                    synchronized (this) {
                        if (curVal > 10) {
                            curVal--;
                        }
                        slider1.setValue(curVal);
                        try {
                            wait(10);
                        } catch (Exception e) {

                        }
                    }
                }

            }

        };

        t2 = new Thread() {
            public void run() {
                while (true) {
                    synchronized (this) {
                        if (curVal < 90) {
                            curVal++;
                        }
                        slider1.setValue(curVal);
                        try {
                            wait(10);
                        } catch (Exception e) {

                        }
                    }
                }

            }
        };
        t1.setPriority(priority_one);
        t2.setPriority(priority_two);
        t1.start();
        t2.start();
    }

    public static void main(String args[]) {
        MainForm new_form = new MainForm();

    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 30, 0, 30), -1, -1));
        startButton = new JButton();
        startButton.setText("Start!");
        panel1.add(startButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 50), null, 0, false));
        spinner1 = new JSpinner();
        panel1.add(spinner1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 50), new Dimension(150, 50), new Dimension(100, 100), 0, false));
        spinner2 = new JSpinner();
        panel1.add(spinner2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(80, 50), new Dimension(150, 50), new Dimension(100, 100), 0, false));
        slider1 = new JSlider();
        slider1.setMajorTickSpacing(10);
        slider1.setMaximum(100);
        slider1.setMinimum(0);
        slider1.setMinorTickSpacing(10);
        slider1.setPaintLabels(true);
        slider1.setPaintTicks(true);
        slider1.setSnapToTicks(false);
        slider1.setValue(10);
        slider1.setValueIsAdjusting(false);
        panel1.add(slider1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
