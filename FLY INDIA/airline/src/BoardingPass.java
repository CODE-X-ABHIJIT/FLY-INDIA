import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class BoardingPass extends JFrame implements ActionListener {
    JTextField tfpnr;
    JLabel tfusername,tfnationality,lblsource,labelfname,labelfcode,lbldest,tfsource,tfdestination,labeldate;
    JButton fetchButton;
    public BoardingPass(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading=new JLabel("FLY INDIA");
        heading.setBounds(380,10,450,35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        add(heading);

        JLabel subheading=new JLabel("Boarding Pass");
        subheading.setBounds(378,50,300,30);
        subheading.setFont(new Font("Tahoma",Font.PLAIN,24));
        subheading.setForeground(Color.BLUE);
        add(subheading);

        JLabel lblpnr=new JLabel("PNR DETAILS");
        lblpnr.setBounds(60,100,150,25);
        add(lblpnr);
        lblpnr.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfpnr= new JTextField();
        tfpnr.setBounds(220,100,150,25);
        add(tfpnr);

        fetchButton=new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380,100,120,25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname=new JLabel("NAME");
        lblname.setBounds(60,140,150,25);
        add(lblname);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfusername= new JLabel();
        tfusername.setBounds(220,140,150,25);
        add(tfusername);

        JLabel lblnationality=new JLabel("NATIONALITY");
        lblnationality.setBounds(60,180,150,25);
        add(lblnationality);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfnationality= new JLabel();
        tfnationality.setBounds(220,180,150,25);
        add(tfnationality);

        lblsource=new JLabel("Source");
        lblsource.setBounds(60,220,150,25);
        add(lblsource);
        lblsource.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfsource= new JLabel();
        tfsource.setBounds(220,220,150,25);
        add(tfsource);

        lbldest=new JLabel("Destination");
        lbldest.setBounds(380,220,150,25);
        add(lbldest);
        lbldest.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfdestination=new JLabel();
        tfdestination.setBounds(540,220,150,25);
        add(tfdestination);

        JLabel lblfname=new JLabel("Flight Name");
        lblfname.setBounds(60,260,150,25);
        add(lblfname);
        lblfname.setFont(new Font("Tahoma",Font.PLAIN,16));

        labelfname= new JLabel();
        labelfname.setBounds(220,260,150,25);
        add(labelfname);

        JLabel lblfcode=new JLabel("Flight Code");
        lblfcode.setBounds(380,260,150,25);
        add(lblfcode);
        lblfcode.setFont(new Font("Tahoma",Font.PLAIN,16));

        labelfcode= new JLabel();
        labelfcode.setBounds(540,260,150,25);
        add(labelfcode);

        JLabel lbldate=new JLabel("Date of Travel");
        lbldate.setBounds(60,300,150,25);
        add(lbldate);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,16));

        labeldate= new JLabel();
        labeldate.setBounds(220,300,150,25);
        add(labeldate);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/flyindia.png"));
        Image i2=i1.getImage().getScaledInstance(250,150,Image.SCALE_DEFAULT);
        ImageIcon image=new ImageIcon(i2);
        JLabel lblimage=new JLabel(image);
        lblimage.setBounds(600,50,300,300);
        add(lblimage);

        setSize(1000,450);
        setLocation(300,150);
        setVisible(true);
        setTitle("Add Customer");
    }

    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText();
        try {
            Conn conn = new Conn();

            String query = "select * from reservation where PNR='" + pnr + "'";

            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                tfusername.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                tfsource.setText(rs.getString("src"));
                tfdestination.setText(rs.getString("des"));
                labelfname.setText(rs.getString("flightname"));
                labelfcode.setText(rs.getString("flightcode"));
                labeldate.setText(rs.getString("ddate"));
            } else {
                JOptionPane.showMessageDialog(null, "Please Enter Correct PNR No.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}
