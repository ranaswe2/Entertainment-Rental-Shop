package adminpanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends JFrame{

    public JPanel panel;
    public Container container;


    public Admin() {

        this.setTitle("Entertainment Rental Shop");
        container= this.getContentPane();
        this.setLayout(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#051E3E"));

        JLabel label= new JLabel();
        label.setText("Admin Panel        ");
        label.setBounds(1200,20,800,90);
        label.setFont(new Font("Serif", Font.BOLD, 90));
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
      //  window.setTextFill(Color.web("#0076a3"));





        Icon imageHome = new ImageIcon("src/adminpanel/images/home.png");
        JButton buttonHome = new JButton(imageHome);
        this.buttonDecoration(buttonHome,100,"Home");
        this.buttonBorder(buttonHome);

        buttonHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Home();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });




        Icon imageUserRequest = new ImageIcon("src/adminpanel/images/userrequest.png");
        JButton buttonUserRequest = new JButton(imageUserRequest);
         this.buttonDecoration(buttonUserRequest,200,"User Request");
        this.buttonBorder(buttonUserRequest);
        buttonUserRequest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new UserRequest();
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Icon imageNewItem = new ImageIcon("src/adminpanel/images/newitem.png");
        JButton buttonNewItem = new JButton(imageNewItem);
        this.buttonDecoration(buttonNewItem,300,"Add New Item" );
        this.buttonBorder(buttonNewItem);
        buttonNewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NewItem();
                dispose();
            }
        });

        Icon imageNewProduct = new ImageIcon("src/adminpanel/images/newproduct.png");
        JButton buttonNewProduct = new JButton(imageNewProduct);
        this.buttonDecoration(buttonNewProduct,400,"Add New Product" );
        this.buttonBorder(buttonNewProduct);
        buttonNewProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new NewProduct();
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Icon imageManageUser = new ImageIcon("src/adminpanel/images/manageuser.png");
        JButton buttonManageUser = new JButton(imageManageUser);
        this.buttonDecoration(buttonManageUser,500,"Manage User" );
        this.buttonBorder(buttonManageUser);
        buttonManageUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageUser();
                dispose();
            }
        });

        Icon imageManageItem = new ImageIcon("src/adminpanel/images/manageproduct.png");
        JButton buttonManageItem = new JButton(imageManageItem);
        this.buttonDecoration(buttonManageItem,600,"Manage Item");
        this.buttonBorder(buttonManageItem);
        buttonManageItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ManageItem();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });

        Icon imageSummaryReport = new ImageIcon("src/adminpanel/images/summaryreport.png");
        JButton buttonSummaryReport = new JButton(imageSummaryReport);
        this.buttonDecoration(buttonSummaryReport,700,"Summary & Report");
        this.buttonBorder(buttonSummaryReport);
        buttonSummaryReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoanSummary();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });


        Icon imageLogout = new ImageIcon("src/adminpanel/images/logout.png");
        JButton buttonLogout = new JButton(imageLogout);
        this.buttonDecoration(buttonLogout,800,"Logout");
        this.buttonBorder(buttonLogout);
        buttonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Logout();
                dispose();
            }
        });




        panel.add(label);
        panel.add(buttonHome);
        panel.add(buttonLogout);
        panel.add(buttonManageItem);
        panel.add(buttonManageUser);
        panel.add(buttonNewItem);
        panel.add(buttonNewProduct);
        panel.add(buttonSummaryReport);
        panel.add(buttonUserRequest);

        Toolkit toolkit= Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();     // Get Device Screen Size


        panel.setSize(screenSize);
        container.add(panel);

        this.setSize(screenSize);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);         // Set the JFrame to be maximized
        this.setResizable(false);

    }

    private void buttonBorder(JButton button){


        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY);
              //  button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.white);
              //  button.setForeground(UIManager.getColor("Button.foreground"));
            }
        });
    }

    private void buttonDecoration(JButton button,int yPosition, String toolTipText){

        button.setBackground(Color.WHITE);
        button.setBorderPainted(false);
        button.setToolTipText(toolTipText);
        button.setBounds(30,yPosition,200,100);

    }

    public void topBanner(String title){

        JLabel banner= new JLabel();
        banner.setText(title);
        banner.setBounds(400,20,800,70);
        banner.setFont(new Font("verdana", Font.BOLD, 70));
        banner.setForeground(Color.green);

        panel.add(banner);

    }

    public void inputDecorator(JTextField textField, String textLabel,int y){
        JLabel fieldName= new JLabel();
        fieldName.setText(textLabel);
        fieldName.setBounds(400,y,400,30);
        fieldName.setFont(new Font("verdana", Font.BOLD, 30));
        fieldName.setForeground(Color.WHITE);

        textField.setBounds(800,y,300,40);
        textField.setFont(new Font("tahome", Font.PLAIN, 30));

        panel.add(fieldName);
        panel.add(textField);
    }

    public void buttonStructure(JButton button,int x,int y,int width,int height){
        button.setBackground(Color.ORANGE);
        button.setBounds(x,y,width,height);
        button.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(button);
    }
    public void paint(Graphics gp) {

        gp.setColor(Color.CYAN);
        super.paint(gp);
        Graphics2D graphics = (Graphics2D) gp;

        Line2D lineHorizontal = new Line2D.Float(1200, 150, 150, 220);
        graphics.draw(lineHorizontal);


        Line2D lineVertical = new Line2D.Float(1200, 150, 1200, 900);
        graphics.draw(lineVertical);
    }

    public static void main(String[] args) {
        new Admin();
    }

}

/*
class Rectangle extends JApplet {


    // paint the applet
    public void paint(Graphics g)
    {
        // set Color for rectangle
        g.setColor(Color.WHITE);

        // draw a rectangle
        g.drawRect(900, 200, 200, 200);
    }
}
*/