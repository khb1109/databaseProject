package hong;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.ScrollPaneConstants;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;


public class AdminFrame extends JFrame {
	
	private DefaultTableModel dt;
	private JPanel contentPane;
	private JTable table;
	MyConn con =new MyConn();
	private JComboBox cb;
	public AdminFrame() {
		setTitle("데이터베이스 통합관리 시스템");
		setSize(500,200);
		setBounds(100, 100, 494, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		getContentPane().add(addbutton());
	
		
		JButton edit=editbutton();
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = cb.getSelectedIndex();
				String b =table.getValueAt(table.getSelectedRow(), 0).toString(); // PK를 구하는데.  스트링으로 변환
				adminEdit ae = new adminEdit(a,b);
				ae.addWindowListener(new WindowAdapter()
				{
					public void windowClosed(WindowEvent e){
						tableSetting(a,"");}
					public void windowCloing(WindowEvent e){
						tableSetting(a,"");}
				});
			}
		});
		getContentPane().add(editbutton());
		getContentPane().add(searchbutton());
	
		cb = new JComboBox();
		cb.setModel(new DefaultComboBoxModel(new String[] {"고객정보","상품정보","직원정보"}));
		cb.setBounds(29, 10, 313, 21);
		cb.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
			{
					JComboBox jb= (JComboBox)e.getSource();
					tableSetting(jb.getSelectedIndex(),"");
			}}
				);
		getContentPane().add(cb);
		getContentPane().add(delbutton());

		JPanel panel = new JPanel();
		panel.setBounds(29, 41, 318, 338);
		contentPane.add(panel);
		
		
		dt = new DefaultTableModel(new Object[][] {
		},
		new String[] {"\uBC88\uD638", "\uC774\uB984", "\uC8FC\uC18C", "\uCC98\uB9AC\uB7C9"});
		table = new JTable(dt);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		table.setBounds(30, 22, 234, 229);
		JScrollPane js = new JScrollPane(table);
		
	
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(73);
		table.getColumnModel().getColumn(1).setPreferredWidth(73);
		table.getColumnModel().getColumn(2).setPreferredWidth(73);
		table.getColumnModel().getColumn(3).setPreferredWidth(73);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(js);
		table.setModel(dt);
		
		JButton button = new JButton("주문내역");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new orderinfo(1,""); // 어드민의 호출
			}
		});
		button.setBounds(369, 293, 97, 53);
		contentPane.add(button);
		tableSetting(0,"");

		setVisible(true);
	}
	public JButton addbutton()
	{

		JButton bt1 = new JButton("추가");
		bt1.setBounds(369, 41, 97, 53);
		contentPane.add(bt1);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add ad = new add();
				int n = cb.getSelectedIndex();
				ad.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e)
					{
						tableSetting(n,"");
					}
				});
			}
		});
		
		return bt1;
	}
	public JButton delbutton()
	{
		JButton bt1= new JButton("삭제");
		bt1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String []tab = {"customer","item","employee"};
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?","메세지",JOptionPane.OK_CANCEL_OPTION);
				if(result ==2) return;
				
				int cindex;
				cindex = cb.getSelectedIndex();
				int tableIndex = table.getSelectedRow();
				String key=(String)table.getValueAt(tableIndex, 0);	
				con = new MyConn();
				String sql;
				if(cindex==1)
					sql = "delete from "+tab[cindex]+" where seriel ="+key;
				else sql = "delete from "+tab[cindex]+" where ssn ="+key;
				System.out.println(sql);
				try {
					con.st.executeUpdate(sql);
				} catch (SQLException e1) {	}
				tableSetting(cindex,"");
				return;
			}
		});
		bt1.setBounds(369, 104, 97, 53);
		contentPane.add(bt1);

		return bt1;
	}
	public JButton editbutton()
	{
		JButton bt1= new JButton("편집");
		bt1.setBounds(369, 167, 97, 53);
		contentPane.add(bt1);
		
		return bt1;
	}
	public JButton searchbutton()
	{
		JButton bt1= new JButton("검색");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search s = new search();
				s.addWindowListener(new WindowAdapter()
						{
							public void windowClosed(WindowEvent e)
							{
								tableSetting(s.i,s.q);
							}
						});
				
			}
		});
		bt1.setBounds(369, 230, 97, 53);
		contentPane.add(bt1);
			return bt1;
	}	
	public void tableSetting(int n,String ser)
	{	
		
		String []head= new String[4];
		int t = dt.getRowCount();
		for(int i = 0;i<t ;i++)
			dt.removeRow(0);
		
		
		String []str = new String[4];
		
		switch(n){
		case 0: // 0번은 고객
			head[0] = "번호"; head[1] = "이름"; head[2] ="주소"; head[3] ="";
			dt.setColumnIdentifiers(head);
			try{
				if(ser.equals(""))
					con.rs = con.st.executeQuery("select *from customer");
				else
					con.rs = con.st.executeQuery(ser);
			while(con.rs.next())
			{
				str[0] =con.rs.getString(1);
				str[1] =con.rs.getString(2);
				str[2] =con.rs.getString(3);
				dt.addRow(str);
			}
			}
			catch(SQLException SQLex)
			{
				
			}
			break;
		case 1: // 상품정보
			head[0] = "번호"; head[1] = "재고"; head[2] ="이름"; head[3] ="가격";
			dt.setColumnIdentifiers(head);
			try{
				if(ser.equals(""))
					con.rs = con.st.executeQuery("select *from item");
				else
					con.rs = con.st.executeQuery(ser);
				while(con.rs.next())
				{
					str[0] =con.rs.getString(1);
					str[1] =con.rs.getString(2);
					str[2] =con.rs.getString(3);
					str[3] = con.rs.getString(4);
					dt.addRow(str);
				}
				}
				catch(SQLException SQLex){	}
			break;
	case 2:
		head[0] = "번호"; head[1] = "이름"; head[2] ="주소"; head[3] ="처리량";
		dt.setColumnIdentifiers(head);
		try{
			if(ser.equals(""))
				con.rs = con.st.executeQuery("select *from employee");
			else
				con.rs = con.st.executeQuery(ser);
			while(con.rs.next())
			{
				str[0] =con.rs.getString(1);
				str[1] =con.rs.getString(2);
				str[2] =con.rs.getString(3);
				str[3] = con.rs.getString(4);
				dt.addRow(str);
			}
			}
			catch(SQLException SQLex){}
		break;

	}
	}

}
