package com.hong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class adminEdit extends JFrame {
    private MyConn con;
    private JPanel contentPane;
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tfa;
    private JTextField tfb;
    private JLabel lb;
    private JLabel lb1;
    private JLabel lb2;
    private JLabel lb3;
    private JLabel lb4;

    public adminEdit(int ComboIndex, String PK) {
        setBounds(100, 100, 420, 233);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tf1 = new JTextField(PK);
        tf1.setEditable(false);
        tf1.setBounds(81, 46, 116, 21);
        contentPane.add(tf1);
        tf1.setColumns(10);

        tf2 = new JTextField();
        tf2.setEditable(false);
        tf2.setBounds(81, 87, 116, 21);
        contentPane.add(tf2);
        tf2.setColumns(10);

        tfa = new JTextField();
        tfa.setBounds(276, 46, 116, 21);
        contentPane.add(tfa);
        tfa.setColumns(10);

        tfb = new JTextField();
        tfb.setBounds(276, 87, 116, 21);
        contentPane.add(tfb);
        tfb.setColumns(10);
        tfb.setVisible(true);

        lb = new JLabel("customer");
        lb.setBounds(138, 10, 130, 23);
        contentPane.add(lb);

        JButton btnNewButton = new JButton("Click");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit(ComboIndex, PK);
            }
        });
        btnNewButton.setBounds(138, 143, 116, 41);
        contentPane.add(btnNewButton);

        lb1 = new JLabel("New label");
        lb1.setBounds(12, 49, 57, 15);
        contentPane.add(lb1);

        lb2 = new JLabel("New label");
        lb2.setBounds(12, 90, 57, 15);
        contentPane.add(lb2);

        lb3 = new JLabel("New label");
        lb3.setBounds(209, 49, 57, 15);
        contentPane.add(lb3);

        lb4 = new JLabel("New label");
        lb4.setBounds(209, 90, 57, 15);
        contentPane.add(lb4);

        formSetting(ComboIndex, PK);

        setVisible(true);
    }

    public void formSetting(int CB, String PK) {
        if (CB == 0) {
            lb1.setText("����ȣ");
            lb2.setText("�̸�");
            lb3.setText("�ּ�");
        }
        if (CB == 1) {
            lb1.setText("�ø���");
            lb2.setText("�̸�");
            lb3.setText("���");
            lb4.setText("����");
        }
        if (CB == 2) {
            lb1.setText("������ȣ");
            lb2.setText("�̸�");
            lb3.setText("�ּ�");
        }
        if (CB == 3) {
            lb1.setText("�ֹ���ȣ");
            lb2.setText("���̸�");
            lb3.setText("�߼ۿ���");
            lb4.setText("�߼���");
        }
        if (CB == 0 || CB == 2) {
            lb4.setVisible(false);
            tfb.setVisible(false);
        }

        String[] tab = {"Customer", "item", "employee", "infoOrder"};
        lb.setText(tab[CB] + " �����ϱ�");
        String[] st = new String[3];
        con = new MyConn();
        String sql;

        if (CB == 1)
            sql = "select seriel,name,stock,price from " + tab[CB] + " where seriel=" + PK;
        else if (CB == 3)
            sql = "select infoNumber,c_Name,expectationDay,delivery_date from " + tab[CB] + " where infoNumber=" + PK;
        else
            sql = "select *from " + tab[CB] + " where ssn=" + PK;

        System.out.println(sql);

        try {
            con.rs = con.st.executeQuery(sql);
            con.rs.next();
            st[0] = con.rs.getString(2);
            st[1] = con.rs.getString(3);
            if (CB == 1 || CB == 3) // Ŀ���͸� ���̺��� �ƴҽ�
                st[2] = con.rs.getString(4);
        } catch (SQLException e) {
        }

        tf2.setText(st[0]);

        tfa.setText(st[1]);
        if (CB == 1 || CB == 3) {
            tfb.setText(st[2]);
            tfb.setVisible(true);
        }

    }

    public void edit(int ComboIndex, String PK) {
        if (tfa.getText().equals(""))
            return; // ���� ù��° ĭ�� ��� ����
        if (ComboIndex == 1)
            if (tfb.getText().equals(""))
                return; // �������϶� �ι�° ĭ�� ��� ����

        con = new MyConn();
        String sql;
        String data1 = tfa.getText();
        String data2 = tfb.getText();

        switch (ComboIndex) {
            case 0: // ������
                try {
                    sql = "UPDATE customer SET adress = '" + data1 + "' where ssn = " + PK;
                    System.out.println(sql);
                    con.st.executeUpdate(sql);
                } catch (SQLException e) {
                }
                break;
            case 1: // ��ǰ����
                try {
                    sql = "UPDATE item SET stock = " + data1 + " where seriel = " + PK;
                    con.st.executeUpdate(sql);
                    sql = "UPDATE item SET price = " + data2 + " where seriel = " + PK;
                    con.st.executeUpdate(sql);
                } catch (SQLException e) {
                }
                break;
            case 2: // ��������
                try {
                    sql = "UPDATE employee SET adress = '" + data1 + "' where ssn = " + PK;
                    con.st.executeUpdate(sql);
                } catch (SQLException e) {
                }
                break;
            case 3:
                try {

                    sql = "UPDATE infoOrder SET expectationDay = '" + data1 + "' where infoNumber = " + PK;
                    System.out.println(sql);
                    con.st.executeUpdate(sql);
                    sql = "UPDATE infoOrder SET delivery_date = '" + data2 + "' where infoNumber = " + PK;
                    System.out.println(sql);
                    con.st.executeUpdate(sql);

                } catch (SQLException e) {
                }
                break;
        }
        JOptionPane.showMessageDialog(null, "�����Ϸ�!");
        dispose();
    }
}
