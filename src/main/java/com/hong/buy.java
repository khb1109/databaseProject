package com.hong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class buy extends JFrame {

    MyConn con;
    private JPanel contentPane;
    private JTextField tf_1;
    private JTextField tf_2;
    private JTextField tf_4;
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel lb_price;
    private JTextField tf_3;
    private JLabel lb_stock;

    public buy(int n) {

        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tf_1 = new JTextField();
        tf_1.setEditable(false);
        tf_1.setText("\uC5EC\uAE30\uC5D0 \uC544\uC774\uD15C\uD3EC\uB9B0\uD0A4");
        tf_1.setBounds(57, 53, 116, 29);
        contentPane.add(tf_1);
        tf_1.setColumns(10);

        tf_2 = new JTextField();
        tf_2.setEditable(false);
        tf_2.setText("\uC544\uC774\uD15C \uC774\uB984");
        tf_2.setBounds(57, 92, 116, 29);
        contentPane.add(tf_2);
        tf_2.setColumns(10);

        lb_price = new JLabel("\uAC00\uACA9 \uC228\uAE30\uAE30");
        lb_price.setEnabled(false);
        lb_price.setBounds(39, 213, 99, 56);
        contentPane.add(lb_price);

        JButton bt1 = new JButton("\uAD6C\uB9E4\uD558\uAE30");
        bt1.addActionListener(new BuyActionListener());
        bt1.setBounds(263, 181, 116, 52);
        contentPane.add(bt1);

        tf_4 = new JTextField();
        tf_4.setText("\uAC00\uACA9");
        tf_4.setEditable(false);
        tf_4.setColumns(10);
        tf_4.setBounds(57, 170, 116, 29);
        contentPane.add(tf_4);

        tf1 = new JTextField();
        tf1.setColumns(10);
        tf1.setBounds(263, 53, 116, 29);
        tf1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                int cnt = Integer.parseInt(tf1.getText());
                int price = Integer.parseInt(lb_price.getText());
                int sum = cnt * price;
                tf_4.setText(Integer.toString(sum));
            }
        });
        contentPane.add(tf1);

        tf2 = new JTextField();
        tf2.setHorizontalAlignment(SwingConstants.LEFT);
        tf2.setColumns(10);
        tf2.setBounds(263, 92, 116, 29);
        contentPane.add(tf2);

        tf3 = new JTextField();
        tf3.setColumns(10);
        tf3.setBounds(263, 131, 116, 29);
        contentPane.add(tf3);

        label1 = new JLabel("\uC2DC\uB9AC\uC5BC");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBounds(0, 60, 57, 15);
        contentPane.add(label1);

        label2 = new JLabel("\uC7AC\uACE0");
        label2.setBounds(12, 99, 57, 15);
        contentPane.add(label2);

        label3 = new JLabel("\uAE08\uC561");
        label3.setBounds(12, 177, 57, 15);
        contentPane.add(label3);

        label4 = new JLabel("\uAC2F\uC218");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setBounds(203, 60, 57, 15);
        contentPane.add(label4);

        label5 = new JLabel("\uACE0\uAC1D\uBC88\uD638");
        label5.setBounds(203, 99, 57, 15);
        contentPane.add(label5);

        label6 = new JLabel("\uC774\uB984");
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setBounds(203, 138, 57, 15);
        contentPane.add(label6);

        tf_3 = new JTextField();
        tf_3.setEditable(false);
        tf_3.setColumns(10);
        tf_3.setBounds(57, 131, 116, 29);
        contentPane.add(tf_3);

        lb_stock = new JLabel("\uC774\uB984");
        lb_stock.setHorizontalAlignment(SwingConstants.CENTER);
        lb_stock.setBounds(0, 138, 57, 15);
        contentPane.add(lb_stock);

        setting(n);
        setVisible(true);

    }

    public String getDate() {
        Calendar now = Calendar.getInstance();
        String year = Integer.toString(now.get(Calendar.YEAR));
        String month = Integer.toString(now.get(Calendar.MONTH) + 1);
        String day = Integer.toString(now.get(Calendar.DATE));
        if (month.equals("13"))
            month = "1";

        return year + "��" + month + "��" + day + "��";
    }

    public void setting(int n) {
        String[] str = new String[4];
        String sql = "select * from item where seriel=" + n + ";";
        System.out.println(sql);
        con = new MyConn();
        try {
            con.rs = con.st.executeQuery(sql);
            con.rs.next();
            str[0] = con.rs.getString(1);
            str[1] = con.rs.getString(2);
            str[2] = con.rs.getString(3);
            str[3] = con.rs.getString(4);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tf_1.setText(str[0]);
        tf_2.setText(str[1]);
        tf_3.setText(str[2]);
        tf_4.setText("");
        lb_price.setText(str[3]);
        lb_price.setVisible(false);
    }

    class BuyActionListener implements ActionListener {
        String sql;
        String temp;
        String str[] = new String[2];
        int c_num;
        String name;

        public void actionPerformed(ActionEvent e) {// �߰��Ҷ� ���ǵ��� �� �ɾ����.
            int stock = Integer.parseInt(tf_2.getText());
            int cnt = Integer.parseInt(tf1.getText()); // ���� �Է��Ѱ� ��Ƽ�� ������ ��ȯ

            if (!(cnt >= 1))
                return;
            if (!(stock >= cnt))
                return;

            temp = tf2.getText();
            if (temp.equals("") || tf3.getText().equals(""))
                return;

            sql = "select *from customer where ssn=" + temp + ";";
            System.out.println(sql);
            try {
                con.rs = con.st.executeQuery(sql);
                con.rs.next();
                str[0] = con.rs.getString(1);
                str[1] = con.rs.getString(2);

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (!str[1].equals(tf3.getText())) // ����ȣ�� ���� �̸��� �Է��� �̸��� ���ƾ���.
                return;

            System.out.println("���Ű���");

            String e_name;
            String c_name = tf3.getText();
            String i_name = tf_3.getText();
            String price = tf_4.getText();
            String day = getDate(); // ���ó�¥
            String CNT = tf1.getText();
            // ó���� ������ ������ ã��
            sql = "select * from employee order by throughput asc;";
            try {
                con.rs = con.st.executeQuery(sql);
                con.rs.next();
                e_name = con.rs.getString(2); // ó���� ����

                sql = "insert into infoOrder(e_Name,c_Name,i_name,cnt,price,paymentDay,"
                    + "expectationDay,delivery_date)value('" + e_name + "','" + c_name + "','" + i_name + "','" + cnt
                    + "','" + price + "','" + day + "','" + "����" + "','" + "����" + "');";

                con.st.execute(sql);
                sql = "update employee set throughput=throughput+1 where name = '" + e_name + "';";
                String seriel = tf_1.getText();
                con.st.execute(sql);
                sql = "update item set stock=stock-" + CNT + " where seriel=" + seriel + ";";
                con.st.execute(sql);
                System.out.println(sql);

                System.out.println("���ſϷ�");

            } catch (SQLException e1) {
            }
            JOptionPane.showMessageDialog(null, "���ſϷ�, �����մϴ�!");

            dispose();

        }
    }
}
