import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LaunchPage extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JButton button = new JButton("PLAY");
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();
    JLayeredPane layeredPane = new JLayeredPane();
    LaunchPage () {
        button.setBounds(145,175,200,100);
        button.setBackground(new Color(60,116,107));
        button.setForeground(Color.white);
        button.setFont(new Font("MV Boli",Font.BOLD, 24));
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.addActionListener(this);

        label3.setBounds(0,100,500,300);
        label3.setOpaque(true);
        label3.setBackground(Color.BLACK);

        label4.setBounds(0,450,500,50);
        label4.setOpaque(true);
        label4.setBackground(Color.BLACK);

        label1.setBounds(0,0,500,100);
        label1.setOpaque(true);
        label1.setBackground(Color.BLACK);
        label1.setText("     Find 8 correct pairs to win!");
        label1.setFont(new Font("MV Boli", Font.BOLD, 24));
        label1.setForeground(new Color(60,116,107));

        label2.setBounds(0,400,500,50);
        label2.setOpaque(true);
        label2.setBackground(Color.BLACK);
        label2.setText("     Or make 8 mistakes and lose!");
        label2.setFont(new Font("MV Boli", Font.BOLD, 24));
        label2.setForeground(new Color(60,116,107));

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("MEMORY GAME");
        frame.setIconImage(new ImageIcon("src//0.png").getImage());
        layeredPane.setBounds(0,0,500,500);
        layeredPane.add(label3,0);
        layeredPane.add(label2,1);
        layeredPane.add(button,1);
        layeredPane.add(label1,1);
        layeredPane.add(label4,1);
        frame.add(layeredPane);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button) {
            try {
                Memory myWindow = new Memory();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
            frame.dispose();
        }
    }
}
