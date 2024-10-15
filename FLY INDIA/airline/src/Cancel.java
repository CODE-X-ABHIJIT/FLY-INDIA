import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Cancel extends JFrame implements ActionListener {
    JTextField tfpnr;
    JLabel tfusername,cancellationno,labelfcode,labeldate;
    JButton fetchButton,cancel;
    public Cancel(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random=new Random();

        JLabel heading=new JLabel("CANCELLATION");
        heading.setBounds(180,20,250,35);
        add(heading);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/cancel.gif"));
        Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(470,120,250,250);
        add(image);

        JLabel lblpnr=new JLabel("PNR Number");
        lblpnr.setBounds(60,80,150,25);
        add(lblpnr);
        lblpnr.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfpnr= new JTextField();
        tfpnr.setBounds(220,80,150,25);
        add(tfpnr);

        fetchButton=new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380,80,120,25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname=new JLabel("Name");
        lblname.setBounds(60,130,150,25);
        add(lblname);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfusername= new JLabel();
        tfusername.setBounds(220,130,150,25);
        add(tfusername);

        JLabel lblcancellation=new JLabel("Cancellation No.");
        lblcancellation.setBounds(60,180,150,25);
        add(lblcancellation);
        lblcancellation.setFont(new Font("Tahoma",Font.PLAIN,16));

        cancellationno= new JLabel(""+random.nextInt(1000000));
        cancellationno.setBounds(220,180,150,25);
        add(cancellationno);

        JLabel lblfcode=new JLabel("Flight Code");
        lblfcode.setBounds(60,230,150,25);
        add(lblfcode);
        lblfcode.setFont(new Font("Tahoma",Font.PLAIN,16));

        labelfcode= new JLabel();
        labelfcode.setBounds(220,230,150,25);
        add(labelfcode);

        JLabel lbldate=new JLabel("Date");
        lbldate.setBounds(60,280,150,25);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldate);

        labeldate= new JLabel();
        labeldate.setBounds(220,280,150,25);
        add(labeldate);


        cancel=new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220,330,120,25);
        cancel.addActionListener(this);
        add(cancel);


        setSize(800,450);
        setLocation(350,170);
        setVisible(true);
        setTitle("Add Customer");
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText();
            try {
                Conn conn = new Conn();

                String query = "select * from reservation where PNR='" + pnr + "'";

                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    tfusername.setText(rs.getString("name"));
                    labelfcode.setText(rs.getString("flightcode"));
                    labeldate.setText(rs.getString("ddate"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter Correct PNR No.");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            String name = tfusername.getText();
            String pnr = tfpnr.getText();
            String cancelno = cancellationno.getText();
            String fcode = labelfcode.getText();
            String date = labeldate.getText();
            try {
                Conn conn = new Conn();

                String query = "insert into cancel values('" + pnr + "','" + name + "','" + cancelno + "','" + fcode + "','" + date + "')";

                conn.s.executeUpdate(query);
                conn.s.executeUpdate("delete from reservation where PNR='" + pnr + "'");

                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}
