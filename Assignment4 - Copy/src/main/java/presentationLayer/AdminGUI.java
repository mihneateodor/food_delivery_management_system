package presentationLayer;

import businessLayer.IDeliveryServiceProcessing;
import businessLayer.MenuItem;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame {
    private JPanel content=new JPanel();
    private JLabel importLbl=new JLabel("Importa produsele de baza:");
    private JLabel createNewBaseProductLbl=new JLabel("Creeaza un nou produs de baza si adauga in meniu:");
    private JLabel createNewBaseProductInfoLbl = new JLabel("Name                                                         Rating            Calories         Proteins          Fats                Sodium          Price");
    private JLabel selectIndexForDeletelbl=new JLabel("Selecteaza index pentru stergerea produsului de baza dintr-un produs compus:");
    private JLabel nameCompositeProductlbl=new JLabel("Nume pentru produsul compus:");
    private JLabel raport1Lbl=new JLabel("Raport \"time interval of the orders\":");
    private JLabel hour1Lbl=new JLabel("Ora 1");
    private JLabel hour2Lbl=new JLabel("Ora 2");
    private JLabel raport2Lbl=new JLabel("Raport \"the products ordered more than specified number of times\":");
    private JLabel raport2NumberLbl=new JLabel("Numar:");
    private JLabel raport3Lbl=new JLabel("Raport \"clients that have ordered..\":");
    private JLabel raport3NumberLbl=new JLabel("Numar:");
    private JLabel raport3MinValueLbl=new JLabel("Valoare minima:");
    private JLabel raport4Lbl=new JLabel("Raport \"products ordered within a specified dat..\":");
    private JLabel yearLbl=new JLabel("An:");
    private JLabel monthLbl=new JLabel("Luna:");
    private JLabel dayLbl=new JLabel("Zi:");
    private JTextField createNameTf=new JTextField();
    private JTextField createRatingTf=new JTextField();
    private JTextField createCaloriesTf=new JTextField();
    private JTextField createProteinsTf=new JTextField();
    private JTextField createFatsTf=new JTextField();
    private JTextField createSodiumTf=new JTextField();
    private JTextField createPriceTf=new JTextField();
    private JTextField indexTf=new JTextField();
    private JTextField nameForCompositeProductTf=new JTextField();
    private JTextField hour1Tf=new JTextField();
    private JTextField hour2Tf=new JTextField();
    private JTextField raport2NumberTf=new JTextField();
    private JTextField raport3NumberTf=new JTextField();
    private JTextField raport3MinValueTf=new JTextField();
    private JTextField yearTf=new JTextField();
    private JTextField monthTf=new JTextField();
    private JTextField dayTf=new JTextField();
    private JComboBox comboBox=new JComboBox();
    private JButton createBaseProductBtn=new JButton("Adauga");
    private JButton modifyBaseProductBtn=new JButton("Modifica");
    private JButton deleteBaseProductBtn=new JButton("Sterge");
    private JButton deleteBPFromCPBtn=new JButton("Sterge din produs compus");
    private JButton addToCPBtn=new JButton("Adauga la produs compus");
    private JButton createCPBtn=new JButton("Creeaza produsul compus");
    private JButton generateRaport1Btn=new JButton("Genereaza raport 1");
    private JButton generateRaport2Btn=new JButton("Genereaza raport 2");
    private JButton generateRaport3Btn=new JButton("Genereaza raport 3");
    private JButton generateRaport4Btn=new JButton("Genereaza raport 4");
    private JButton importProductsBtn=new JButton("Import");

    private IDeliveryServiceProcessing deliveryService;

    public AdminGUI(IDeliveryServiceProcessing deliveryService){
        this.deliveryService=deliveryService;
        this.setBounds(100, 100, 866, 696);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        content.setLayout(null);
        this.setTitle("Admin");

        importLbl.setBounds(10, 11, 174, 24);
        content.add(importLbl);
        createNewBaseProductLbl.setBounds(10, 48, 300, 25);
        content.add(createNewBaseProductLbl);
        createNewBaseProductInfoLbl.setBounds(10, 84, 639, 25);
        content.add(createNewBaseProductInfoLbl);
        selectIndexForDeletelbl.setBounds(10, 213, 518, 25);
        content.add(selectIndexForDeletelbl);
        nameCompositeProductlbl.setBounds(10, 249, 227, 30);
        content.add(nameCompositeProductlbl);
        raport1Lbl.setBounds(10, 290, 263, 25);
        content.add(raport1Lbl);
        hour1Lbl.setBounds(292, 285, 53, 20);
        content.add(hour1Lbl);
        hour2Lbl.setBounds(292, 310, 53, 20);
        content.add(hour2Lbl);
        raport2Lbl.setBounds(10, 341, 540, 25);
        content.add(raport2Lbl);
        raport2NumberLbl.setBounds(10, 368, 53, 23);
        content.add(raport2NumberLbl);
        raport3Lbl.setBounds(10, 398, 351, 25);
        content.add(raport3Lbl);
        raport3NumberLbl.setBounds(10, 424, 53, 20);
        content.add(raport3NumberLbl);
        raport3MinValueLbl.setBounds(10, 446, 152, 20);
        content.add(raport3MinValueLbl);
        raport4Lbl.setBounds(10, 486, 581, 25);
        content.add(raport4Lbl);
        yearLbl.setBounds(10, 518, 21, 25);
        content.add(yearLbl);
        monthLbl.setBounds(10, 554, 56, 25);
        content.add(monthLbl);
        dayLbl.setBounds(10, 590, 21, 25);
        content.add(dayLbl);

        createNameTf.setBounds(10, 106, 189, 20);
        content.add(createNameTf);
        createRatingTf.setBounds(211, 106, 62, 20);
        content.add(createRatingTf);
        createCaloriesTf.setBounds(283, 106, 62, 20);
        content.add(createCaloriesTf);
        createProteinsTf.setBounds(355, 106, 69, 20);
        content.add(createProteinsTf);
        createFatsTf.setBounds(434, 106, 62, 20);
        content.add(createFatsTf);
        createSodiumTf.setBounds(506, 106, 62, 20);
        content.add(createSodiumTf);
        createPriceTf.setBounds(578, 106, 53, 20);
        content.add(createPriceTf);
        indexTf.setBounds(538, 215, 86, 20);
        content.add(indexTf);
        nameForCompositeProductTf.setBounds(190, 249, 189, 20);
        content.add(nameForCompositeProductTf);
        hour1Tf.setBounds(355, 285, 86, 20);
        content.add(hour1Tf);
        hour2Tf.setBounds(355, 310, 86, 20);
        content.add(hour2Tf);
        raport2NumberTf.setBounds(76, 369, 86, 20);
        content.add(raport2NumberTf);
        raport3NumberTf.setBounds(184, 424, 86, 20);
        content.add(raport3NumberTf);
        raport3MinValueTf.setBounds(184, 446, 86, 20);
        content.add(raport3MinValueTf);
        yearTf.setBounds(76, 522, 86, 20);
        content.add(yearTf);
        monthTf.setBounds(76, 556, 86, 20);
        content.add(monthTf);
        dayTf.setBounds(76, 592, 86, 20);
        content.add(dayTf);

        comboBox.setBounds(10, 146, 830, 22);
        content.add(comboBox);

        importProductsBtn.setBounds(184, 12, 89, 23);
        content.add(importProductsBtn);
        createBaseProductBtn.setBounds(751, 105, 89, 23);
        content.add(createBaseProductBtn);
        modifyBaseProductBtn.setBounds(652, 179, 89, 23);
        content.add(modifyBaseProductBtn);
        deleteBaseProductBtn.setBounds(751, 179, 89, 23);
        content.add(deleteBaseProductBtn);
        deleteBPFromCPBtn.setBounds(634, 213, 206, 25);
        content.add(deleteBPFromCPBtn);
        addToCPBtn.setBounds(383, 249, 208, 25);
        content.add(addToCPBtn);
        createCPBtn.setBounds(601, 249, 239, 24);
        content.add(createCPBtn);
        generateRaport1Btn.setBounds(451, 309, 158, 23);
        content.add(generateRaport1Btn);
        generateRaport2Btn.setBounds(210, 368, 151, 23);
        content.add(generateRaport2Btn);
        generateRaport3Btn.setBounds(273, 423, 151, 23);
        content.add(generateRaport3Btn);
        generateRaport4Btn.setBounds(194, 591, 151, 23);
        content.add(generateRaport4Btn);

        this.setContentPane(content);
    }
    public void importProductsBtnActionListener(ActionListener a){
        importProductsBtn.addActionListener(a);
    }
    public void createBaseProductBtnActionListener(ActionListener a){
        createBaseProductBtn.addActionListener(a);
    }
    public void modifyBaseProductBtnActionListener(ActionListener a){
        modifyBaseProductBtn.addActionListener(a);
    }
    public void deleteBaseProductBtnActionListener(ActionListener a){
        deleteBaseProductBtn.addActionListener(a);
    }
    public void deleteBPFromCPBtnActionListener(ActionListener a){
        deleteBPFromCPBtn.addActionListener(a);
    }
    public void addToCPBtnActionListener(ActionListener a){
        addToCPBtn.addActionListener(a);
    }
    public void createCPBtnActionListener(ActionListener a){
        createCPBtn.addActionListener(a);
    }
    public void generateRaport1BtnActionListener(ActionListener a){
        generateRaport1Btn.addActionListener(a);
    }
    public void generateRaport2BtnActionListener(ActionListener a){
        generateRaport2Btn.addActionListener(a);
    }
    public void generateRaport3BtnActionListener(ActionListener a){
        generateRaport3Btn.addActionListener(a);
    }
    public void generateRaport4BtnActionListener(ActionListener a){
        generateRaport4Btn.addActionListener(a);
    }
    public String getCreateNameTf () {return this.createNameTf.getText();}
    public String getCreateRatingTf() { return this.createRatingTf.getText();}
    public String getCreateCaloriesTf() {return this.createCaloriesTf.getText();}
    public String getCreateProteinsTf() { return this.createProteinsTf.getText();}
    public String getCreateFatsTf() {return this.createFatsTf.getText();}
    public String getCreateSodiumTf() {return this.createSodiumTf.getText();}
    public String getCreatePriceTf() {return this.createPriceTf.getText();}
    public String getIndexToDelete() { return this.indexTf.getText();}
    public String getNumberRaport2() {return this.raport2NumberTf.getText();}
    public String getNumberRaport3() {return this.raport3NumberTf.getText();}
    public String getMinValueRaport3() {return this.raport3MinValueTf.getText();}
    public String getYearTf() {return this.yearTf.getText();}
    public String getMonthTf() {return this.monthTf.getText();}
    public String getDayTf() {return this.dayTf.getText();}
    public String getHour1Tf() {return this.hour1Tf.getText();}
    public String getHour2Tf() {return this.hour2Tf.getText();}
    public String getNameForCompositeProductTf() {return this.nameForCompositeProductTf.getText();}
    public void resetTextFields(){
        this.createNameTf.setText("");
        this.createRatingTf.setText("");
        this.createCaloriesTf.setText("");
        this.createProteinsTf.setText("");
        this.createFatsTf.setText("");
        this.createSodiumTf.setText("");
        this.createPriceTf.setText("");
        this.indexTf.setText("");
        this.nameForCompositeProductTf.setText("");
        this.hour1Tf.setText("");
        this.hour2Tf.setText("");
        this.raport2NumberTf.setText("");
        this.raport3NumberTf.setText("");
        this.raport3MinValueTf.setText("");
        this.yearTf.setText("");
        this.monthTf.setText("");
        this.dayTf.setText("");
    }
    public void setComboBox(IDeliveryServiceProcessing deliveryService){
        this.comboBox.removeAllItems();
        for(MenuItem item: deliveryService.getMenuItems()) {
            this.comboBox.addItem(item.productToString());
        }
    }
    public int getComboBoxIndex() {return this.comboBox.getSelectedIndex();}

}
