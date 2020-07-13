import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*; 
import java.awt.print.*; 
import java.util.Vector;                                                        //javac -Xlint:unchecked Busticket.java
import javax.swing.table.*;

class BusTicket implements ActionListener,ItemListener,Printable
{

	JFrame main,details,reserv,fare,ticket,gen_ticket,contactUs;
	JButton login,detail,contact,Selseat,home,back1,back2,back3,back4,back5,back6,confirm,confirm2,confirm3,select_seat,ok,submit,reset,generate,
			cancel,ok_d1,ok_d2,ok_d3,ok_d4,Tprint,Tcancel;
			
	JButton[] s= new JButton[19];
	JPanel seat_arr1,seat_arr2,seat_arr3,p,ticketP;
	JLabel msg,msg2,msg3,msg4,sel_seat,ticket_bID,ticket_source,ticket_dest,ticket_bus,ticket_date,ticket_time,ticket_dist,ticket_fare,ticket_name,ticket_phn,ticket_seat;
	Vector columnNames,data;
	Dialog d1,d2,d3,d4;
	List st;
	TextField username,password,Uname,Upass,fullName,email,tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10,tf11,tf12,tf13,tf14,tf15,tf16,tf17,tf18;
	JRadioButton b1,b2,b3,b4;
	ButtonGroup bg1,bg2; 
	Checkbox c1,c2,c3,c4;
	CheckboxGroup cg1,cg2;
	String bus_id,tour_id,name,source,dest,time,date,passName,phn,station,sel_st,St_ID,seat;
	int i,bus_ID,tour_ID,dist,st_ID,fare_km,Tfare,check,seatNo;
	int[] flag=new int[19];
	JScrollPane sp;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;
	JComboBox<String> cb;
	Font f1=new Font("Times New Roman",Font.BOLD,36);
	Font f2=new Font("Times New Roman",Font.PLAIN,20);
	Font f3=new Font("Times New Roman",Font.PLAIN,14);
	Font f4=new Font("Times New Roman",Font.PLAIN,18);
	Font f5=new Font("Times New Roman",Font.BOLD,18);
	
	BusTicket()
	{
		fare_km=5;
		
		
		columnNames=new Vector();
		p=new JPanel();
		data=new Vector();
		
		
		
		//Frame main
		
		
		
		main=new JFrame();
		main.setSize(900,750);
		main.setBackground(Color.LIGHT_GRAY);
		main.setLayout(null);
		main.setResizable(false);
		JLabel title1=new JLabel("DELUX BUS SERVICE");
		title1.setFont(f1);
		title1.setBounds(250,40,380,50);
		main.add(title1);
		
		login=new JButton("Login");
		login.setFont(f2);
		login.setBounds(650,150,150,40);
		main.add(login);
		login.addActionListener(this);
		
		
	
		
		
		detail=new JButton("Bus Details");
		detail.setFont(f2);
		detail.setBounds(650,250,150,40);
		main.add(detail);
		detail.addActionListener(this);
		
		
		contact=new JButton("Contact Us");
		contact.setFont(f2);
		contact.setBounds(650,350,150,40);
		main.add(contact);
		contact.addActionListener(this);
		
		
		JLabel background=new JLabel(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\bus.jpg"));
		background.setBounds(70,150,500,400);
		main.add(background);
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		
		
		
		
		
		//Frame details
		
		
		
		
		
		details=new JFrame();
		details.setSize(900,750);
		details.setBackground(Color.LIGHT_GRAY);
		details.setLayout(null);
		details.setResizable(false);
		JLabel title2=new JLabel("BUS DETAILS");
		title2.setFont(f1);
		title2.setBounds(340,40,380,50);
		details.add(title2);
		
		
		JLabel bg=new JLabel(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\map.jpg"));
		bg.setBounds(200,110,500,150);
		details.add(bg);
		
		
		try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				Statement stmt=con.createStatement();
				String sql="select * from BusDetails";
				ResultSet rs=stmt.executeQuery(sql);
				ResultSetMetaData md=rs.getMetaData();
				int columns=md.getColumnCount();
				for(int i=1;i<=columns;i++)
				{
					columnNames.addElement(md.getColumnName(i));
				}
				while(rs.next())
				{
					Vector row=new Vector(columns);
					for(int i=1;i<=columns;i++)
					{
						row.addElement(rs.getObject(i));
					}
					data.addElement(row);
					
				}
				rs.close();
				stmt.close();
			}
			catch(ClassNotFoundException ce)
			{
				System.out.println("Unable to load driver");
			}
			catch(SQLException ex)
			{
				System.out.println("Some database error"+ex);
			}
		JTable table=new JTable(data,columnNames);
		TableColumn col;
		for(int i=0;i<table.getColumnCount();i++)
		{
			col=table.getColumnModel().getColumn(i);//.setPreferredWidth(100);
            col.setPreferredWidth(100); 
			//col.setMaxWidth(400);
		}
		JScrollPane scrollPane=new JScrollPane(table);
		p.add(scrollPane);
		details.add(p);
		p.setBounds(50,300,800,88);
		
		JLabel select1=new JLabel("Select Bus ID:");
		select1.setFont(f2);
		select1.setBounds(100,430,200,30);
		details.add(select1);
		JLabel select2=new JLabel("Select Tour ID:");
		select2.setFont(f2);
		select2.setBounds(550,430,200,30);
		details.add(select2);
	
		cg1=new CheckboxGroup();
		c1=new Checkbox("101",cg1,false);
		c2=new Checkbox("102",cg1,false);
		c1.setBounds(100,490,70,30);
		c2.setBounds(100,510,70,30);
		details.add(c1);
		details.add(c2);
		
		cg2=new CheckboxGroup();
		c3=new Checkbox("1",cg2,false);
		c4=new Checkbox("2",cg2,false);
		c3.setBounds(550,490,70,30);
		c4.setBounds(550,510,70,30);
		details.add(c3);
		details.add(c4);
		
		confirm=new JButton("Confirm");
		confirm.setBounds(400,610,100,30);
		confirm.addActionListener(this);
		details.add(confirm);
		
		back1=new JButton("Back");
		back1.setBounds(50,50,100,30);
		back1.setFont(f2);
		details.add(back1);
		back1.addActionListener(this);
		
		
		
		
		//Frame reserv
		
		
		
		
		
		reserv=new JFrame();
		reserv.setSize(900,750);
		reserv.setBackground(Color.LIGHT_GRAY);
		reserv.setLayout(null);
		reserv.setResizable(false);
		JLabel title3=new JLabel("RESERVATION");
		title3.setFont(f1);
		title3.setBounds(320,40,380,50);
		reserv.add(title3);
		reserv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		back2=new JButton("Back");
		back2.setBounds(50,50,100,30);
		back2.setFont(f2);
		reserv.add(back2);
		back2.addActionListener(this);
		
		JLabel sourceSt=new JLabel("Source Station");
		sourceSt.setFont(f3);
		sourceSt.setBounds(50,130,100,25);
		reserv.add(sourceSt);
		JLabel destSt=new JLabel("Destination Station");
		destSt.setFont(f3);
		destSt.setBounds(300,130,130,25);
		reserv.add(destSt);
		JLabel StID=new JLabel("Station ID");
		StID.setFont(f3);
		StID.setBounds(640,130,100,25);
		reserv.add(StID);
		JLabel ldate=new JLabel("Date");
		ldate.setFont(f3);
		ldate.setBounds(120,220,60,25);
		reserv.add(ldate);
		JLabel dateFor=new JLabel("(DD/MM/YYYY)");
		dateFor.setFont(f3);
		dateFor.setBounds(310,220,100,25);
		reserv.add(dateFor);
		JLabel ltime=new JLabel("Time");
		ltime.setFont(f3);
		ltime.setBounds(500,220,100,25);
		reserv.add(ltime);
		
		cb = new JComboBox<String>();	
		cb.setBackground(Color.white);
		cb.setFont(f3);
		cb.setBounds(430,130,100,30);
		cb.addItemListener(this);
		reserv.add(cb);
		ok=new JButton("OK");
		ok.setFont(f3);
		ok.setBounds(540,130,60,30);
		ok.addActionListener(this);
		reserv.add(ok);
		
		tf1=new TextField("");
		tf1.setBounds(160,130,100,25);
		reserv.add(tf1);
		tf3=new TextField("");
		tf3.setBounds(740,130,100,25);
		reserv.add(tf3);
		tf4=new TextField("");
		tf4.setBounds(190,220,110,25);
		reserv.add(tf4);
		tf5=new TextField("");
		tf5.setBounds(600,220,110,25);
		reserv.add(tf5);
		
		select_seat=new JButton("Select Seat");
		select_seat.setFont(f2);
		select_seat.setBounds(380,280,130,30);
		select_seat.addActionListener(this);
		reserv.add(select_seat);
		
		sel_seat=new JLabel("Selected seat is");
		sel_seat.setBounds(370,540,130,25);
		sel_seat.setFont(f4);
		reserv.add(sel_seat);
		sel_seat.setVisible(false);
		tf6=new TextField("");
		tf6.setBounds(500,540,50,25);
		reserv.add(tf6);
		tf6.setVisible(false);
		confirm2=new JButton("Confirm");
		confirm2.setFont(f2);
		confirm2.setBounds(410,630,100,30);
		confirm2.addActionListener(this);
		reserv.add(confirm2);
		confirm2.setVisible(false);
		
		
		
		//Seat Panel
		
		
		
		seat_arr1=new JPanel();
		seat_arr1.setLayout(new GridLayout(1,6,30,20));
		seat_arr1.setBounds(250,350,390,37);
		reserv.add(seat_arr1);
		seat_arr1.setVisible(false);
		
		
		seat_arr2=new JPanel();
		seat_arr2.setLayout(new GridLayout(1,6,30,20));
		seat_arr2.setBounds(250,392,390,37);
		reserv.add(seat_arr2);
		seat_arr2.setVisible(false);
		
		
		seat_arr3=new JPanel();
		seat_arr3.setLayout(new GridLayout(1,6,30,20));
		seat_arr3.setBounds(250,460,390,37);
		reserv.add(seat_arr3);
		seat_arr3.setVisible(false);
		
		
		for(i=1;i<=18;i++)
		{
			if(i==1)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g1.jpg"));
			if(i==2)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g2.jpg"));
			if(i==3)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g3.jpg"));
			if(i==4)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g4.jpg"));
			if(i==5)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g5.jpg"));
			if(i==6)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g6.jpg"));
			if(i==7)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g7.jpg"));
			if(i==8)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g8.jpg"));
			if(i==9)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g9.jpg"));
			if(i==10)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g10.jpg"));
			if(i==11)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g11.jpg"));
			if(i==12)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g12.jpg"));
			if(i==13)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g13.jpg"));
			if(i==14)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g14.jpg"));
			if(i==15)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g15.jpg"));
			if(i==16)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g16.jpg"));
			if(i==17)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g17.jpg"));
			if(i==18)
				s[i]=new JButton(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\g18.jpg"));
			
			
			
			s[i].addActionListener(this);
		}
		
		
		for(i=1;i<=6;i++)
		{
			seat_arr1.add(s[i]); 
		}
		
		
		for(i=7;i<=12;i++)
		{
			seat_arr2.add(s[i]); 
		}
		
		for(i=13;i<=18;i++)
		{
			seat_arr3.add(s[i]); 
		}
		
		
	
		


		//fare calculator
		
		
		
		
		
		fare=new JFrame();
		fare.setSize(900,750);
		fare.setBackground(Color.LIGHT_GRAY);
		fare.setLayout(null);
		fare.setResizable(false);
		JLabel title4=new JLabel("FARE CALCULATOR");
		title4.setFont(f1);
		title4.setBounds(300,40,380,50);
		fare.add(title4);
		fare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		back3=new JButton("Back");
		back3.setBounds(50,50,100,30);
		back3.setFont(f2);
		fare.add(back3);
		back3.addActionListener(this);
		
		JLabel busFare=new JLabel(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\fare.jpg"));
		busFare.setBounds(560,80,300,300);
		fare.add(busFare);
		
		JLabel l1=new JLabel("Source Station");
		l1.setFont(f4);
		l1.setBounds(100,150,150,30);
		fare.add(l1);
		JLabel l2=new JLabel("Destination Station");
		l2.setFont(f4);
		l2.setBounds(100,200,150,30);
		fare.add(l2);
		JLabel l3=new JLabel("Total Distance (in Kms)");
		l3.setFont(f4);
		l3.setBounds(100,250,200,30);
		fare.add(l3);
		JLabel l4=new JLabel("Approximate fare");
		l4.setFont(f4);
		l4.setBounds(100,300,150,30);
		fare.add(l4);
		
		tf7=new TextField("");
		tf7.setBounds(330,150,120,30);
		fare.add(tf7);
		tf8=new TextField("");
		tf8.setBounds(330,200,120,30);
		fare.add(tf8);
		tf9=new TextField("");
		tf9.setBounds(330,250,120,30);
		fare.add(tf9);
		tf10=new TextField("");
		tf10.setBounds(330,300,120,30);
		fare.add(tf10);
		
		confirm3=new JButton("CONFIRM");
		confirm3.setFont(f2);
		confirm3.setBounds(420,550,130,30);
		confirm3.addActionListener(this);
		fare.add(confirm3);
		
		
		
		
		
		//Ticket
		
		
		
		
		
		ticket=new JFrame();
		ticket.setSize(900,750);
		ticket.setBackground(Color.LIGHT_GRAY);
		ticket.setLayout(null);
		ticket.setResizable(false);
		JLabel title5=new JLabel("TICKET");
		title5.setFont(f1);
		title5.setBounds(330,40,380,50);
		ticket.add(title5);
		ticket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		JLabel busTicket=new JLabel(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\ticket.jpg"));
		busTicket.setBounds(550,130,250,250);
		ticket.add(busTicket);
		
		back4=new JButton("Back");
		back4.setBounds(50,50,100,30);
		back4.setFont(f2);
		ticket.add(back4);
		back4.addActionListener(this);
		
		JLabel t1=new JLabel("Date");
		t1.setFont(f4);
		t1.setBounds(100,150,150,30);
		ticket.add(t1);
		JLabel t2=new JLabel("Bus ID");
		t2.setFont(f4);
		t2.setBounds(100,200,150,30);
		ticket.add(t2);
		JLabel t3=new JLabel("Source station");
		t3.setFont(f4);
		t3.setBounds(100,250,200,30);
		ticket.add(t3);
		JLabel t4=new JLabel("Destination");
		t4.setFont(f4);
		t4.setBounds(100,300,150,30);
		ticket.add(t4);
		JLabel t5=new JLabel("Selected Seat");
		t5.setFont(f4);
		t5.setBounds(100,350,150,30);
		ticket.add(t5);
		JLabel t6=new JLabel("Enter Name of passenger");
		t6.setFont(f4);
		t6.setBounds(100,450,200,30);
		ticket.add(t6);
		JLabel t7=new JLabel("Phone number");
		t7.setFont(f4);
		t7.setBounds(100,500,150,30);
		ticket.add(t7);
		
		tf11=new TextField("");
		tf11.setBounds(330,150,120,30);
		ticket.add(tf11);
		tf12=new TextField("");
		tf12.setBounds(330,200,120,30);
		ticket.add(tf12);
		tf13=new TextField("");
		tf13.setBounds(330,250,120,30);
		ticket.add(tf13);
		tf14=new TextField("");
		tf14.setBounds(330,300,120,30);
		ticket.add(tf14);
		tf15=new TextField("");
		tf15.setBounds(330,350,120,30);
		ticket.add(tf15);
		tf16=new TextField("");
		tf16.setBounds(330,450,120,30);
		ticket.add(tf16);
		tf17=new TextField("");
		tf17.setBounds(330,500,120,30);
		ticket.add(tf17);
		
		submit=new JButton("SUBMIT");
		submit.setBounds(260,570,100,30);
		submit.addActionListener(this);
		ticket.add(submit);
		reset=new JButton("RESET");
		reset.setBounds(560,570,100,30);
		reset.addActionListener(this);
		ticket.add(reset);
		generate=new JButton("GENERATE TICKET");
		generate.setBounds(385,640,150,30);
		generate.addActionListener(this);
		ticket.add(generate);
		generate.setVisible(false);
		
		
		
		
		
		// gen_ticket
		
		
		
		
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		
		ticketP=new JPanel();
		ticketP.setLayout(null);
		ticketP.setBackground(Color.white);
		ticketP.setBounds(100,100,700,520);
		ticketP.setBorder(BorderFactory.createLineBorder(Color.black));
		
		gen_ticket=new JFrame();
		gen_ticket.setSize(900,750);
		gen_ticket.setBackground(Color.white);
		gen_ticket.setLayout(null);
		gen_ticket.setResizable(false);
		JLabel title6=new JLabel("DELUX BUS SERVICES");
		title6.setFont(f1);
		title6.setBounds(160,30,400,50);
		ticketP.add(title6);
		//gen_ticket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		gen_ticket.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		
		back5=new JButton("Back");
		back5.setBounds(50,50,100,30);
		back5.setFont(f2);
		gen_ticket.add(back5);
		back5.addActionListener(this);
		
		home=new JButton("Home");
		home.setBounds(750,50,100,30);
		home.setFont(f2);
		gen_ticket.add(home);
		home.addActionListener(this);
		
		
		JLabel busID=new JLabel("BUS ID");
		busID.setBounds(100,110,150,30);
		busID.setFont(f4);
		ticketP.add(busID);
		JLabel busName=new JLabel("BUS NAME");
		busName.setBounds(100,140,150,30);
		busName.setFont(f4);
		ticketP.add(busName);
		JLabel date_jrny=new JLabel("DATE OF JOURNEY");
		date_jrny.setBounds(100,170,180,30);
		date_jrny.setFont(f4);
		ticketP.add(date_jrny);
		JLabel Source=new JLabel("SOURCE STATION");
		Source.setBounds(100,200,200,30);
		Source.setFont(f4);
		ticketP.add(Source);
		JLabel destination=new JLabel("DESTINATION");
		destination.setBounds(100,230,150,30);
		destination.setFont(f4);
		ticketP.add(destination);
		JLabel timing=new JLabel("BUS TIMING");
		timing.setBounds(100,260,150,30);
		timing.setFont(f4);
		ticketP.add(timing);
		JLabel cost=new JLabel("FARE");
		cost.setBounds(100,290,150,30);
		cost.setFont(f4);
		ticketP.add(cost);
		JLabel info=new JLabel("PERSONAL DETAILS");
		info.setBounds(265,340,250,30);
		info.setFont(f5);
		ticketP.add(info);
		JLabel bookedSeat=new JLabel("SEAT NUMBER");
		bookedSeat.setBounds(100,400,150,30);
		bookedSeat.setFont(f4);
		ticketP.add(bookedSeat);
		JLabel passenger=new JLabel("PASSENGER NAME");
		passenger.setBounds(100,430,200,30);
		passenger.setFont(f4);
		ticketP.add(passenger);
		JLabel phone=new JLabel("PHONE NUMBER");
		phone.setBounds(100,460,150,30);
		phone.setFont(f4);
		ticketP.add(phone);
		
		
		
		ticket_bID=new JLabel();
		ticket_bID.setBounds(350,110,150,30);
		ticket_bID.setFont(f3);
		ticketP.add(ticket_bID);
		
		ticket_bus=new JLabel();
		ticket_bus.setBounds(350,140,150,30);
		ticket_bus.setFont(f3);
		ticketP.add(ticket_bus);
		
		ticket_date=new JLabel();
		ticket_date.setBounds(350,170,150,30);
		ticket_date.setFont(f3);
		ticketP.add(ticket_date);
		
		ticket_source=new JLabel();
		ticket_source.setBounds(350,200,150,30);
		ticket_source.setFont(f3);
		ticketP.add(ticket_source);
		
		ticket_dest=new JLabel();
		ticket_dest.setBounds(350,230,150,30);
		ticket_dest.setFont(f3);
		ticketP.add(ticket_dest);
		
		ticket_time=new JLabel();
		ticket_time.setBounds(350,260,150,30);
		ticket_time.setFont(f3);
		ticketP.add(ticket_time);
		
		ticket_fare=new JLabel();
		ticket_fare.setBounds(350,290,150,30);
		ticket_fare.setFont(f3);
		ticketP.add(ticket_fare);
		
		
		ticket_seat=new JLabel();
		ticket_seat.setBounds(350,400,150,30);
		ticket_seat.setFont(f3);
		ticketP.add(ticket_seat);
		
		ticket_name=new JLabel();
		ticket_name.setBounds(350,430,150,30);
		ticket_name.setFont(f3);
		ticketP.add(ticket_name);
		
		ticket_phn=new JLabel();
		ticket_phn.setBounds(350,460,150,30);
		ticket_phn.setFont(f3);
		ticketP.add(ticket_phn);
		
		
		gen_ticket.add(ticketP);
		
		Tprint=new JButton("PRINT TICKET");
		Tprint.setBounds(200,650,200,30);
		Tprint.setFont(f2);
		gen_ticket.add(Tprint);
		Tprint.addActionListener(this);
		
		
		Tcancel=new JButton("CANCEL TICKET");
		Tcancel.setBounds(550,650,200,30);
		Tcancel.setFont(f2);
		gen_ticket.add(Tcancel);
		Tcancel.addActionListener(this);
		
		
		
		
		//Frame CONTACT
		
		
		
		
		contactUs=new JFrame();
		contactUs.setSize(900,750);
		contactUs.setBackground(Color.white);
		contactUs.setLayout(null);
		contactUs.setResizable(false);
		JLabel title7=new JLabel("CONTACT US");
		title7.setFont(f1);
		title7.setBounds(310,30,400,50);
		contactUs.add(title7);
		contactUs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		back6=new JButton("Back");
		back6.setBounds(50,50,100,30);
		back6.setFont(f2);
		contactUs.add(back6);
		back6.addActionListener(this);
		
		JLabel service=new JLabel(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\24.jpg"));
		service.setBounds(550,130,300,300);
		contactUs.add(service);
		
		JLabel telephone=new JLabel(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\telephone.jpg"));
		telephone.setBounds(100,130,150,150);
		contactUs.add(telephone);
		JLabel tele=new JLabel("01236 722385");
		tele.setFont(f5);
		tele.setBounds(300,170,200,30);
		contactUs.add(tele);
		
		JLabel mobile=new JLabel(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\mobile.jpg"));
		mobile.setBounds(100,300,150,150);
		contactUs.add(mobile);
		JLabel mob=new JLabel("+91 9876540324");
		mob.setFont(f5);
		mob.setBounds(300,340,200,30);
		contactUs.add(mob);
		
		JLabel email=new JLabel(new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\email.jpg"));
		email.setBounds(100,460,150,150);
		contactUs.add(email);
		JLabel em=new JLabel("deluxbuses@gmail.com");
		em.setFont(f5);
		em.setBounds(300,500,200,30);
		contactUs.add(em);
		

		
		
		
		
		
		//Dialog Box1
		
		
		d1=new Dialog(details,"Message");
		d1.setBounds(360,330,200,130);
		d1.setLayout(null);
		msg=new JLabel("");
		msg.setFont(f3);
		msg.setBounds(60,30,150,30);
		d1.add(msg);
		ok_d1=new JButton("OK");
		ok_d1.setBounds(70,70,60,30);
		ok_d1.addActionListener(this);
		d1.add(ok_d1);
		d1.setModal(true);
		d1.setResizable(false);
		d1.setVisible(false);
		
		//Dialog Box2
		
		d2=new Dialog(reserv,"Message");
		d2.setBounds(360,330,230,130);
		d2.setLayout(null);
		msg2=new JLabel("");
		msg2.setFont(f3);
		msg2.setBounds(30,30,200,30);
		d2.add(msg2);
		ok_d2=new JButton("OK");
		ok_d2.setBounds(90,70,60,30);
		ok_d2.addActionListener(this);
		d2.add(ok_d2);
		d2.setModal(true);
		d2.setResizable(false);
		d2.setVisible(false);
		
		
		//Dialog Box3
		
		d3=new Dialog(ticket,"Message");
		d3.setBounds(360,330,230,130);
		d3.setLayout(null);
		msg3=new JLabel("");
		msg3.setFont(f3);
		msg3.setBounds(30,30,200,30);
		d2.add(msg3);
		ok_d3=new JButton("OK");
		ok_d3.setBounds(90,70,60,30);
		ok_d3.addActionListener(this);
		d3.add(ok_d3);
		d3.setModal(true);
		d3.setResizable(false);
		d3.setVisible(false);
	}
	
	
	
	
	
	
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
	{
		if (page > 0) 
		{ /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}
		/* User (0,0) is typically outside the imageable area, so we must
		* translate by the X and Y values in the PageFormat to avoid clipping
		*/
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		/* Now we perform our rendering */
		g.drawString("Hello world!", 100, 100);
		g.drawString("This is a new Text Line ",200,200);
		String str="This is a varible text";
		g.drawString(str,300,300);
		int x=10;
		String s=Integer.toString(x);
		g.drawString(s,500,300);

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}
	
	
	
	
	
	
	
	//Action Event
	
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==login)						//LOGIN
		{
			log_sign ob=new log_sign();
		}
		
		
		if(ae.getSource()==detail)						//DETAILS
		{
			main.setVisible(false);
			details.setVisible(true);
			details.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				
		}
		
		
		if(ae.getSource()==contact)						//CONTACT
		{
			main.setVisible(false);
			contactUs.setVisible(true);
		}
		
		
		
		//Confirm button in DETAILS
		
		
		if(ae.getSource()==confirm)
		{
			check=0;
			if(c1.getState())
			{
				bus_id=c1.getLabel();
				bus_ID=Integer.parseInt(bus_id);
			}
			if(c2.getState())
			{
				bus_id=c2.getLabel();
				bus_ID=Integer.parseInt(bus_id);
			}
			if(c3.getState())
			{
				tour_id=c3.getLabel();
				tour_ID=Integer.parseInt(tour_id);
			}
			if(c4.getState())
			{
				tour_id=c4.getLabel();
				tour_ID=Integer.parseInt(tour_id);
			}
			if((c1.getState()==false&&c2.getState()==false)&&((c3.getState()==false&&c4.getState()==true)||(c3.getState()==true&&c4.getState()==false)))
			{
				check=1;
				msg.setText("Select Bus ID");
				d1.setVisible(true);
			}
			if((c3.getState()==false&&c4.getState()==false)&&((c1.getState()==false&&c2.getState()==true)||(c1.getState()==true&&c2.getState()==false)))
			{
				check=1;
				msg.setText("Select Tour ID");
				d1.setVisible(true);
			}
			if((c1.getState()==false&&c2.getState()==false)&&(c3.getState()==false&&c4.getState()==false))
			{
				check=1;
				msg.setText("Select Bus ID and Tour ID");
				msg.setBounds(20,30,170,30);
				d1.setVisible(true);
			}
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				stmt=con.createStatement();
				sql="select * from BusDetails";
				rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					int bid=rs.getInt("BusID");
					int tid=rs.getInt("TourID");
					if(bid==bus_ID&&tid==tour_ID)
					{
						name=rs.getString("BusName");
						source=rs.getString("SourceStation");
						time=rs.getString("Time");
					}
				}
				con.close();
			}
			catch(ClassNotFoundException ce)
			{
				System.out.println("Unable to load driver");
			}
			catch(SQLException ex)
			{
				System.out.println("Some database error"+ex);
			}
			

			if(check==0)
			{
				details.setVisible(false);
				reserv.setVisible(true);
			tf1.setText(source);
			tf5.setText(time);
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				stmt=con.createStatement();
				if(tour_ID==1)
				{
					sql="select StationName from StationDetails where TourID=1";
				}
				if(tour_ID==2)
				{
					sql="select StationName from StationDetails where TourID=2";
				}
				rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					station=rs.getString("StationName");
					cb.addItem(station);
				}
				con.close();
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
			
		}
		
		
		
		//OK button in RESERVATION
		
		
		
		if(ae.getSource()==ok)
		{
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				stmt=con.createStatement();
				if(tour_ID==1)
				{
					sql="select StationID,StationName,DistanceFromSource from StationDetails where TourID=1";
				}
				if(tour_ID==2)
				{
					sql="select StationID,StationName,DistanceFromSource from StationDetails where TourID=2";
				}
				rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					station=rs.getString("StationName");
					if(station.equals(sel_st))
					{
						dest=sel_st;
						st_ID=rs.getInt("StationID");
						St_ID=String.valueOf(st_ID);
						dist=rs.getInt("DistanceFromSource");
					}
					
				}
				tf3.setText(St_ID);
				con.close();
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
		
			
			
			
		//Select seat button in RESERVATION	
			
			
			
		if(ae.getSource()==select_seat)
		{
			
			date=tf4.getText();
			
			if(date.equals(""))
			{
				msg2.setText("Enter date of journey");
				d2.setVisible(true);
			}
			if((tf3.getText()).equals(""))
			{
				msg2.setText("Fill the station ID");
				d2.setVisible(true);
			}		
			else
			{
				
				for(i=0;i<=18;i++)
				{
					flag[i]=0;
				}
				
				seat_arr1.setVisible(true);
				seat_arr2.setVisible(true);
				seat_arr3.setVisible(true);
				sel_seat.setVisible(true);
				tf6.setVisible(true);
				confirm2.setVisible(true);
			
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				stmt=con.createStatement();
				sql="select * from BookingDetails";
				rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					String date_=rs.getString("Date");
					int busId=rs.getInt("BusID");
					int tourId=rs.getInt("TourID");
					int seatNumber=rs.getInt("SeatNumber");
					if(date_.equals(date)&&busId==bus_ID&&tourId==tour_ID)
					{
						
						for(i=1;i<=18;i++)
						{
							if(i==1)
							{
								Icon im1=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r1.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im1);
									flag[i]=1;
								}
									
							}
								
							if(i==2)
							{
								Icon im2=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r2.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im2);
									flag[i]=1;
								}
									
							}
								
							if(i==3)
							{
								Icon im3=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r3.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im3);
									flag[i]=1;
								}
									
							}
								
							if(i==4)
							{
								Icon im4=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r4.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im4);
									flag[i]=1;
								}
									
							}
								
							if(i==5)
							{
								Icon im5=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r5.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im5);
									flag[i]=1;
								}
									
							}
								
							if(i==6)
							{
								Icon im6=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r6.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im6);
									flag[i]=1;
								}
									
							}
								
							if(i==7)
							{
								Icon im7=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r7.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im7);
									flag[i]=1;
								}
									
							}
								
							if(i==8)
							{
								Icon im8=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r8.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im8);
									flag[i]=1;
								}
									
							}
								
							if(i==9)
							{
								Icon im9=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r9.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im9);
									flag[i]=1;
								}
									
							}
								
							if(i==10)
							{
								Icon im10=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r10.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im10);
									flag[i]=1;
								}
									
							}
								
							if(i==11)
							{
								Icon im11=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r11.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im11);
									flag[i]=1;
								}
									
							}
								
							if(i==12)
							{
								Icon im12=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r12.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im12);
									flag[i]=1;
								}
									
							}
								
							if(i==13)
							{
								Icon im13=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r13.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im13);
									flag[i]=1;
								}
									
							}
								
							if(i==14)
							{
								Icon im14=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r14.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im14);
									flag[i]=1;
								}
									
							}
								
							if(i==15)
							{
								Icon im15=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r15.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im15);
									flag[i]=1;
								}
									
							}
								
							if(i==16)
							{
								Icon im16=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r16.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im16);
									flag[i]=1;
								}
									
							}
								
							if(i==17)
							{
								Icon im17=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r17.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im17);
									flag[i]=1;
								}
									
							}
								
							if(i==18)
							{
								Icon im18=new ImageIcon("C:\\Program Files (x86)\\Java\\jdk1.7.0_71\\bin\\JavaProject\\Images\\r18.jpg");
								if(i==seatNumber)
								{
									s[i].setIcon(im18);
									flag[i]=1;
								}
									
							}
						}
						
						
					}
				}
				con.close();
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
		}
		
		
		
		//Seats in RESERVATION
		
		
		
		
		for(i=1;i<=18;i++)
		{
			if(ae.getSource()==s[i])
			{
				if(flag[i]==1)
				{
					msg2.setText("This seat is already booked");
					d2.setVisible(true);
				}
				else
				{
					seatNo=i;
					seat=String.valueOf(i);
					tf6.setText(seat);
				}
				
			}
		}
		
		
		
		
		//Confirm button in RESERVATION
		
		
		
		
		if(ae.getSource()==confirm2)
		{
			date=tf4.getText();
			if(date.equals(""))
			{
				msg2.setText("Enter date of journey");
				d2.setVisible(true);
			}
			else
			{
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				stmt=con.createStatement();
				sql="select * from BookingDetails";
				rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					String date_=rs.getString("Date");
					int busId=rs.getInt("BusID");
					int tourId=rs.getInt("TourID");
					int seatNumber=rs.getInt("SeatNumber");
					if(date_.equals(date)&&busId==bus_ID&&tourId==tour_ID&&seatNumber==seatNo)
					{
						
					}
				}
				con.close();
			}
			catch(ClassNotFoundException ce)
			{
				System.out.println("Unable to load driver");
			}
			catch(SQLException ex)
			{
				System.out.println("Some database error"+ex);
			}
			
			if((tf6.getText()).equals(""))
			{
				msg2.setText("Please select a seat");
				d2.setVisible(true);
			}
			else
			{
				reserv.setVisible(false);
				fare.setVisible(true);
				tf7.setText(source);
				tf8.setText(dest);
				tf9.setText(String.valueOf(dist));
				Tfare=fare_km*dist;
				tf10.setText(String.valueOf(Tfare));
			}
			}
			
		}
		
		
		
		//Confirm button in FARE CALCULATOR
		
		
		
		if(ae.getSource()==confirm3)
		{
			fare.setVisible(false);
			ticket.setVisible(true);
			tf11.setText(date);
			tf12.setText(bus_id);
			tf13.setText(source);
			tf14.setText(dest);
			tf15.setText(seat);
		}
		
		
		
		
		// Submit and Reset button in GENERATE TICKET
		
		
		
		
		if(ae.getSource()==submit)
		{
			passName=tf16.getText();
			phn=tf17.getText();
			if(passName.equals("")||phn.equals(""))
			{
				msg2.setText("  Enter your details carefully");
			}
			else
			{
				msg2.setText("Your details have been saved");
			}
				
			d2.setVisible(true);
		}
	
		if(ae.getSource()==reset)
		{
			tf16.setText("");
			tf17.setText("");
		}
		
		
		
		
		//Generate button in GENERATE TICKET
		
		
		
		
		if(ae.getSource()==generate)
		{
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				stmt=con.createStatement();
				sql= "INSERT INTO BookingDetails " + "VALUES ('"+date+"','"+bus_ID+"','"+tour_ID+"','"+source+"','"+dest+"','"+seatNo+"','"+passName+"','"+phn+"','"+Tfare+"')";
				stmt.executeUpdate(sql);
				con.close();
			}
			catch(ClassNotFoundException ce)
			{
				System.out.println("Unable to load driver");
			}
			catch(SQLException ex)
			{
				System.out.println("Some database error"+ex);
			}
			
			
			ticket_bID.setText(bus_id);
			ticket_date.setText(date);
			ticket_bus.setText(name);
			ticket_source.setText(source);
			ticket_dest.setText(dest);
			ticket_time.setText(time);
			ticket_fare.setText(String.valueOf(Tfare));
			ticket_seat.setText(seat);
			ticket_name.setText(passName);
			ticket_phn.setText(phn);
			
			
			ticket.setVisible(false);
			gen_ticket.setVisible(true);		
		}
			
	
		
		
		
		
		//CANCEL button in gen_ticket
		
		
		
		if(ae.getSource()==Tcancel)
		{
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:BusManagement");
				stmt=con.createStatement();
				sql="DELETE FROM BookingDetails WHERE Date='"+date+"' AND BusID="+bus_ID+" AND TourID="+tour_ID+" AND SeatNumber="+seatNo+"";
				//System.out.println(sql);
				stmt.executeUpdate(sql);
				con.close();
			}
			catch(ClassNotFoundException ce)
			{
				System.out.println("Unable to load driver");
			}
			catch(SQLException ex)
			{
				System.out.println("Some database error"+ex);
			}
			
			gen_ticket.setVisible(false);
			BusTicket tikcet=new BusTicket();
		}
		
		
		
		
		if(ae.getSource()==Tprint)
		{
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);
			boolean ok = job.printDialog();
			if (ok) 
			{
				try 
				{
					job.print();
				} 
				catch (PrinterException ex) {}
			}
		}
		
		
		
		
		
		
		
		//Ok buttons in Dialog boxes
		
		
		
	
		if(ae.getSource()==ok_d1)
		{
			d1.setVisible(false);
		
		}	
		if(ae.getSource()==ok_d2)
		{
			d2.setVisible(false);
			generate.setVisible(true);
		}		
		if(ae.getSource()==ok_d3)
		{
			d3.setVisible(false);
		
		}	
		
		
		
		
		//Back Buttons
		
		
		
		
		if(ae.getSource()==back1)  				//DETAILS
		{
			details.setVisible(false);
			main.setVisible(true);
		}
		if(ae.getSource()==back2)				//RESERVATION
		{
			cb.removeAllItems();
			reserv.setVisible(false);
			details.setVisible(true);
		}
		if(ae.getSource()==back3)				//FARE CALCULATOR
		{
			fare.setVisible(false);
			reserv.setVisible(true);
			tf4.setText("");
			tf6.setText("");
		}
		if(ae.getSource()==back4)				//TICKET
		{
			ticket.setVisible(false);
			fare.setVisible(true);
		}
		if(ae.getSource()==back5)				//GENERATE TICKET
		{
			gen_ticket.setVisible(false);
			ticket.setVisible(true);
		}
		if(ae.getSource()==back6)				//CONTACT US
		{
			contactUs.setVisible(false);
			main.setVisible(true);
		}
		
		
		if(ae.getSource()==home)				//HOME BUTTON
		{
			gen_ticket.setVisible(false);
			BusTicket newTicket=new BusTicket();
		}
	}
	
	
	
	//Item Event
	
	
	public void itemStateChanged(ItemEvent e) 
	{
		sel_st=(String)cb.getSelectedItem(); 
    }
	
	
	
	
	//MAIN method
	
	
	
	public static void main(String args[])
	{
		
		BusTicket obj=new BusTicket();
		
	}

}