import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Memory extends JFrame implements ActionListener {
    int rows = 4;
    int columns = 4;
    int noOfImages = rows * columns / 2;

    JFrame frame = new JFrame();
    JLabel[] labels = new JLabel[rows * columns];
    JPanel[] panels = new JPanel[rows * columns];
    JButton[] buttons = new JButton[rows * columns];
    ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();

    int clicked = -1;
    int correct = 0;

    public Memory() {
        frame.setSize(1920, 1080);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < panels.length; i++) {
            images.add(new ImageIcon("Image" + (i + 1) + ".jpg"));
            images.add(new ImageIcon("Image" + (i + 1) + ".jpg"));
        }
        Collections.shuffle(images);

        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            add(panels[i]);
            panels[i].setBackground(Color.BLACK);
            labels[i] = new JLabel();
            buttons[i] = new JButton();
            buttons[i].setIcon(new ImageIcon("src//0.png"));
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setFocusable(false);
            //buttons.setBorderPainted(false);
            buttons[i].addActionListener(this);
            panels[i].add(labels[i]);
            panels[i].add(buttons[i]);
            frame.add(panels[i]);
        }
            frame.setVisible(true);
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int index = 0;
        for (int j = 0; j < panels.length; j++) {
            if (buttons[j] == button) {
                index = j;
                labels[index].setIcon(images.get(index));
            }
        }
    }
}








