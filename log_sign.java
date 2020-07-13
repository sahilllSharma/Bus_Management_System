import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;  
import java.util.Vector;                                                       
import javax.swing.table.*;
class log_sign implements ActionListener
{
	JFrame f_login;
	JButton Llogin,Lreset,ok_d;
	TextField text1,text2;
	JLabel msg1,user,pass;
	String stUser,stPass;
	Dialog d;
	int flag;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;
	
	Font f1=new Font("Times New Roman",Font.BOLD,28);
	Font f2=new Font("Times New Roman",Font.PLAIN,20);
	Font f3=new Font("Times New Roman",Font.PLAIN,14);
	Font f4=new Font("Times New Roman",Font.PLAIN,18);
	Font f5=new Font("Times New Roman",Font.BOLD,18);
	
	log_sign()
	{
		
		
		//Frame login
		
		
	
		f_login=new JFrame();
		f_login.setSize(500,450);
		f_login.setBackground(Color.LIGHT_GRAY);
		f_login.setLayout(null);
		f_login.setResizable(false);
		JLabel title=new JLabel("LOGIN");
		title.setFont(f1);
		title.setBounds(180,40,150,50);
		f_login.add(title);
		
		JLabel user=new JLabel("USERNAME");
		user.setBounds(100,130,100,30);
		user.setFont(f3);
		JLabel pass=new JLabel("PASSWORD");
		pass.setBounds(100,200,100,30);
		pass.setFont(f3);
		text1=new TextField("");
		text1.setBounds(220,130,150,30);
		text2=new TextField("");
		text2.setBounds(220,200,150,30);
		
		Llogin=new JButton("LOGIN");
		Llogin.setBounds(100,300,80,30);
		Llogin.setFont(f3);
		Llogin.addActionListener(this);
		Lreset=new JButton("RESET");
		Lreset.setBounds(280,300,80,30);
		Lreset.setFont(f3);
		Lreset.addActionListener(this);

		f_login.add(user);
		f_login.add(text1);
		f_login.add(pass);
		f_login.add(text2);
		f_login.add(Llogin);
		f_login.add(Lreset);
		
		f_login.setVisible(true);
		
		
		
		
		//Dialog Box
		
	
		
		d=new Dialog(f_login,"Message");
		d.setBounds(120,150,250,150);
		d.setLayout(null);
		msg1=new JLabel("");
		msg1.setFont(f3);
		msg1.setBounds(50,30,150,30);
		d.add(msg1);
		ok_d=new JButton("OK");
		ok_d.setBounds(100,80,60,30);
		ok_d.addActionListener(this);
		d.add(ok_d);
		d.setModal(true);
		d.setResizable(false);
		d.setVisible(false);
		
		
		
		
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==Llogin)
		{
			stUser=text1.getText();
			stPass=text2.getText();
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				stmt=con.createStatement();
				sql="select Username,Password from LoginDetails";
				rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					if(stUser.equals(rs.getString("Username")))
					{
						flag=1;
						if(stPass.equals(rs.getString("Password")))
						{
							msg1.setText("Welcome to Delux Bus Services");
							d.setVisible(true);
						}
						else
						{
							msg1.setText("Incorrect Password");
							d.setVisible(true);
						}
							
					}
				}
				if(flag==0)
				{
					msg1.setText("Invalid username");
					d.setVisible(true);
				}
			}
			catch(ClassNotFoundException ce)
			{
				System.out.println("Unable to load driver");
			}
			catch(SQLException ex)
			{
				System.out.println("Some database error"+ex);
			}
		}
		
		if(ae.getSource()==Lreset)
		{
			text1.setText(" ");
			text2.setText(" ");
		}
		
		if(ae.getSource()==ok_d)
		{
			d.setVisible(false);
			
		}
	}
	
	public static void main(String args[])
	{
		
	}
}