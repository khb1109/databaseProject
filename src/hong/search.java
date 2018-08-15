package hong;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class search extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	public String q;
	public int i;
	public JComboBox cb;
	
	public search() {
		
		setSize(100,100);
		setBounds(100, 100, 380, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf = new JTextField();
		tf.setBounds(74, 73, 154, 23);
		contentPane.add(tf);
		tf.setColumns(10);
		
		JButton btnNewButton = new JButton("\uD655\uC778");

		btnNewButton.setBounds(240, 73, 97, 23);
		contentPane.add(btnNewButton);
		
		cb = new JComboBox();
		cb.setModel(new DefaultComboBoxModel(new String[] {"\uACE0\uAC1D\uAC80\uC0C9", "\uC0C1\uD488\uAC80\uC0C9", "\uC9C1\uC6D0\uAC80\uC0C9"}));
		cb.setBounds(74, 40, 154, 23);
		contentPane.add(cb);
		
		JLabel lblNewLabel = new JLabel("\uC774\uB984 \uC785\uB825");
		lblNewLabel.setBounds(12, 77, 57, 15);
		contentPane.add(lblNewLabel);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str[] ={"customer","item","employee"};
				i = cb.getSelectedIndex();
				String name = tf.getText();
				
				
				if(name.equals("")) return; // 비어있을시 종료
				
				q=new String("select *from "+str[i]+" where name='"+name+"'");
				
				dispose();
				JOptionPane.showMessageDialog(null, "검색완료!");
			}
		});
		setVisible(true);
	}
}
