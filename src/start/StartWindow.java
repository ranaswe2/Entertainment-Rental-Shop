package start;

import java.awt.Color;
import static javax.swing.BorderFactory.createMatteBorder;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class StartWindow extends JFrame{
    
    JProgressBar progress;
    JLabel progressLabel,label;
         
    public StartWindow() {
        //super("Welcome");
        dispose();
        this.setUndecorated(true);
        
        this.setLocationRelativeTo(null);  
       label= new JLabel("Entertainment Rental Shop"); 
       label.setBounds(75,20,250,20);
       progress= new JProgressBar();
       progress.setBounds(50,90,200,20);
       progressLabel= new JLabel();
       
        
        progress.setBackground(Color.gray);
        progress.setForeground(Color.BLUE);
        //progress.setCursor(new Cursor(Cursor.HAND_CURSOR));
        progress.setBorder(createMatteBorder(0,0,5,0,Color.white));
        
        
        
         
         this.add(label);
         this.add(progress);
         this.add(progressLabel);
         
        this.setBackground(Color.decode("#051E3E"));
        this.setSize(300, 200);
        this.setResizable(false);
        this.setVisible(true);
        
    }
    
    public static void main(String[] args) {
      StartWindow start= new StartWindow();
        
        
         try {
            int i;
            for(i=0;i<=50;i++){
            Thread.sleep(50);
                start.progress.setValue(i);
            }
            for(i=50;i<=80;i++){
            Thread.sleep(75);
                start.progress.setValue(i);
            }
            for(i=80;i<=100;i++){
            Thread.sleep(100);
                start.progress.setValue(i);
            }
            
            Thread.sleep(100);
            start.dispose();
            
            new CombinedFrame();
            
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
        
    }
}
