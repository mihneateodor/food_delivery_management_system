package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View1 extends JFrame {
    private JPanel content=new JPanel();
    private JLabel choose = new JLabel("Conecteaza-te ca:");
    private JButton clientBtn= new JButton("CLIENT");
    private JButton adminBtn= new JButton("ADMINISTRATOR");
    private JButton employeeBtn= new JButton("ANGAJAT");

    public View1(){
        this.setBounds(100, 100, 507, 391);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.setLayout(null);
        this.setTitle("Prima fereastra");

        choose.setFont(new Font("Tahoma", Font.BOLD, 14));
        choose.setBounds(171, 64, 174, 30);
        content.add(choose);

        clientBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        clientBtn.setBounds(138, 105, 207, 49);
        content.add(clientBtn);
        adminBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        adminBtn.setBounds(138, 178, 207, 49);
        content.add(adminBtn);
        employeeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        employeeBtn.setBounds(138, 247, 207, 49);
        content.add(employeeBtn);

        this.setContentPane(content);
    }

    public void clientActionListener(ActionListener a){
        clientBtn.addActionListener(a);
    }
    public void adminActionListener(ActionListener a){
        adminBtn.addActionListener(a);
    }
    public void employeeActionListener(ActionListener a){
        employeeBtn.addActionListener(a);
    }
}
