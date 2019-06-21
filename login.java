import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class loginpage implements ActionListener
{
	JFrame f;
	JLabel l1,l2;
	JTextField t1;
	JPasswordField p1;
	JButton b1,b2;
		loginpage()
	{
		f=new JFrame("Login Page");
		f.setVisible(true);

		f.setSize(700,700);
		f.setLayout(null);
		//f.getContentPane().setBackground(Color.white);
		l1=new JLabel("Username");
		t1=new JTextField(30);
		l2=new JLabel("Password");
		p1=new JPasswordField(30);
		b1=new JButton("Login");
		b1.addActionListener(this);
		b2=new JButton("Close");
		b2.addActionListener(this);
		l1.setBounds(200,200,100,30);
		t1.setBounds(310,200,150,30);
		l2.setBounds(200,250,100,30);
		p1.setBounds(310,250,150,30);
		b1.setBounds(200,300,100,40);
		b2.setBounds(310,300,100,40);
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(p1);
		f.add(b1);
		f.add(b2);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","arun");
		Statement s=c.createStatement();
		ResultSet rs=s.executeQuery("select * from login");;
		if(ae.getSource()==b1)
		{
			int success=0;
			while(rs.next())
			{
				if( (t1.getText()).equals(rs.getString(1) )&&(p1.getText()).equals(rs.getString(2) ))
				{
				JOptionPane.showMessageDialog(f,new String("Welcome "+rs.getString(1) ) );
				new homepage();
				f.dispose();
				
				success++;
				}
			}
			if(success==0)
			{
				JOptionPane.showMessageDialog(f,new String("Invalid User"));
				t1.setText("");
				p1.setText("");
			}	
		}
			
		
		if(ae.getSource()==b2)
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
		new loginpage();
	}
}
