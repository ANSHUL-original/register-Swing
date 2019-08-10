package register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register2 implements ActionListener {

	JFrame jf;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1,jtf2,jtf3,jtf4;
	JButton jb;
	public Register2(){
		// TODO Auto-generated constructor stub
		jf=new JFrame("Register_page");
		
		jl1=new JLabel("username :");
		jl1.setBounds(30, 50, 100, 30);
		jf.add(jl1);
		jl2=new JLabel("email :");
		jl2.setBounds(30, 90, 100, 30);
		jf.add(jl2);
		jl3=new JLabel("password :");
		jl3.setBounds(30, 130, 100, 30);
		jf.add(jl3);
		jl4=new JLabel("confirm password :");
		jl4.setBounds(30, 170, 150, 30);
		jf.add(jl4);
		
		jtf1=new JTextField();
		jtf1.setBounds(160, 50, 290, 30);
		jf.add(jtf1);
		jtf2=new JTextField();
		jtf2.setBounds(160, 90, 290, 30);
		jf.add(jtf2);
		jtf3=new JPasswordField();
		jtf3.setBounds(160, 130, 290, 30);
		jf.add(jtf3);
		jtf4=new JPasswordField();
		jtf4.setBounds(160, 170, 290, 30);
		jf.add(jtf4);
		
		jb=new JButton("Submit");
		jb.setBounds(180, 230, 100, 30);
		jf.add(jb);
		
		jb.addActionListener(this);
		
		jf.setLayout(null);
		jf.setSize(500, 500);
		jf.setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Register2();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			String username=jtf1.getText();
			String email=jtf2.getText();
			String password=jtf3.getText();
			String confirm_password=jtf4.getText();
			if(password.equals(confirm_password))
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DB5", "root", "");
					Statement st=con.createStatement();
					//ResultSet rs;
					
					String query="INSERT INTO table1 values('"+username+"','"+email+"','"+password+"')";
					
					int i=st.executeUpdate(query);
					
					if(i>0)
					{
						JOptionPane.showMessageDialog(null, "Registered");
						new Login2();
						System.exit(0);
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Somethig not Right");
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Server is not onn");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "ERROR!\npassword dont match");
			}
		}
	}
}
