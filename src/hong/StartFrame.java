package hong;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class StartFrame extends JFrame {

	private JPanel contentPane;

	public StartFrame() {
		setTitle("\uBB3C\uD488\uAD00\uB9AC \uC2DC\uC2A4\uD15C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 244, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bt1 = new JButton("관리자 모드");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFrame af=new AdminFrame();
				setVisible(false);
				af.addWindowListener(new Mywin());
			}
		});
		bt1.setBounds(47, 36, 145, 54);
		contentPane.add(bt1);
		
		JButton bt2 = new JButton("구매자 모드");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerFrame cf =new customerFrame();
				setVisible(false);
				cf.addWindowListener(new Mywin());
			}
		});
		bt2.setBounds(47, 105, 145, 54);
		contentPane.add(bt2);
		setVisible(true);
	}
	class Mywin extends WindowAdapter{
		public void windowClosed(WindowEvent e)
		{
			setVisible(true);
		}
		public void windowClosing(WindowEvent e)
		{	setVisible(true);}
	}
}
