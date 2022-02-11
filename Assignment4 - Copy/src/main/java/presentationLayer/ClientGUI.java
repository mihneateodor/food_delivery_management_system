package presentationLayer;

import businessLayer.Client;
import businessLayer.IDeliveryServiceProcessing;
import businessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private JPanel content = new JPanel();
    private JLabel menuLabel=new JLabel("Meniu:");
    private JLabel dateLabel=new JLabel("Introduceti data si ora:");
    private JLabel yearLabel=new JLabel("An:");
    private JLabel monthLabel=new JLabel("Luna:");
    private JLabel dayLabel=new JLabel("Zi:");
    private JLabel hourLabel=new JLabel("Ora:");
    private JLabel searchLabel=new JLabel("Cauta in functie de:");
    private JLabel nameLabel=new JLabel("Nume:");
    private JLabel ratingLabel=new JLabel("\"Rating\":");
    private JLabel caloriesLabel=new JLabel("\"Calories\":");
    private JLabel proteinsLabel=new JLabel("\"Proteins\":");
    private JLabel fatsLabel=new JLabel("\"Fats\":");
    private JLabel sodiumLabel=new JLabel("\"Sodium\":");
    private JLabel priceLabel=new JLabel("Pret:");
    private JTextField nameTextField=new JTextField();
    private JTextField ratingTextField=new JTextField();
    private JTextField caloriesTextField=new JTextField();
    private JTextField proteinsTextField=new JTextField();
    private JTextField fatsTextField=new JTextField();
    private JTextField sodiumTextField=new JTextField();
    private JTextField priceTextField=new JTextField();
    private JTextField yearTextField=new JTextField();
    private JTextField monthTextField= new JTextField();
    private JTextField dayTextField=new JTextField();
    private JTextField hourTextField=new JTextField();
    private JComboBox<String> comboBoxMenu= new JComboBox<String>();
    private JButton addButton = new JButton("Adauga la comanda");
    private JButton removeButton = new JButton("Scoate din comanda");
    private JButton createOrderButton = new JButton("Lanseaza comanda");
    private JButton searchButton=new JButton("Cautare");
    private JEditorPane textBox = new JEditorPane();

    private IDeliveryServiceProcessing deliveryService;
    private Client client;

    public ClientGUI(IDeliveryServiceProcessing deliveryService, Client client){
        this.client=client;
        this.deliveryService=deliveryService;
        this.setBounds(100, 100, 796, 519);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        content.setLayout(null);
        this.setTitle("Client");

        menuLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuLabel.setBounds(48, 11, 85, 23);
        content.add(menuLabel);
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dateLabel.setBounds(10, 131, 141, 23);
        content.add(dateLabel);
        yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        yearLabel.setBounds(154, 128, 35, 23);
        content.add(yearLabel);
        monthLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        monthLabel.setBounds(258, 128, 35, 23);
        content.add(monthLabel);
        dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dayLabel.setBounds(375, 128, 35, 23);
        content.add(dayLabel);
        hourLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        hourLabel.setBounds(497, 128, 35, 23);
        content.add(hourLabel);
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        searchLabel.setBounds(10, 405, 123, 27);
        content.add(searchLabel);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nameLabel.setBounds(139, 405, 50, 27);
        content.add(nameLabel);
        ratingLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ratingLabel.setBounds(10, 431, 50, 27);
        content.add(ratingLabel);
        caloriesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        caloriesLabel.setBounds(143, 431, 63, 27);
        content.add(caloriesLabel);
        proteinsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        proteinsLabel.setBounds(290, 431, 76, 27);
        content.add(proteinsLabel);
        fatsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        fatsLabel.setBounds(456, 431, 50, 27);
        content.add(fatsLabel);
        sodiumLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        sodiumLabel.setBounds(603, 431, 57, 27);
        content.add(sodiumLabel);
        priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        priceLabel.setBounds(603, 405, 35, 27);
        content.add(priceLabel);

        yearTextField.setBounds(173, 131, 57, 20);
        content.add(yearTextField);
        monthTextField.setBounds(290, 131, 57, 20);
        content.add(monthTextField);
        dayTextField.setBounds(392, 131, 57, 20);
        content.add(dayTextField);
        hourTextField.setBounds(528, 131, 57, 20);
        content.add(hourTextField);
        nameTextField.setBounds(185, 409, 237, 20);
        content.add(nameTextField);
        ratingTextField.setBounds(65, 435, 68, 20);
        content.add(ratingTextField);
        caloriesTextField.setBounds(212, 435, 68, 20);
        content.add(caloriesTextField);
        proteinsTextField.setBounds(357, 435, 68, 20);
        content.add(proteinsTextField);
        fatsTextField.setBounds(497, 435, 68, 20);
        content.add(fatsTextField);
        sodiumTextField.setBounds(663, 435, 68, 20);
        content.add(sodiumTextField);
        priceTextField.setBounds(663, 405, 68, 20);
        content.add(priceTextField);

        comboBoxMenu.setBounds(10, 45, 744, 22);
        content.add(comboBoxMenu);

        addButton.setBounds(590, 83, 170, 37);
        content.add(addButton);
        removeButton.setBounds(10, 83, 179, 37);
        content.add(removeButton);
        createOrderButton.setBounds(301, 83, 179, 37);
        content.add(createOrderButton);
        searchButton.setBounds(476, 405, 89, 23);
        content.add(searchButton);

        content.add(textBox);

        final JScrollPane scrollPane=new JScrollPane(textBox);
        scrollPane.setBounds(10, 162, 744, 232);
        content.add(scrollPane);

        this.setContentPane(content);
    }

    public int getJComboBoxMenuIndex(){
        return this.comboBoxMenu.getSelectedIndex();
    }
    public void setTextBox(String text){
        textBox.setText(text);
    }
    public void addButtonActionListener(ActionListener a){
        addButton.addActionListener(a);
    }
    public void removeButtonActionListener(ActionListener a){
        removeButton.addActionListener(a);
    }
    public void createOrderButtonActionListener(ActionListener a){
        createOrderButton.addActionListener(a);
    }
    public void searchActionListener(ActionListener a){
        searchButton.addActionListener(a);
    }
    public Client getClient(){ return this.client;}
    public String getYearTextField(){ return this.yearTextField.getText();}
    public String getMonthTextField() {return this.monthTextField.getText();}
    public String getDayTextField() {return this.dayTextField.getText();}
    public String getHourTextField() {return this.hourTextField.getText();}
    public void resetTextFields() {
        this.yearTextField.setText("");
        this.monthTextField.setText("");
        this.dayTextField.setText("");
        this.hourTextField.setText("");
    }
    public String getNameTextField() { return this.nameTextField.getText();}
    public String getPriceTextField() { return this.priceTextField.getText();}
    public String getRatingTextField() { return this.ratingTextField.getText();}
    public String getCaloriesTextField() { return this.caloriesTextField.getText();}
    public String getProteinsTextField() { return this.proteinsTextField.getText();}
    public String getFatsTextField() { return this.fatsTextField.getText();}
    public String getSodiumTextField() { return this.sodiumTextField.getText();}
    public void resetSearchTextFields(){
        this.nameTextField.setText("");
        this.priceTextField.setText("");
        this.ratingTextField.setText("");
        this.caloriesTextField.setText("");
        this.proteinsTextField.setText("");
        this.fatsTextField.setText("");
        this.sodiumTextField.setText("");
    }
    public void setComboBox(){
        for(MenuItem item : deliveryService.getMenuItems()){
            this.comboBoxMenu.addItem(item.productToString());
        }
    }
}
