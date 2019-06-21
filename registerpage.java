import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class registerpage extends JFrame implements ActionListener
{
	JFrame f;
	Font f1;
	JLabel l,l1,l2;
	JTextField t1;
	JPasswordField p1;
	JButton b1,b2;
		registerpage()
	{
		f=new JFrame("Register Page");
		Font f1=new Font("Arial",Font.BOLD,20);
		f.setVisible(true);
		f.setSize(700,700);
		f.setLayout(null);
		//f.getContentPane().setBackground(Color.white);
		l=new JLabel("Register Page");
		l.setFont(f1);
		l1=new JLabel("Enter Username");
		t1=new JTextField(30);
		l2=new JLabel("Enter Password");
		p1=new JPasswordField(30);
		b1=new JButton("Register now");
		b1.addActionListener(this);
		b2=new JButton("Close");
		b2.addActionListener(this);
		l.setBounds(200,100,700,30);
		l1.setBounds(200,200,100,30);
		t1.setBounds(370,200,150,30);
		l2.setBounds(200,250,200,30);
		p1.setBounds(370,250,150,30);
		b1.setBounds(200,350,150,30);
		b2.setBounds(370,350,100,40);
		f.add(l1);
		f.add(l);
		f.add(l2);
		f.add(t1);
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
		ResultSet rs=s.executeQuery("select * from login");
		if(ae.getSource()==b1)
		{

			int r=s.executeUpdate("insert into login values('"+t1.getText()+"','"+p1.getText()+"')");
			if(r!=0)
			{
				JOptionPane.showMessageDialog(f,new String("Registered Successfully"));
				new register();
				f.dispose();
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
		new registerpage();
	}
}
