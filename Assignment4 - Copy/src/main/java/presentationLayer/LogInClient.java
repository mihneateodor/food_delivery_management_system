package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInClient extends JFrame {
    private JPanel content=new JPanel();
    private JLabel labelCredentials= new JLabel("Introduceti datele de autentificare pentru client:");
    private JLabel labelUser=new JLabel("Nume de autentificare");
    private JLabel labelPassword=new JLabel("Parola");
    private JLabel labelRegister=new JLabel("Nu detii un cont?");
    private JTextField textFieldUser=new JTextField();
    private JPasswordField passFieldPassword=new JPasswordField();
    private JButton connectBtn=new JButton("Conectare");
    private JButton registerBtn=new JButton("Inregistrare");

    public LogInClient(){
        this.setBounds(100, 100, 416, 277);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        content.setLayout(null);
        this.setTitle("Autentificare client");

        labelCredentials.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelCredentials.setBounds(28, 44, 315, 25);
        content.add(labelCredentials);
        labelUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelUser.setBounds(28, 80, 153, 25);
        content.add(labelUser);
        labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelPassword.setBounds(28, 114, 127, 20);
        content.add(labelPassword);
        labelRegister.setBounds(10, 181, 103, 25);
        content.add(labelRegister);

        textFieldUser.setBounds(171, 83, 172, 20);
        content.add(textFieldUser);

        passFieldPassword.setBounds(171, 114, 172, 20);
        content.add(passFieldPassword);

        connectBtn.setBounds(171, 145, 111, 23);
        content.add(connectBtn);
        registerBtn.setBounds(10, 204, 118, 23);
        content.add(registerBtn);

        this.setContentPane(content);
    }

    public void connectActionListener(ActionListener a){
        connectBtn.addActionListener(a);
    }
    public void registerActionListener(ActionListener a){
        registerBtn.addActionListener(a);
    }
    public String getUsernameText(){
        return this.textFieldUser.getText();
    }
    public String getPasswordText(){
        String pass=String.valueOf(this.passFieldPassword.getPassword());
        return pass;
    }
    public void clearUsernameAndPasswordText(){
        this.textFieldUser.setText("");
        this.passFieldPassword.setText("");
    }
}
