import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

import com.toedter.calendar.JDateChooser;

public class BookFlight extends JFrame implements ActionListener {
    JTextField tfaadhaar;
    JLabel tfusername,tfnationality,tfaddress,labelgender,labelfname,labelfcode;
    JButton bookflight,fetchButton,flight;
    Choice source,destination;
    JDateChooser dcdate;
    public BookFlight(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading=new JLabel("Book Flight");
        heading.setBounds(420,20,500,40);
        add(heading);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        heading.setForeground(Color.BLUE);

        JLabel lblaadhaar=new JLabel("Aadhaar");
        lblaadhaar.setBounds(60,80,150,25);
        add(lblaadhaar);
        lblaadhaar.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfaadhaar= new JTextField();
        tfaadhaar.setBounds(220,80,150,25);
        add(tfaadhaar);

        fetchButton=new JButton("Fetch User");
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

        JLabel lblnationality=new JLabel("Nationality");
        lblnationality.setBounds(60,180,150,25);
        add(lblnationality);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfnationality= new JLabel();
        tfnationality.setBounds(220,180,150,25);
        add(tfnationality);

        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(60,230,150,25);
        add(lbladdress);
        lbladdress.setFont(new Font("Tahoma",Font.PLAIN,16));

        tfaddress= new JLabel();
        tfaddress.setBounds(220,230,150,25);
        add(tfaddress);

        JLabel lblgender=new JLabel("Gender");
        lblgender.setBounds(60,280,150,25);
        add(lblgender);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));

        labelgender=new JLabel();
        labelgender.setBounds(220,280,150,25);
        add(labelgender);

        JLabel lblsource=new JLabel("Source");
        lblsource.setBounds(60,330,150,25);
        add(lblsource);
        lblsource.setFont(new Font("Tahoma",Font.PLAIN,16));

        source=new Choice();
        source.setBounds(220,330,150,25);
        add(source);

        JLabel lbldest=new JLabel("Destination");
        lbldest.setBounds(60,380,150,25);
        add(lbldest);
        lbldest.setFont(new Font("Tahoma",Font.PLAIN,16));

        destination=new Choice();
        destination.setBounds(220,380,150,25);
        add(destination);

        try{
            Conn c=new Conn();
            String query="select * from flight";
            ResultSet rs= c.s.executeQuery(query);

            while (rs.next()){
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        flight=new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380,380,120,25);
        flight.addActionListener(this);
        add(flight);

        JLabel lblfname=new JLabel("Flight Name");
        lblfname.setBounds(60,430,150,25);
        add(lblfname);
        lblfname.setFont(new Font("Tahoma",Font.PLAIN,16));

        labelfname= new JLabel();
        labelfname.setBounds(220,430,150,25);
        add(labelfname);

        JLabel lblfcode=new JLabel("Flight Code");
        lblfcode.setBounds(60,480,150,25);
        add(lblfcode);
        lblfcode.setFont(new Font("Tahoma",Font.PLAIN,16));

        labelfcode= new JLabel();
        labelfcode.setBounds(220,480,150,25);
        add(labelfcode);

        JLabel lbldate=new JLabel("Date of Travel");
        lbldate.setBounds(60,530,150,25);
        add(lbldate);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,16));

        dcdate= new JDateChooser();
        dcdate.setBounds(220,530,150,25);
        add(dcdate);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/book.gif"));
        Image i2=i1.getImage().getScaledInstance(450,350,Image.SCALE_DEFAULT);
        ImageIcon image=new ImageIcon(i2);
        JLabel lblimage=new JLabel(image);
        lblimage.setBounds(550,80,500,410);
        add(lblimage);

        bookflight=new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220,580,150,25);
        bookflight.addActionListener(this);
        add(bookflight);

        setSize(1100,700);
        setLocation(200,50);
        setVisible(true);
        setTitle("Add Customer");
    }
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == fetchButton) {
            String aadhaar = tfaadhaar.getText();
            try {
                Conn conn = new Conn();

                String query = "select * from passenger where aadhaar='" + aadhaar + "'";

                ResultSet rs=conn.s.executeQuery(query);

                if (rs.next()){
                    tfusername.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                }else {
                    JOptionPane.showMessageDialog(null,"Please Enter Correct Aadhaar");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest=destination.getSelectedItem();
            try {
                Conn conn = new Conn();

                String query = "select * from flight where source='" + src + "' and destination='" + dest + "'";

                ResultSet rs=conn.s.executeQuery(query);

                if (rs.next()){
                    labelfname.setText(rs.getString("f_name"));
                    labelfcode.setText(rs.getString("f_code"));
                }else {
                    JOptionPane.showMessageDialog(null,"No Flights Found");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Random random=new Random();

            String aadhaar=tfaadhaar.getText();
            String name= tfusername.getText();
            String nationality=tfnationality.getText();
            String address=tfaddress.getText();
            String flightname=labelfname.getText();
            String flightcode=labelfcode.getText();
            String src= source.getSelectedItem();
            String dest=destination.getSelectedItem();
            String ddate= ((JTextField)dcdate.getDateEditor().getUiComponent()).getText();

            try {
                Conn conn = new Conn();

                String query = "insert into reservation values ('PNR-"+random.nextInt(1000000)+"','TIC-"+random.nextInt(10000)+"','"+aadhaar+"','"+name+"','"+nationality+"','"+flightname+"','"+flightcode+"','"+src+"','"+dest+"','"+ddate+"');";

               conn.s.executeUpdate(query);


                    JOptionPane.showMessageDialog(null,"Ticket Booked Successfully");
                    setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
