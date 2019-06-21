import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class billing extends JFrame implements ActionListener
{
	JFrame f;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t1,t2,t3,t4,t5,t6;
	JTextArea ta;
	String type;
	JScrollPane jsp;
	JList l;
	JComboBox cb;
	JButton b2,b3,b4,b5,b6,b7,b8;
		billing()
	{
		f=new JFrame("Billing");
		f.setVisible(true);
		f.setSize(900,700);
		f.setLayout(null);
		//f.getContentPane().setBackground(Color.white);
		l1=new JLabel("Bill No");
		t1=new JTextField(30);
		l2=new JLabel("Name");
		t2=new JTextField(30);
		l3=new JLabel("Address");
		ta=new JTextArea(4,30);
		jsp=new JScrollPane(ta);
		l4=new JLabel("Bill type");
		String des[]={"Domestic","Commercial"};
		cb=new JComboBox(des);
		l5=new JLabel("Previous Readings");
		t3=new JTextField(30);
		l6=new JLabel("Current Readings");
		t4=new JTextField(30);
		l7=new JLabel("No. of units used");
		t5=new JTextField(30);
		l8=new JLabel("Amount");
		t6=new JTextField(30);
		b2=new JButton("Usage");
		b2.addActionListener(this);
		b3=new JButton("Calculate");
		b3.addActionListener(this);
		b4=new JButton("Reset");
		b4.addActionListener(this);
		b5=new JButton("Close");
		b5.addActionListener(this);
		b6=new JButton("Back to home");
		b6.addActionListener(this);
		b7=new JButton("Save");
		b7.addActionListener(this);
		b8=new JButton("Search");
		b8.addActionListener(this);
		l1.setBounds(100,100,100,30);
		t1.setBounds(210,100,150,30);
		l2.setBounds(500,100,100,30);
		t2.setBounds(610,100,150,30);
		l3.setBounds(100,150,100,30);
		jsp.setBounds(210,150,150,60);
		l4.setBounds(500,150,100,30);
		cb.setBounds(610,150,150,30);
		l5.setBounds(100,260,120,30);
		t3.setBounds(210,260,150,30);
		l6.setBounds(500,260,100,30);
		t4.setBounds(610,260,150,30);
		l8.setBounds(500,330,100,30);
		b3.setBounds(500,360,100,40);
		t6.setBounds(610,330,150,30);
		l7.setBounds(100,330,100,30);
		t5.setBounds(210,330,150,30);
		b2.setBounds(100,360,100,40);
		b4.setBounds(430,480,100,40);
		b5.setBounds(540,480,100,40);
		b6.setBounds(650,480,120,40);
		b7.setBounds(320,480,100,40);
		b8.setBounds(500,200,100,40);
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(l3);
		f.add(jsp);
		f.add(l4);
		f.add(cb);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.add(t3);
		f.add(t4);
		f.add(t5);
		f.add(t6);
		f.add(l5);
		f.add(l6);
		f.add(l7);
		f.add(l8);
		f.add(b7);
		f.add(b8);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","arun");
		Statement s=c.createStatement();
		ResultSet rs=s.executeQuery("select * from details");
		if(ae.getSource()==b4) //reset
		{
			t1.setText("");
			t2.setText("");
			ta.setText("");
			cb.setSelectedIndex(0);
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
		}
		if(ae.getSource()==b8) //search
		{
			int count=0;
			rs=s.executeQuery("select * from details where Bill_No='"+t1.getText()+"'");
			while(rs.next())
			{
				count++;
				t2.setText(rs.getString(2));
				ta.setText(rs.getString(3));
				cb.setSelectedItem(rs.getString(4));
				type=rs.getString(4);
			}
			if(count==0)
			{
			JOptionPane.showMessageDialog(f,"RECORD Not Found");
			t1.setText("");
			}
		}
		if(ae.getSource()==b7) 
		{
			int r=s.executeUpdate("insert into details values('"+t1.getText()+"','"+t2.getText()+"','"+ta.getText()+"','"+cb.getSelectedItem()+"','"+t3.getText()+"','"+t4.getText()+"','"+t5.getText()+"','"+t6.getText()+"')");
			if(r!=0)
			{
				JOptionPane.showMessageDialog(f,new String("Record Inserted"));
			}
			t1.setText("");
			t2.setText("");
			ta.setText("");
			cb.setSelectedIndex(0);
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");

		}
		
		if(ae.getSource()==b6) //Homepage
		{
			new homepage();
			f.dispose();
		}
		if(ae.getSource()==b5) //exit
		{
			System.exit(0);
		}
		if(ae.getSource()==b2) //Usage
		{
			int prev=Integer.parseInt(t3.getText());
			int cur=Integer.parseInt(t4.getText());
			int usage=cur-prev;
			t5.setText(""+usage);
		}
		if(ae.getSource()==b3) //calculation
		{
			
			if(type.equals("Domestic"))
			{
			int use=Integer.parseInt(t5.getText());
			if(use<=100)
			{
				int amount=(use*1);
				t6.setText(""+amount);
			}
			else if((use>100)&&(use<=200))
			{
				
				int amount=(use*2);
				t6.setText(""+amount);
			}
			else if((use>200)&&(use<=300))
			{
				int amount=(use*3);
				t6.setText(""+amount);
			}
			else if(use>300)
			{
				
				int amount=(use*4);
				t6.setText(""+amount);
			}
		}
		else
			{
			int use=Integer.parseInt(t5.getText());
			if(use<=100)
			{
				double amount=use*5.00;
				t6.setText(""+amount);
			}
			if((use>100)&&(use<=200))
			{
				use=use-100;
				double amount=(100*5.00)+(use*5.20);
				t6.setText(""+amount);
			}
			if((use>200)&&(use<=300))
			{
				use=use-200;
				double amount=(100*5.00)+(100*5.20)+(use*5.45);
				t6.setText(""+amount);
			}
			if(use>300)
			{
				use=use-300;
				double amount=(100*5.00)+(100*5.20)+(100*5.45)+(use*5.60);
				t6.setText(""+amount);
			}
		}

		}
		}

		catch(Exception e)
		{
				JOptionPane.showMessageDialog(f,e);
		}
}
	public static void main(String ar[])
	{
		new billing();
	}
}
