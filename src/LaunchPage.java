/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage {
    JFrame frame = new JFrame();
    JButton button = new JButton("PLAY");
    JPanel panel = new JPanel();

    LaunchPage () {
        button.setBounds(145,175,200,100);
        button.setBackground(new Color(60,116,107));
        button.setForeground(Color.white);
        button.setFont(new Font("MV Boli",Font.BOLD, 24));
        button.setFocusable(false);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button) {
                    Memory myWindow = new Memory();
                    frame.dispose();
                }
            }
        });
        panel.add(button);
        panel.setBackground(Color.black);
        panel.setSize(500,500);

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(button);
        frame.add(panel);
        frame.setVisible(true);
    }
    }

 */

