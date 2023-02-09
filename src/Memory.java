import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;




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
    File file;
    Clip clip;
    private ArrayList<ImageIcon> imageHolder = new ArrayList<ImageIcon>();
    ArrayList<Integer> openCards = new ArrayList<Integer>();


    public Memory() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        frame.setSize(1920, 1080);
        frame.setResizable(false);
        frame.setTitle("MEMORY GAME");
        frame.setIconImage(new ImageIcon("src//0.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows, columns));

        file = new File("src//karavi.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

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
                imageHolder.add(images.get(j)); // adding to the list of comparison
                openCards.add(j);  // we add the button position

            }
                if (openCards.size() == 3) {
                    if (imageHolder.get(0).equals(imageHolder.get(1)) && openCards.get(0) != openCards.get(1) && openCards.get(0) != openCards.get(2) && openCards.get(1) != openCards.get(2)) {
                        correct++;
                        buttons[openCards.get(0)].setEnabled(false);        // sets buttons of correct pair unclickable
                        buttons[openCards.get(1)].setEnabled(false);
                    } else {
                        wrong++;
                        buttons[openCards.get(1)].setIcon(new ImageIcon("src//0.png"));
                        buttons[openCards.get(0)].setIcon(new ImageIcon("src//0.png"));
                        buttons[openCards.get(2)].setIcon(imageHolder.get(2));                  // keeps open last picked image no matter what
                    }
                    openCards.remove(1);
                    openCards.remove(0);
                    imageHolder.remove(1);
                    imageHolder.remove(0);
                    System.out.println("You found " + correct + " pairs!");
                    System.out.println("You made " + wrong + " mistakes!");

                    if(correct==7 && openCards.size()==1){
                        clip.stop();
                        frame.dispose();
                        try {
                            WinnerPage winnerPage = new WinnerPage();
                        } catch (LineUnavailableException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if(wrong==8){
                        clip.stop();
                        frame.dispose();
                        try {
                            LoserPage loserPage = new LoserPage();
                        } catch (UnsupportedAudioFileException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (LineUnavailableException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            }
        }
