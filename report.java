import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
class report
{
	JFrame f;
	JTable table;
	report()
	{
		f = new JFrame();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE );
		f.setLocationRelativeTo(null);
		Vector columnNames = new Vector();
		Vector data = new Vector();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","arun");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from details");
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			for (int i = 1; i <= columns; i++)
			{
				columnNames.addElement( md.getColumnName(i));
			}
			while (rs.next())
			{
				Vector row = new Vector(columns);
				for (int i = 1; i <= columns; i++)
				{
					row.addElement( rs.getObject(i) );
				}
				data.addElement( row );
			}
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println( e );
		}
		table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane( table);
		f.add( scrollPane );
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args)
	{
		new report();
	}
}