import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class homepage extends JFrame implements ActionListener
{
	JFrame f;
	Font f1;
	JLabel l;
	JButton b1,b2,b3,b4;
		homepage()
	{
		f=new JFrame("Home page");
		f.setVisible(true);
		f.setSize(500,500);
		f.setLayout(null);
		Font f1=new Font("Arial",Font.BOLD,20);
		l=new JLabel("Board of Electricity Department");
		l.setFont(f1);
		b1=new JButton("Consumer Registeration");
		b1.addActionListener(this);
		b2=new JButton("Billing");
		b2.addActionListener(this);
		b3=new JButton("Close");
		b3.addActionListener(this);
		b4=new JButton("Report");
		b4.addActionListener(this);
		l.setBounds(110,100,500,40);
		b1.setBounds(150,150,180,40);
		b2.setBounds(183,200,100,40);
		b4.setBounds(183,250,100,40);
		b3.setBounds(183,300,100,40);
		f.add(l);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","arun");
		Statement s=c.createStatement();
		ResultSet rs=s.executeQuery("select * from login");
		if(ae.getSource()==b1)
		{
			new conreg();
			f.dispose();
		}
		if(ae.getSource()==b2)
		{
			new billing();
			f.dispose();
		}
		if(ae.getSource()==b4)
		{
			new report();
			f.setVisible(false);
		}
		if(ae.getSource()==b3)
		{
			System.exit(0);
		}
		
	}
		catch(Exception e)
		{
				JOptionPane.showMessageDialog(f,e);
		}
	}
	public static void main(String ar[])
	{
		new homepage();
	}
}
