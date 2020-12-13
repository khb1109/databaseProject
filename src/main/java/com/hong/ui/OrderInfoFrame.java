package com.hong.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.hong.db.JdbcTemplate;
import com.hong.ui.admin.EditFrame;

public class OrderInfoFrame extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel dt;
    private JdbcTemplate con;
    private String sql;
    private String[] str;
    private JButton bt;

    public OrderInfoFrame(int n, String s) {
        setBounds(100, 100, 1090, 513);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        dt = new DefaultTableModel(new Object[][] {
            {null, null, null, null, null, null, null, null, null},
        },
            new String[] {
                "No", "\uCC98\uB9AC\uC9C1\uC6D0", "\uAD6C\uB9E4\uC790", "\uD488\uBAA9", "\uAC2F\uC218",
                "\uC9C0\uBD88\uC561", "\uACB0\uC81C\uC77C", "\uBC30\uC1A1\uC608\uC815\uC77C",
                "\uC2E4\uC81C\uBC30\uC1A1\uC77C"
            }
        );
        table = new JTable(dt);

        JScrollPane scrollPane = new JScrollPane(table);

        contentPane.add(scrollPane, BorderLayout.NORTH);

        if (n == 0)
            add(scrollPane, BorderLayout.CENTER);
        else
            add(scrollPane, BorderLayout.NORTH);

        setTable(s);
        setTitle("�ֹ����� ��ȸ");

        if (n == 1) {
            setTitle("�ֹ����� �������");
            bt = new JButton("�����ϱ�");
            bt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String pk = table.getValueAt(table.getSelectedRow(), 0).toString();
                    System.out.println(pk);
                    EditFrame ae = new EditFrame(3, pk);
                    ae.addWindowListener(new WindowAdapter() {
                        public void windowClosed(WindowEvent e) {
                            setTable("");
                        }

                        public void windowClosing(WindowEvent e) {
                            setTable("");
                        }
                    });
                }
            });
            add(bt, BorderLayout.SOUTH);
        }
        setVisible(true);
    }

    public void setTable(String s) {
        con = new JdbcTemplate();

        int cnt = dt.getRowCount(); // ���̺� �ʱ�ȭ
        for (int i = 0; i < cnt; i++)
            dt.removeRow(0);

        str = new String[9];

        if (s.equals(""))
            sql = "select *from infoOrder;";
        else
            sql = "select *from infoOrder where C_name ='" + s + "'";
        try {
            con.rs = con.st.executeQuery(sql);
            while (con.rs.next()) {
                for (int i = 0; i < 9; i++)
                    str[i] = con.rs.getString(i + 1);
                dt.addRow(str);
            }

        } catch (SQLException e) {
        }
    }
}
