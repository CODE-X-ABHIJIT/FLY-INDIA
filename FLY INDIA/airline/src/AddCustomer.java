import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {
    JTextField tfusername,tfphone,tfaadhaar,tfnationality,tfaddress;
    JRadioButton rbmale,rbfemale;
    public AddCustomer(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading=new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(250,20,500,40);
        add(heading);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        heading.setForeground(Color.BLUE);

        JLabel lblname=new JLabel("Name");
        lblname.setBounds(60,80,150,25);
        add(lblname);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));

         tfusername= new JTextField();
        tfusername.setBounds(220,80,150,25);
        add(tfusername);

        JLabel lblnationality=new JLabel("Nationality");
        lblnationality.setBounds(60,130,150,25);
        add(lblnationality);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));

         tfnationality= new JTextField();
        tfnationality.setBounds(220,130,150,25);
        add(tfnationality);

        JLabel lblaadhaar=new JLabel("Aadhaar Number");
        lblaadhaar.setBounds(60,180,150,25);
        add(lblaadhaar);
        lblaadhaar.setFont(new Font("Tahoma",Font.PLAIN,16));

         tfaadhaar= new JTextField();
        tfaadhaar.setBounds(220,180,150,25);
        add(tfaadhaar);

        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(60,230,150,25);
        add(lbladdress);
        lbladdress.setFont(new Font("Tahoma",Font.PLAIN,16));

         tfaddress= new JTextField();
        tfaddress.setBounds(220,230,150,25);
        add(tfaddress);

        JLabel lblgender=new JLabel("Gender");
        lblgender.setBounds(60,280,150,25);
        add(lblgender);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));

        ButtonGroup genderGroup=new ButtonGroup();

        rbmale=new JRadioButton("Male");
        rbmale.setBounds(220,280,70,25);
        rbmale.setBackground(Color.WHITE);
        add(rbmale);

        rbfemale=new JRadioButton("Female");
        rbfemale.setBounds(300,280,70,25);
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);

        genderGroup.add(rbmale);
        genderGroup.add(rbfemale);

        JLabel lblphone=new JLabel("Phone");
        lblphone.setBounds(60,330,150,25);
        add(lblphone);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN,16));

         tfphone= new JTextField();
        tfphone.setBounds(220,330,150,25);
        add(tfphone);

        JButton save=new JButton("SAVE");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setBounds(220,380,150,30);
        save.addActionListener(this);
        add(save);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/emp.gif"));
        Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon image=new ImageIcon(i2);
        JLabel lblimage=new JLabel(image);
        lblimage.setBounds(500,50,300,400);
        add(lblimage);

        setSize(900,600);
        setLocation(300,150);
        setVisible(true);
        setTitle("Add Customer");
    }
    public void actionPerformed(ActionEvent ae){

        String name=tfusername.getText();
        String nationality=tfnationality.getText();
        String phone= tfphone.getText();
        String address=tfaddress.getText();
        String aadhaar=tfaadhaar.getText();
        String gender=null;
        if (rbmale.isSelected()){
            gender="Male";
        }else {
            gender="Female";
        }
        try{
            Conn conn=new Conn();

            String query="insert into passenger values('"+name+"','"+nationality+"','"+phone+"','"+address+"','"+aadhaar+"','"+gender+"');";

            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null,"Customer Details Entered Successfully");
            setVisible(false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
