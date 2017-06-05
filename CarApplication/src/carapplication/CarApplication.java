package carapplication;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.sql.*;
import javax.imageio.ImageIO;
class CustomerApplication{
    public JFrame C_Frame=null;
    private JMenuBar Car_Menu=null;
    private JMenu Select_Car=null,Setting=null;
    private JMenuItem Car_1=null,Car_2=null,Car_3=null,Car_4=null,Update=null,Delete=null,Show_Booking=null;
    private JLabel C_Name=null,C_Id=null,C_Address=null,C_Deliver_O=null,Mode_Of_Payement=null,Selected_Car=null,Choose_Car=null;
    private JTextField C_Name_Txt=null,C_Id_Txt,C_Address_Txt=null;
    private JRadioButton At_Home=null,Take_In=null;
    private ButtonGroup jButtonGroup1;
    private JComboBox Mode_Pay=null;
    private JButton Book_Car=null,Reset=null,Quit=null,Update_Btn=null,Delete_Btn=null;
    private Connection Con=null;
    private PreparedStatement PStmt=null;
    private ResultSet RS=null;
    private String Query=null,Delivery=null;
    public CustomerApplication(){
        try{Customer_GUI();}catch(NullPointerException e){}
    }
    private void Customer_GUI(){
        SwingUtilities.invokeLater(()->{
            try{
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }catch(Exception e){e.printStackTrace();}
            //making JFrame
            C_Frame = new JFrame("Customer Car Customization");
            C_Frame.setSize(460,500);
//            try{
//            C_Frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/Deepanshu/Desktop/CarApplication/awesome-abstract-wallpapers.jpg")))));
//            }catch(IOException e){JOptionPane.showMessageDialog(null,e);}
            C_Frame.setResizable(false);
            //C_Frame set Operation
            C_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            C_Frame.setVisible(true);
            //set layout because setting the jComponents by setBounds() function
            C_Frame.setLayout(null);
            //making menubar
            Car_Menu = new JMenuBar();
            //first menu
            Select_Car = new JMenu("Select Car");
            Setting = new JMenu("Changes In Customer Booking");
            //first menu items
            Car_1 = new JMenuItem("Lamborghini");
            Car_2 = new JMenuItem("Ferrari");
            Car_3 = new JMenuItem("Buggati");
            Car_4 = new JMenuItem("Ford");
            //second menu items
            Update = new JMenuItem("Update Booking");
            Delete = new JMenuItem("Delete Booking");
            Show_Booking = new JMenuItem("Show Customer Booking");
            //set bounds of mneu bar in jframe
            Car_Menu.setBounds(0,0,500,30);
            //add menu bar to jframe
            C_Frame.add(Car_Menu);
            //add menu to menubar
            Car_Menu.add(Select_Car);
            Car_Menu.add(Setting);
            //add menu item to menu
            Select_Car.add(Car_1);
            Select_Car.add(Car_2);
            Select_Car.add(Car_3);
            Select_Car.add(Car_4);
            Setting.add(Update);
            Setting.add(Delete);
            Setting.add(Show_Booking);
            //intialise label for jframe
            C_Name = new JLabel("Customer Name :- ");
            C_Id = new JLabel("Customer Special Id :- ");
            C_Address = new JLabel("Customer Addresss :- ");
            C_Deliver_O = new JLabel("Delivery Option <---->");
            Mode_Of_Payement = new JLabel("Customer Mode of Payement <------->");
            Selected_Car = new JLabel("Selected Car <------>");
            //intialise textfield , radiobuttons,checkbox and ComboBox
            C_Name_Txt = new JTextField(20);
            C_Id_Txt = new JTextField(20);
            C_Address_Txt = new JTextField(20);
            At_Home = new JRadioButton("At Home");
            Take_In = new JRadioButton("Take In");
            jButtonGroup1 = new ButtonGroup();
            jButtonGroup1.add(At_Home);
            jButtonGroup1.add(Take_In);
            Mode_Pay = new JComboBox();
            Mode_Pay.setEditable(true);
            //adding items in jcombobox
            Mode_Pay.addItem("DD");
            Mode_Pay.addItem("Cheque");
            Mode_Pay.addItem("Debit Card");
            Mode_Pay.addItem("Credit Card");
            Mode_Pay.addItem("Cash");
            Choose_Car = new JLabel();
            Choose_Car.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            Book_Car = new JButton("Book Car");
            Reset = new JButton("Reset");
            Quit = new JButton("Quit");
            Update_Btn = new JButton("Update Customer Booking");
            Update_Btn.setVisible(false);
            Delete_Btn = new JButton("Delete Customer Booking");
            Delete_Btn.setVisible(false);
            //Delete_Btn.setVisible(false);
            //setting bounds for the components
            C_Id.setBounds(60,50,150,25);
            C_Id_Txt.setBounds(220,50,150,25);
            C_Name.setBounds(60,85,160,25);
            C_Name_Txt.setBounds(220,88,150,25);
            C_Address.setBounds(60,123,160,25);
            C_Address_Txt.setBounds(220,125,150,25);
            C_Deliver_O.setBounds(60,160,165,25);
            At_Home.setBounds(220,161,85,25);
            Take_In.setBounds(305,161,80,25);
            Mode_Of_Payement.setBounds(60,195,300,25);
            Mode_Pay.setBounds(270,195,100,25);
            Selected_Car.setBounds(60,230,280,25);
            Choose_Car.setBounds(220,228,150,25);
            Book_Car.setBounds(120,280,100,25);
            Reset.setBounds(240,280,80,25);
            Quit.setBounds(190,330,70,25);
            Update_Btn.setBounds(100,390,250,25);
            Delete_Btn.setBounds(100,390,250,25);
            //adding Components to jframe
            C_Frame.add(C_Id);
            C_Frame.add(C_Id_Txt);
            C_Frame.add(C_Name);
            C_Frame.add(C_Name_Txt);
            C_Frame.add(C_Address);
            C_Frame.add(C_Address_Txt);
            C_Frame.add(C_Deliver_O);
            C_Frame.add(At_Home);
            C_Frame.add(Take_In);
            C_Frame.add(Mode_Of_Payement);
            C_Frame.add(Mode_Pay);
            C_Frame.add(Selected_Car);
            C_Frame.add(Choose_Car);
            C_Frame.add(Book_Car);
            C_Frame.add(Reset);
            C_Frame.add(Quit);
            C_Frame.add(Update_Btn);
            C_Frame.add(Delete_Btn);
            Car_1.addActionListener((java.awt.event.ActionEvent Ae)->{
                Choose_Car.setText(Car_1.getText());});
            Car_2.addActionListener((java.awt.event.ActionEvent Ae)->{
                Choose_Car.setText(Car_2.getText());});
            Car_3.addActionListener((java.awt.event.ActionEvent Ae)->{
                Choose_Car.setText(Car_3.getText());});
            Car_4.addActionListener((java.awt.event.ActionEvent Ae)->{
                Choose_Car.setText(Car_4.getText());});
            Book_Car.addActionListener((java.awt.event.ActionEvent evt)->{
                Customer_Car_Booking();
            });
            Reset.addActionListener((java.awt.event.ActionEvent evt)->{
                C_Name_Txt.setText(null);
                C_Id_Txt.setText(null);
                C_Address_Txt.setText(null);
                At_Home.setSelected(true);
                Take_In.setSelected(false);
                Mode_Pay.setSelectedIndex(0);
                Choose_Car.setText(null);
                if(Update_Btn.isVisible()){
                    Update_Btn.setVisible(false);
                }else
                {
                    Delete_Btn.setVisible(false);
                }
            });
            Quit.addActionListener((java.awt.event.ActionEvent evt)->{
                System.exit(0);
            });
            Update.addActionListener((java.awt.event.ActionEvent Ae)->{
                C_Id_Txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
                C_Id_Txt.setBackground(Color.cyan);
                C_Id_Txt.setForeground(Color.MAGENTA);
                Book_Car.setVisible(false);
                Update_Btn.setVisible(true);
            });
            Delete.addActionListener((java.awt.event.ActionEvent Ae)->{
                C_Id_Txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
                C_Id_Txt.setBackground(Color.cyan);
                C_Id_Txt.setForeground(Color.MAGENTA);
                Book_Car.setVisible(false);
                Update_Btn.setVisible(false);
                C_Name_Txt.setEnabled(false);
                C_Address_Txt.setEnabled(false);
                At_Home.setEnabled(false);
                Take_In.setEnabled(false);
                Mode_Pay.setEnabled(false);
                Choose_Car.setEnabled(false);
                Select_Car.setEnabled(false);
                Delete_Btn.setVisible(true);
            });
            Show_Booking.addActionListener((java.awt.event.ActionEvent Ae)->{
                C_Frame.dispose();
                try{
                    CustomerApplication CA = new CustomerApplication();
                    CA.C_Frame.show(true);
                }catch(NullPointerException e){}
            });
            Update_Btn.addActionListener((java.awt.event.ActionEvent evt)->{
                if(At_Home.isSelected()==true){Delivery = "Delivery At Home";}
                else{Delivery="Drive by car";}
                switch(Mode_Pay.getSelectedItem().toString()){
                    case "DD":break;
                    case "Cheque":break;
                    case "Debit Card":break;
                    case "Credit Card":break;
                    case "Cash":break;
                    default:JOptionPane.showMessageDialog(null,"Entered Option is not Valid!!!");
                        break;
                }
                try{
                    Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Car_Application","root","9868");
                    Query = "update Customer set Customer_Name=?,Customer_Address=?,Delivery_Opt=?,"
                            + "Payement=?,Selected_Car=? where Customer_Id=?";
                    PStmt = Con.prepareStatement(Query);
                    PStmt.setString(1, C_Name_Txt.getText());
                    PStmt.setString(2, C_Address_Txt.getText());
                    PStmt.setString(3, Delivery);
                    PStmt.setString(4, Mode_Pay.getSelectedItem().toString());
                    PStmt.setString(5, Choose_Car.getText());
                    PStmt.setLong(6, Long.parseLong(C_Id_Txt.getText()));
                    int a = PStmt.executeUpdate();
                    if(a==1)JOptionPane.showMessageDialog(null,"Customer Record Successfully Updated");
                    else JOptionPane.showMessageDialog(null,"Customer Record is not Updated");
                }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
            });
            Delete_Btn.addActionListener((java.awt.event.ActionEvent evt)->{
                try{
                    Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Car_Application","root","9868");
                    Query="delete from Customer where Customer_Id=?;";
                    PStmt = Con.prepareStatement(Query);
                    PStmt.setLong(1, Long.parseLong(C_Id_Txt.getText()));
                    int a = PStmt.executeUpdate();
                    if(a==1)JOptionPane.showMessageDialog(null,"Customer is Successfully deleted");
                    else JOptionPane.showMessageDialog(null,"Customer record is not deleted");
                }catch(SQLException e){JOptionPane.showMessageDialog(null,e);}
            });
        });
    }
    private void Customer_Car_Booking(){
        if(At_Home.isSelected()==true){Delivery = "Delivery At Home";}
        else{Delivery="Drive by car";}
        switch(Mode_Pay.getSelectedItem().toString()){
            case "DD":break;
            case "Cheque":break;
            case "Debit Card":break;
            case "Credit Card":break;
            case "Cash":break;
            default:JOptionPane.showMessageDialog(null,"Entered Option is not Valid!!!");
                break;
        }try{
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Car_Application","root","9868");
            Query = "insert into Customer values(?,?,?,?,?,?);";
            PStmt = Con.prepareStatement(Query);
            PStmt.setLong(1,Long.parseLong(C_Id_Txt.getText()));
            PStmt.setString(2,C_Name_Txt.getText());
            PStmt.setString(3,C_Address_Txt.getText());
            PStmt.setString(4,Delivery);
            PStmt.setString(5,Mode_Pay.getSelectedItem().toString());
            PStmt.setString(6,Choose_Car.getText());
            int a = PStmt.executeUpdate();
            if(a==1){
                JOptionPane.showMessageDialog(null,"Customer Booking Successfull");
            }
            else{JOptionPane.showMessageDialog(null,"Car Data is not updated");}
        }catch(SQLException E){JOptionPane.showMessageDialog(null,E);}
    }
}
public class CarApplication{
    public static void main(String[] args){
        new CustomerApplication();
    }
}