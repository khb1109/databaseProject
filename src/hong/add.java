package hong;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
public class add extends JFrame {

	public JComboBox comboBox;
	private JPanel contentPane;
	private JTextField txt2;
	private JTextField txt1;
	private JTextField txt3;
	String sql;
	MyConn con = new MyConn();
	private JTextField txt4;
	public add() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb4 = new JLabel("\uAC00\uACA9");
		lb4.setBounds(12, 171, 62, 51);
		contentPane.add(lb4); lb4.setVisible(false);
		
		txt4 = new JTextField();
		txt4.setColumns(10); txt4.setVisible(false);
		txt4.setBounds(75, 186, 116, 21);
		contentPane.add(txt4);
		
		JLabel lb2 = new JLabel("\uC8FC\uC18C");
		lb2.setBounds(12, 93, 62, 51);
		contentPane.add(lb2);
		
		JLabel lb1 = new JLabel("\uC774\uB984");
		lb1.setBounds(12, 52, 62, 51);
		contentPane.add(lb1);
		
		JLabel lb3 = new JLabel("");
		lb3.setBounds(12, 135, 62, 51);
		contentPane.add(lb3);
		setVisible(false);
		
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"회원추가","상품추가","직원추가"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(51, 10, 140, 32);
		comboBox.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) // 콤보박스 설정
				{
					int i = comboBox.getSelectedIndex();
					if(i==0||i==2)
					{
						txt1.setVisible(true);
						lb1.setVisible(true);
						lb3.setVisible(false);
						txt3.setVisible(false);
						lb1.setText("이름");
						lb2.setText("주소");
					}
					else{
						lb3.setVisible(true);
						lb1.setVisible(true);
						txt1.setVisible(true);
						lb2.setText("재고 수");
						lb3.setText("가격");
						txt3.setVisible(true);
					}
				}});
		contentPane.add(comboBox);
		
		txt2 = new JTextField();
		txt2.setBounds(75, 108, 116, 21);
		contentPane.add(txt2);
		txt2.setColumns(10);
		
		txt1 = new JTextField();
		txt1.setBounds(75, 67, 116, 21);
		contentPane.add(txt1);
		txt1.setColumns(10);
		
		JButton btn = new JButton("\uCD94\uAC00\uD558\uAE30");
		btn.setBounds(255, 67, 129, 102);
		contentPane.add(btn);
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int i=comboBox.getSelectedIndex();
				String a; // 고객추가
				String b;
				String c;
				a=txt1.getText();
				b=txt2.getText();
				
				switch(i){
				case 0:
					if(!a.equals("") && !b.equals(""))
					{
						String temp;
						sql = "insert into customer(name,adress)values('" + a+"','"+b+"');";
					
						try {
							con.st.executeUpdate(sql);
							con.rs=con.st.executeQuery("select*from customer order by ssn desc");
							con.rs.next();
							temp = con.rs.getString(1);
							JOptionPane.showMessageDialog(null, "회원 번호는 "+temp+" 입니다. 기억하세요!");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
					}
					break;
				case 1: // 아이템추가
					c=txt3.getText();
					if(!a.equals("")&&!b.equals("")&&!c.equals(""))
					{
						sql = "insert into item(name,stock,price)values('"+a+"','"+b+"','"+c+"');";
						try {
							con.st.executeUpdate(sql);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "추가완료!");
						dispose();
					}
					break;
					
				case 2: // 직원추가
					if(!a.equals("") && !b.equals(""))
					{
						sql = "insert into employee(name,adress)value('" + a+"','"+b+"');";

						try {
							con.st.executeUpdate(sql);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "추가완료!");
						dispose();
					}
				break;
				}	
			}
		});
		
		
		txt3 = new JTextField();
		txt3.setBounds(75, 148, 116, 21);
		contentPane.add(txt3);
		txt3.setColumns(10);
		
	
		txt3.setVisible(false);
		
		setVisible(true);
	}
	

}
