import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class conreg extends JFrame implements ActionListener
{
	JFrame f;
	JLabel la,l1,l2,l3,l4;
	JTextField t1,t2;
	Font f1;
	JTextArea ta;
	JScrollPane jsp;
	JList l;
	String type;
	JComboBox cb;
	JButton b1,b2,b3,b4,b5,b6,b7;
		conreg()
	{
		f=new JFrame("Consumer Registration");
		f.setVisible(true);
		f.setSize(1000,650);
		f.setLayout(null);
		//f.getContentPane().setBackground(Color.white);/
		Font f1=new Font("Arial",Font.BOLD,25);
		la=new JLabel("Consumer Details");
		la.setFont(f1);
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
		b1=new JButton("Save");
		b1.addActionListener(this);
		b2=new JButton("Update");
		b2.addActionListener(this);
		b3=new JButton("Delete");
		b3.addActionListener(this);
		b4=new JButton("Reset");
		b4.addActionListener(this);
		b5=new JButton("Close");
		b5.addActionListener(this);
		b6=new JButton("Back to home");
		b6.addActionListener(this);
		b7=new JButton("Search");
		b7.addActionListener(this);
		la.setBounds(280,150,400,30);
		l1.setBounds(100,250,100,30);
		t1.setBounds(210,250,150,30);
		l2.setBounds(500,250,100,30);
		t2.setBounds(610,250,150,30);
		l3.setBounds(100,300,100,30);
		jsp.setBounds(210,300,150,60);
		l4.setBounds(500,300,100,30);
		cb.setBounds(610,300,150,30);
		b6.setBounds(100,150,150,40);
		b1.setBounds(250,450,100,40);
		b2.setBounds(360,450,100,40);
		b3.setBounds(470,450,100,40);
		b4.setBounds(580,450,100,40);
		b5.setBounds(690,450,100,40);
		b7.setBounds(140,450,100,40);
		f.add(la);
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(l3);
		f.add(jsp);
		f.add(l4);
		f.add(cb);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.add(b7);
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
		}
		if(ae.getSource()==b1) //save
		{
			int r=s.executeUpdate("insert into details(Bill_No,Name,Address,Bill_Type) values('"+t1.getText()+"','"+t2.getText()+"','"+ta.getText()+"','"+cb.getSelectedItem()+"')");
			if(r!=0)
			{
				JOptionPane.showMessageDialog(f,new String("Record Inserted"));
			}
			t1.setText("");
			t2.setText("");
			ta.setText("");
			cb.setSelectedIndex(0);
		}
		if(ae.getSource()==b7) //search
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
		if(ae.getSource()==b6) //Homepage
		{
			new homepage();
			f.dispose();
		}
		if(ae.getSource()==b5) //exit
		{
			System.exit(0);
		}
		if(ae.getSource()==b3) //delete
		{
			int r=s.executeUpdate("delete from details where Bill_No='"+t1.getText()+"'");
			if(r!=0)
			{
				JOptionPane.showMessageDialog(f,"ValueDeleted");
			}
			t1.setText("");
			t2.setText("");
			ta.setText("");
			cb.setSelectedIndex(0);
		}
		if(ae.getSource()==b2) //update
		{
			int r=s.executeUpdate("Update details set Bill_No='"+t1.getText()+"',Name='"+t2.getText()+"',Address='"+ta.getText()+"',Bill_Type='"+cb.getSelectedItem()+"'where Bill_No='"+t1.getText()+"'");
			if(r!=0)
			{
				JOptionPane.showMessageDialog(f,"Value Updated");
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
		new conreg();
	}
}
