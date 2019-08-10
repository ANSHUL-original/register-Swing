package register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class OtpVerify {
	JFrame jf3;
	JLabel jl5;
	JTextField jtf4;
	JButton jb3;

	public OtpVerify() {
		jf3=new JFrame();
		
		jl5=new JLabel("enter the OTP below");
		jl5.setBounds(80, 30, 250, 30);
		jf3.add(jl5);
		
		jtf4=new JTextField();
		jtf4.setBounds(50, 80, 185, 30);
		jf3.add(jtf4);
		
		jb3=new JButton("Verify");
		jb3.setBounds(90, 130, 100, 30);
		jf3.add(jb3);
		
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==jb3)
				{
					String onetimepassword=jtf4.getText();
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DB5", "root", "");
						Statement st=con.createStatement();
						ResultSet rs;
						
						//String query="SELECT * FROM table1 where email='"+jtf3.getText()+"'";
						String query="SELECT * FROM table1";
						rs=st.executeQuery(query);
						//JOptionPane.showMessageDialog(null, rs.getString(4));
						
						while(rs.next())
						{
							if(onetimepassword.equals(rs.getString(4)))
							{
								JOptionPane.showMessageDialog(null, "OTP verified");
								JOptionPane.showMessageDialog(null, "your password is: "+rs.getString(3));
								System.exit(0);
								new Login2();
							}
						}
						JOptionPane.showMessageDialog(null, "Incorrect OTP!!!");
						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
					
				}
			}
		});
		
		jf3.setLayout(null);
		jf3.setSize(300, 300);
		jf3.setVisible(true);
	}

}
