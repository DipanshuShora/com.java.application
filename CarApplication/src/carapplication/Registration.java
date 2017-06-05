package carapplication;
import java.awt.Color;
import javax.swing.*;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
class Registration_GUI implements ActionListener{
    //intialise variables for the frame and database
    //intialise swing Components
    public JFrame N_R_Frame=null;
    private JPanel N_R_Panel=null;
    private JLabel New_User_ID=null,New_Password=null,Retype_Password=null;
    private JTextField New_User_ID_Txt=null;
    private JPasswordField New_Password_Txt=null,Retype_Password_Txt=null;
    private JButton Register_User=null,Reset=null,Exit=null,Open_Login=null;
    //intialise jdbc Components
    private Connection Con=null;
    private Statement Stmt=null;
    public Registration_GUI(){
        GUI();
    }
    private void GUI(){
        SwingUtilities.invokeLater(()->{
            try{
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }catch(Exception E){E.printStackTrace();}
            N_R_Frame = new JFrame("New User Registration");
            N_R_Frame.setSize(280,320);
//            try{
//            N_R_Frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/Deepanshu/Desktop/CarApplication/1280.jpg")))));
//            }catch(IOException e){JOptionPane.showMessageDialog(null,e);}
            N_R_Frame.setResizable(false);
            N_R_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            N_R_Frame.setVisible(true);
            JPanel N_R_Panel = new JPanel();
            N_R_Frame.add(N_R_Panel);
            //adding swing Components
            New_User_ID = new JLabel("New User ID :- ");
            New_User_ID.setFont(new java.awt.Font("Serif", 2, 16));
            New_Password = new JLabel("New Pasword :- ");
            New_Password.setFont(new java.awt.Font("Serif", 2, 16));
            Retype_Password = new JLabel("Retype Password :- ");
            Retype_Password.setFont(new java.awt.Font("Serif", 2, 16));
            New_User_ID_Txt = new JTextField(20);
            New_User_ID_Txt.setFont(new java.awt.Font("Serif", 2, 16));
            Color C1 = new Color(0,239,255);
            New_User_ID_Txt.setBackground(C1);
            New_Password_Txt = new JPasswordField(20);
            New_Password_Txt.setFont(new java.awt.Font("Serif", 2, 16));
            New_Password_Txt.setBackground(C1);
            Retype_Password_Txt = new JPasswordField(20);
            Retype_Password_Txt.setFont(new java.awt.Font("Serif", 2, 16));
            Retype_Password_Txt.setBackground(C1);
            Register_User = new JButton("Register New User");
            Register_User.setFont(new java.awt.Font("Serif", 2, 16));
            Reset = new JButton("Reset");
            Reset.setFont(new java.awt.Font("Serif", 2, 16));
            Exit = new JButton("Exit");
            Exit.setFont(new java.awt.Font("Serif", 2, 16));
            Open_Login = new JButton("Open Login Page");
            Open_Login.setFont(new java.awt.Font("Serif", 2, 16));
            Color C = new Color(239,0,255);
            New_User_ID.setForeground(C);
            New_User_ID_Txt.setToolTipText("Enter new Login id for registration");
            New_Password.setForeground(C);
            New_Password_Txt.setToolTipText("Enter new password for same login");
            Retype_Password_Txt.setToolTipText("Re enter the password");
            Retype_Password.setForeground(C);
            Register_User.setForeground(C);
            Reset.setForeground(C);
            Exit.setForeground(C);
            Open_Login.setForeground(C);
            //adding Components to JFrame
            N_R_Panel.add(New_User_ID);
            N_R_Panel.add(New_User_ID_Txt);
            N_R_Panel.add(New_Password);
            N_R_Panel.add(New_Password_Txt);
            N_R_Panel.add(Retype_Password);
            N_R_Panel.add(Retype_Password_Txt);
            N_R_Panel.add(Register_User);
            N_R_Panel.add(Reset);
            N_R_Panel.add(Exit);
            N_R_Panel.add(Open_Login);
            //adding action listener to the swing buttons
            Register_User.addActionListener(this);
            Reset.addActionListener(this);
            Exit.addActionListener(this);
            Open_Login.addActionListener(this);
        });
    }
    //new user register update data
    private void New_Register_Login(){
        if(New_Password_Txt.getText().equals(Retype_Password_Txt.getText())){
            //load driver connection
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch(ClassNotFoundException E){JOptionPane.showMessageDialog(null,E);}
            //registering for new user values
            try{
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Car_Application","root","9868");
                Stmt  = Con.createStatement();
                String Query = "insert into Login values('"+Long.parseLong(New_User_ID_Txt.getText())+"','"+New_Password_Txt.getText()+"');";
                int a = Stmt.executeUpdate(Query);
                if(a==1){JOptionPane.showMessageDialog(null,"New User Id is Successfully registered");}
                else{JOptionPane.showMessageDialog(null,"Not Successfully registered");}
            }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
        }
        else{JOptionPane.showMessageDialog(null,"Entered wrong password in retype field");}
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent AE){
        //simply calling buttons
        switch(AE.getActionCommand()){
            case "Register New User":
                New_Register_Login();
                break;
            case "Reset":
                New_User_ID_Txt.setText(null);
                New_Password_Txt.setText(null);
                Retype_Password_Txt.setText(null);
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                try{
                N_R_Frame.hide();
                Login_Page L = new Login_Page();
                L.Login_Frame.setVisible(true);}catch(NullPointerException E){}
        }
    }
}
public class Registration{
    public static void main(String args[]){new Registration_GUI();}
}