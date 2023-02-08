import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static java.nio.file.Files.exists;


public class Memory extends JFrame implements ActionListener {
    int rows = 4;
    int columns = 4;
    int noOfImages = rows * columns / 2;
    int wrong = 0; // counter of mistakes (5 max maybe?)
    int correct = 0; // counter of correct pairs (8max=win)




    JFrame frame = new JFrame();
    JPanel[] panels = new JPanel[rows * columns];
    JButton[] buttons = new JButton[rows * columns];
    ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
    private ArrayList<ImageIcon> imagine = new ArrayList<ImageIcon>();


    public boolean exists(int x){
        return true;
    }

    ArrayList<Integer> openCards = new ArrayList<Integer>();


    public Memory() {
        frame.setSize(1920, 1080);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < noOfImages; i++) {      // used noOfImages instead of panels.length
            ImageIcon image1 = new ImageIcon("src//" + (i + 1) + ".jpg");
            ImageIcon image2 = image1;
            images.add(image1); // had to add "src//" and remove "image"
            images.add(image2);
        }
        Collections.shuffle(images);

        for (int i = 0; i < panels.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setIcon(new ImageIcon("src//0.png"));
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setFocusable(false);
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(this);
            panels[i] = new JPanel();
            panels[i].add(buttons[i]);
            panels[i].setBackground(Color.BLACK);
            frame.add(panels[i]);
        }
            frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        for (int j = 0; j < panels.length; j++) {
            if (buttons[j] == button) {
                buttons[j].setIcon(images.get(j)); // It works!
                imagine.add(images.get(j)); // adding to the list of comparison
                openCards.add(j);  // we add the button position

            }
            if(openCards.size()==3){
                if(imagine.get(1).equals(imagine.get(0))) {
                    correct++;
                }
                else if(!imagine.get(0).equals(imagine.get(1))){
                    wrong++;
                    buttons[openCards.get(1)].setIcon(new ImageIcon("src//0.png"));
                    buttons[openCards.get(0)].setIcon(new ImageIcon("src//0.png"));
                }
                openCards.remove(1);
                openCards.remove(0);
                imagine.remove(1);
                imagine.remove(0);
                System.out.println("You found " + correct + " pairs!");
                System.out.println("You made " + wrong + " mistakes!");

                if(wrong==5){
                    frame.dispose();
                    LoserPage loserpage = new LoserPage();
                }
            }
        }
    }
}
