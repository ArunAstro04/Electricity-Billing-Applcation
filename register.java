import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class register extends JFrame implements ActionListener
{
	JFrame f;
	JLabel l,l1,l2;
	Font f1;
	JButton b1,b2,b3;
		register()
	{
		f=new JFrame("Electricity Billing");
		Font f1=new Font("Arial",Font.BOLD,20);
		f.setVisible(true);
		f.setSize(700,700);
		f.setLayout(null);
		//f.setContentPane(new JLabel(new ImageIcon("D:\\pro\\1.jpg")));
		//f.getContentPane().setBackground(Color.white);
		l=new JLabel("Board of Electricity Department");
		l.setFont(f1);
		l1=new JLabel("New user?");
		b1=new JButton("Register Now");
		b1.addActionListener(this);
		l2=new JLabel("Already have an account?");
		b2=new JButton("Login");
		b2.addActionListener(this);
		b3=new JButton("Close");
		b3.addActionListener(this);
		l.setBounds(200,100,700,30);
		l1.setBounds(200,200,100,30);
		b1.setBounds(370,200,150,30);
		l2.setBounds(200,250,200,30);
		b2.setBounds(370,250,150,30);
		b3.setBounds(370,300,100,40);
		f.add(l1);
		f.add(l);
		f.add(l2);
		f.add(b1);
		f.add(b2);
		f.add(b3);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
	/*	Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","arun");
		Statement s=c.createStatement();
		ResultSet rs=s.executeQuery("select * from login");; */
		if(ae.getSource()==b1)
		{
			new registerpage();
			f.dispose();
		}
		if(ae.getSource()==b2)
		{
			new loginpage();
			f.dispose();
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
		new register();
	}
}
