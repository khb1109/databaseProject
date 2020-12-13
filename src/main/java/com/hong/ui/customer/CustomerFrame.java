package com.hong.ui.customer;

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
import com.hong.ui.OrderQuery;
import com.hong.ui.Search;
import com.hong.ui.common.AddFrame;

public class CustomerFrame extends JFrame {

    private JdbcTemplate con;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel dt;

    public CustomerFrame() {
        setBounds(100, 100, 461, 390);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(2, 0, 321, 350);
        contentPane.add(panel);

        dt = new DefaultTableModel(new Object[][] {{null, null, null, null},},
            new String[] {"seriel", "���", "��ǰ", "����"});
        table = new JTable(dt);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(scrollPane);
        JButton button = new JButton("\uAD6C\uB9E4");
        button.addActionListener(new buyButtonListener());
        button.setBounds(330, 109, 97, 43);
        contentPane.add(button);

        JButton button_2 = new JButton("\uAC80\uC0C9");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search s = new Search();
                s.cb.setSelectedIndex(1);
                s.cb.setEnabled(false);
                System.out.print(s.q);
                s.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        ShowTable(s.q);
                    }
                });
            }
        });
        button_2.setBounds(330, 184, 97, 43);
        contentPane.add(button_2);

        JButton button_1 = new JButton("\uAC00\uC785\uD558\uAE30");
        button_1.addActionListener(new MyActionListener());
        button_1.setBounds(330, 33, 97, 43);
        contentPane.add(button_1);

        JButton bt3 = new JButton("\uC8FC\uBB38\uB0B4\uC5ED");
        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new OrderQuery();
            }
        });
        bt3.setBounds(330, 262, 97, 43);
        contentPane.add(bt3);

        ShowTable("");
        setVisible(true);
    }

    public void clearTable() {
        int cnt = dt.getRowCount();
        for (int i = 0; i < cnt; i++)
            dt.removeRow(0);
    }

    public void ShowTable(String sea) {
        clearTable();
        con = new JdbcTemplate();
        String[] str = new String[4];

        try {
            if (sea.equals(""))
                con.rs = con.st.executeQuery("select *from item where stock<>0;");
            else
                con.rs = con.st.executeQuery(sea);
            while (con.rs.next()) {
                str[0] = con.rs.getString(1);
                str[1] = con.rs.getString(2);
                str[2] = con.rs.getString(3);
                str[3] = con.rs.getString(4);
                dt.addRow(str);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public class buyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow();
            String st = (String)table.getValueAt(row, 0); // �̰� �ø����� ����Ű
            BuyFrame b = new BuyFrame(Integer.parseInt(st));
            b.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    ShowTable("");
                }
            });
        }
    }

    public class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AddFrame ad = new AddFrame();
            ad.comboBox.setVisible(false);
        }
    }
}
