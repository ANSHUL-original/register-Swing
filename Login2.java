package register;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login2 implements ActionListener,MouseListener{

	JFrame jf;
	
	JFrame jf2;
	
	//JFrame jf3;
	
	JLabel jl1,jl2,jl3;
	
	JLabel jl4;
	
	//JLabel jl5;
	
	JTextField jtf1,jtf2;
	
	JTextField jtf3;
	
	//JTextField jtf4;
	
	JButton jb;
	
	JButton jb2;
	
	//JButton jb3;
	
	public Login2() {
		jf=new JFrame("Login_page");
		jl1=new JLabel("email :");
		jl1.setBounds(50, 50, 100, 30);
		jf.add(jl1);
		jl2=new JLabel("password :");
		jl2.setBounds(50, 90, 100, 30);
		jf.add(jl2);
		jl3=new JLabel("Forget Password");
		jl3.setBounds(310, 140, 230, 30);
		jf.add(jl3);
		
		jtf1=new JTextField();
		jtf1.setBounds(150, 50, 280, 30);
		jf.add(jtf1);
		jtf2=new JPasswordField();
		jtf2.setBounds(150, 90, 280, 30);
		jf.add(jtf2);
		
		jb=new JButton("Login");
		jb.setBounds(160, 140, 100, 30);
		jf.add(jb);
		
		jb.addActionListener(this);
		jl3.addMouseListener(this);
		jl3.setForeground(Color.blue);
		
		jf.setLayout(null);
		jf.setSize(500, 500);
		jf.setVisible(true);		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login2();
	}

//for auth of login
	//for login
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			String email=jtf1.getText();
			String password=jtf2.getText();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DB5", "root", "");
				Statement st=con.createStatement();
				ResultSet rs;
				
				String query="SELECT * FROM table1";
				rs=st.executeQuery(query);
				while(rs.next())
				{
					if(email.equals(rs.getString(2)))
					{
						if(password.equals(rs.getString(3)))
						{
							JOptionPane.showMessageDialog(null, "Welcome "+rs.getString(1));
							System.exit(0);
							new Dashboard();
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect Password!");
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Incorrect Email!");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
//for forget password
	//forget password
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		jl3.setForeground(Color.red.darker());
		//JOptionPane.showMessageDialog(null, "under_progress");
		//Random r=new Random();
		jf2=new JFrame();
		jl4=new JLabel("Enter Reg. email");
		jl4.setBounds(55, 50, 200, 30);
		jf2.add(jl4);
		
		jtf3=new JTextField();
		jtf3.setBounds(170, 50, 250, 30);
		jf2.add(jtf3);
		
		jb2=new JButton("send OTP");
		jb2.setBounds(160, 100, 150, 30);
		jf2.add(jb2);
		
		jb2.addActionListener(new ActionListener() {
			
			//OTP SEND
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==jb2)
				{
					String email=jtf3.getText();
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DB5", "root", "");
						Statement st=con.createStatement();
						ResultSet rs;
						
						String qurery="SELECT * FROM table1";
						rs=st.executeQuery(qurery);
						while(rs.next())
						{
							if(email.equals(rs.getString(2)))
							{
								//Random r2=new Random();//for integer random number
								//random string generator
								final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

									StringBuilder builder = new StringBuilder();
									int count=5;//no of letters in the OTP
									while (count-- != 0) {
										int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
										builder.append(ALPHA_NUMERIC_STRING.charAt(character));
										}
										 
													
								String OTP=builder.toString();
								new OtpMail(email,OTP);//constructor of OtpMail class in a differnet file
								
								
								try {
									Class.forName("com.mysql.jdbc.Driver");
									Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/DB5", "root", "");
									Statement st2=con2.createStatement();
									
									String query="update table1 set otp='"+OTP+"' where email='"+email+"'";
									int i2=st2.executeUpdate(query);
									if(i2>0)	
									{
										JOptionPane.showMessageDialog(null, "Check email for OTP");
										
										new OtpVerify();

										
									}									
									
								} catch (Exception e2) {
									// TODO: handle exception
									e2.printStackTrace();
								}
								

//								JOptionPane.showMessageDialog(null, "OTP send on Reg. email");
							}
						
						}
						JOptionPane.showMessageDialog(null, "Email not registered");
						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
					
				}
			}
		});
		
		jf2.setLayout(null);
		jf2.setSize(500, 500);
		jf2.setVisible(true);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//jl3.setForeground(Color.green);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		jl3.setForeground(Color.blue.darker());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		jl3.setForeground(Color.red);		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		jl3.setForeground(Color.blue.darker());
	}

}
