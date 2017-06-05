package carapplication;
//import jdbc packages
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
//import GUI swing Components
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
//import layout jFrame
import java.awt.FlowLayout;
//import event listener to jButtons
import java.awt.Color;
import java.awt.event.ActionListener;
class Login_Page implements ActionListener{
    //declare variables and object reference of GUI and JDBC connection
    public JFrame Login_Frame = null;
    private JPanel Login_Panel=null;
    private JLabel User_ID = null,Password = null,Register=null;
    private JTextField Txt_User_ID = null;
    private JPasswordField Txt_Password = null;
    private JButton Log_in = null,Reset = null,Log_out = null;
    //JDBC Connection variables
    private Connection Con = null;
    private PreparedStatement P_Stmt = null;
    private ResultSet RS = null;
    
    Login_Page(){
        //call the GUI function
        Login_GUI();
    }
    private void Login_GUI(){
        //making swingutilities for swing components handling
        SwingUtilities.invokeLater(()->{
            try{
                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }catch(Exception E){E.printStackTrace();}
        //making jframe
        Login_Frame = new JFrame("Car Server Login");
        Login_Frame.setSize(320,220);
//        try{
//            Login_Frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/Deepanshu/Desktop/CarApplication/1920.jpg")))));
//        }catch(IOException e){JOptionPane.showMessageDialog(null,e);}
        Login_Frame.setResizable(false);
        Login_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login_Frame.setVisible(true);
        Login_Panel = new JPanel();
        Login_Frame.add(Login_Panel);
        //adding swing components
        User_ID = new JLabel("User ID :- ");
        User_ID.setFont(new java.awt.Font("David", 2, 18));
        Password = new JLabel("Password :- ");
        Password.setFont(new java.awt.Font("David", 2, 18));
        Register = new JLabel("<----New Login Register---->");
        Register.setFont(new java.awt.Font("David", 2, 18));
        Txt_User_ID = new JPasswordField(20);
        Txt_User_ID.setFont(new java.awt.Font("David", 2, 18));
        Color C2 = new Color(246,94,175);
        Txt_User_ID.setBackground(C2);
        Txt_Password = new JPasswordField(20);
        Txt_Password.setFont(new java.awt.Font("David", 2, 18));
        Txt_Password.setBackground(C2);
        Log_in = new JButton("Login");
        Log_in.setFont(new java.awt.Font("David", 2, 18));
        Reset = new JButton("Reset");
        Reset.setFont(new java.awt.Font("David", 2, 18));
        Log_out = new JButton("Logout");
        Log_out.setFont(new java.awt.Font("David", 2, 18));
        Color C = new Color(36,218,193);
        Color C1 = new Color(218,36,97);
        User_ID.setForeground(C);
        Txt_User_ID.setToolTipText("Insert Valid User ID");
        Password.setForeground(C);        
        Txt_Password.setToolTipText("Insert Valid Password");
        Log_in.setForeground(C);
        Reset.setForeground(C);
        Log_out.setForeground(C);
        Register.setForeground(C1);
        //adding swing components to JFrame
        Login_Panel.add(User_ID);
        Login_Panel.add(Txt_User_ID);
        Login_Panel.add(Password);
        Login_Panel.add(Txt_Password);
        Login_Panel.add(Log_in);
        Login_Panel.add(Reset);
        Login_Panel.add(Log_out);
        Login_Panel.add(Register);
        //adding action listener to the swing buttons
        Log_in.addActionListener(this);
        Reset.addActionListener(this);
        Log_out.addActionListener(this);
        Register.addMouseListener(new java.awt.event.MouseAdapter(){
        //adding mouse clicked for new user login
        @Override
        public void mouseClicked(java.awt.event.MouseEvent ME){
                RegisterMouseClicked(ME);
            }});
        });
    }
    private void Login_DataBase(){
        //upload driver connection
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){JOptionPane.showMessageDialog(null,e);}
        // making queries in mysql commmand
        try{
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Car_Application","root","9868");
            String Query = "select * from Login where User_Id=? and Password=? ;";
            P_Stmt = Con.prepareStatement(Query);
            P_Stmt.setLong(1,Long.parseLong(Txt_User_ID.getText()));
            P_Stmt.setString(2,Txt_Password.getText());
            RS = P_Stmt.executeQuery();
            if(RS.next()){
                JOptionPane.showMessageDialog(null, "User Login Successfully");
                try{
                CustomerApplication Ca = new CustomerApplication();
                Login_Frame.setVisible(false);
                Ca.C_Frame.setVisible(true);}catch(NullPointerException e){System.out.println(e);}
            }
            else{
                JOptionPane.showMessageDialog(null,"Wrong UserId or Password Enter");
            }
            Con.close();
        }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
    }
    //performing action on click of buttons
    @Override
    public void actionPerformed(java.awt.event.ActionEvent AE){
        //simply callig buttons
        switch(AE.getActionCommand()){
            case "Login":
                Login_DataBase();
                break;
            case "Reset":
                Txt_User_ID.setText("");
                Txt_Password.setText("");
                break;
            default:
                System.exit(0);
        }
    }
    //adding click event on register label and calling the register function
    public void RegisterMouseClicked(java.awt.event.MouseEvent ME){
        try{
        Registration_GUI R = new Registration_GUI();
        Login_Frame.hide();        
        R.N_R_Frame.setVisible(true);}catch(NullPointerException e){}
    }
}
public class Login{
    public static void main(String args[]){
        //making object of Login_Page
        new Login_Page();
    }
}