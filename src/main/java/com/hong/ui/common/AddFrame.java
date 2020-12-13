package com.hong.ui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.hong.db.JdbcTemplate;

public class AddFrame extends JFrame {

    public JComboBox comboBox;
    String sql;
    JdbcTemplate con = new JdbcTemplate();
    private JPanel contentPane;
    private JTextField txt2;
    private JTextField txt1;
    private JTextField txt3;
    private JTextField txt4;

    public AddFrame() {
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lb4 = new JLabel("\uAC00\uACA9");
        lb4.setBounds(12, 171, 62, 51);
        contentPane.add(lb4);
        lb4.setVisible(false);

        txt4 = new JTextField();
        txt4.setColumns(10);
        txt4.setVisible(false);
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
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"회원추가", "상품추가", "직원추가"}));
        comboBox.setToolTipText("");
        comboBox.setBounds(51, 10, 140, 32);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) // �޺��ڽ� ����
            {
                int i = comboBox.getSelectedIndex();
                if (i == 0 || i == 2) {
                    txt1.setVisible(true);
                    lb1.setVisible(true);
                    lb3.setVisible(false);
                    txt3.setVisible(false);
                    lb1.setText("�̸�");
                    lb2.setText("�ּ�");
                } else {
                    lb3.setVisible(true);
                    lb1.setVisible(true);
                    txt1.setVisible(true);
                    lb2.setText("��� ��");
                    lb3.setText("����");
                    txt3.setVisible(true);
                }
            }
        });
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
            public void actionPerformed(ActionEvent e) {
                int i = comboBox.getSelectedIndex();
                String a; // ���߰�
                String b;
                String c;
                a = txt1.getText();
                b = txt2.getText();

                switch (i) {
                    case 0:
                        if (!a.equals("") && !b.equals("")) {
                            String temp;
                            sql = "insert into customer(name,adress)values('" + a + "','" + b + "');";

                            try {
                                con.st.executeUpdate(sql);
                                con.rs = con.st.executeQuery("select*from customer order by ssn desc");
                                con.rs.next();
                                temp = con.rs.getString(1);
                                JOptionPane.showMessageDialog(null, "ȸ�� ��ȣ�� " + temp + " �Դϴ�. ����ϼ���!");
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            dispose();
                        }
                        break;
                    case 1: // �������߰�
                        c = txt3.getText();
                        if (!a.equals("") && !b.equals("") && !c.equals("")) {
                            sql = "insert into item(name,stock,price)values('" + a + "','" + b + "','" + c + "');";
                            try {
                                con.st.executeUpdate(sql);
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "�߰��Ϸ�!");
                            dispose();
                        }
                        break;

                    case 2: // �����߰�
                        if (!a.equals("") && !b.equals("")) {
                            sql = "insert into employee(name,adress)value('" + a + "','" + b + "');";

                            try {
                                con.st.executeUpdate(sql);
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "�߰��Ϸ�!");
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
