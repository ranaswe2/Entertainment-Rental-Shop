package userpanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import start.CombinedFrame;

public class Logout  extends User{
    public Logout() {
        
        super.topBanner("Log Out");
        
        JButton leaveButton = new JButton("Leave From User Panel");
        leaveButton.setBounds(450, 550, 500,  40);
        leaveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        leaveButton.setFont(new Font("Serif", Font.BOLD, 24));
        leaveButton.setBackground(Color.ORANGE);

        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CombinedFrame();
                dispose();
            }
        
        });
        
        
        
        JButton exitButton = new JButton("Exit From System");
        exitButton.setBounds(1300, 550, 500,  40);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exitButton.setFont(new Font("Serif", Font.BOLD, 24));
        exitButton.setBackground(Color.RED);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        
        });
        
        panel.add(leaveButton);
        panel.add(exitButton);
    }
}

