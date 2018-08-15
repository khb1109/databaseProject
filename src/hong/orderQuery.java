package hong;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class orderQuery extends JFrame {

	private JPanel contentPane;
	private JTextField tf;


	public orderQuery() {
		setBounds(100, 100, 304, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf = new JTextField();
		tf.setHorizontalAlignment(SwingConstants.CENTER);
		tf.setBounds(123, 37, 132, 38);
		contentPane.add(tf);
		tf.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(276, 106, 33, -2);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Click");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tf.getText();
				if(name.equals(""))
					return;
				new orderinfo(0,name);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(80, 84, 116, 33);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("\uC774\uB984 \uAC80\uC0C9");
		lblNewLabel.setBounds(41, 36, 70, 38);
		contentPane.add(lblNewLabel);
		setVisible(true);
	}

}
