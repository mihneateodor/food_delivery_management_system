package controller;

import businessLayer.*;
import businessLayer.MenuItem;
import dataLayer.Serializator;
import presentationLayer.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class Controller {
    private View1 view1;
    private LogInEmployee logInEmployee;
    private LogInAdmin logInAdmin;
    private LogInClient logInClient;
    private RegisterClient registerClient;
    private IDeliveryServiceProcessing deliveryService;
    private ClientGUI clientGUI;
    private EmployeeGUI employeeGUI;
    private AdminGUI adminGUI;
    private Serializator serializator;

    private List<MenuItem> listForOrder;
    private List<MenuItem> listForSearch;
    private List<MenuItem> listForCreateCompositeProduct;

    public Controller() {
        view1=new View1();
        view1.setVisible(true);
        logInEmployee=new LogInEmployee();
        logInAdmin=new LogInAdmin();
        logInClient=new LogInClient();
        registerClient=new RegisterClient();
        deliveryService=new DeliveryService();
        serializator=new Serializator(this.deliveryService);
        serializator.deserializeOrders();
        serializator.deserializeMenuItems();
        serializator.deserializeClients();
        serializator.deserializeBaseProducts();

        listForCreateCompositeProduct=new ArrayList<>();
        listForOrder=new ArrayList<>();
        listForSearch=new ArrayList<>();

        view1.employeeActionListener(new View1EmployeeActionListener());
        logInEmployee.connectActionListener(new EmployeeLogInActionListener());
        view1.adminActionListener(new View1AdminActionListener());
        logInAdmin.connectActionListener(new AdminLogInActionListener());
        view1.clientActionListener(new View1ClientActionListener());
        logInClient.connectActionListener(new ClientLogInActionListener());
        logInClient.registerActionListener(new ClientLogRegisterActionListener());
        registerClient.registerActionListener(new ClientRegisterActionListener());
    }

    class View1EmployeeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logInEmployee.setVisible(true);
        }
    }

    class View1AdminActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logInAdmin.setVisible(true);
        }
    }

    class View1ClientActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logInClient.setVisible(true);
        }
    }

    class ClientLogRegisterActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            registerClient.setVisible(true);
        }
    }

    class ClientRegisterActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Client newClient = new Client();
            String user, pass;
            user = registerClient.getUsernameText();
            pass = registerClient.getPasswordText();
            registerClient.clearUsernameAndPasswordText();
            if(!user.equals("") && !pass.equals("")) {
                if (newClient.clientRegister(user, pass) == true) {
                    JOptionPane.showMessageDialog(registerClient, "Inregistrare cu succes!");
                    serializator.serializeClientsList();
                } else
                    JOptionPane.showMessageDialog(registerClient, "Un client detine deja aceste date de autentificare!");
            }
            else
                JOptionPane.showMessageDialog(registerClient, "Date invalide!");
        }
    }

    class EmployeeLogInActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String user, pass;
            Employee basicEmployee = new Employee("employeeUser", "employeePass");
            user = logInEmployee.getUsernameText();
            pass = logInEmployee.getPasswordText();
            logInEmployee.clearUsernameAndPasswordText();
            if (basicEmployee.employeeLogIn(user, pass) == true) {
                employeeGUI=new EmployeeGUI(deliveryService,serializator);
                employeeGUI.setVisible(true);
                SubmissionPublisher<Order> publisher=new SubmissionPublisher<>();
                publisher.subscribe(employeeGUI);
                deliveryService.getOrderList().forEach(publisher::submit);
                publisher.close();
            } else
                JOptionPane.showMessageDialog(logInEmployee, "Date de autentificare invalide!");
        }
    }

    class AdminLogInActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String user, pass;
            Admin basicAdmin = new Admin("adminUser", "adminPass");
            user = logInAdmin.getUsernameText();
            pass = logInAdmin.getPasswordText();
            logInAdmin.clearUsernameAndPasswordText();
            if (basicAdmin.adminLogIn(user, pass) == true) {
                adminGUI=new AdminGUI(deliveryService);
                adminGUI.importProductsBtnActionListener(new AdminImportActionListener());
                adminGUI.createBaseProductBtnActionListener(new AdminAddBaseProductToMenuItemsActionListener());
                adminGUI.modifyBaseProductBtnActionListener(new AdminModifyBaseProduct());
                adminGUI.deleteBaseProductBtnActionListener(new AdminDeleteFromMenuItems());
                adminGUI.deleteBPFromCPBtnActionListener(new AdminDeleteItemFromCompositeProduct());
                adminGUI.createCPBtnActionListener(new AdminCreateCompositeProduct());
                adminGUI.addToCPBtnActionListener(new AdminAddBaseProductToCompositeProduct());
                adminGUI.generateRaport1BtnActionListener(new AdminGenerateRaport1());
                adminGUI.generateRaport2BtnActionListener(new AdminGenerateRaport2());
                adminGUI.generateRaport3BtnActionListener(new AdminGenerateRaport3());
                adminGUI.generateRaport4BtnActionListener(new AdminGenerateRaport4());
                adminGUI.setComboBox(deliveryService);
                adminGUI.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(logInAdmin, "Date de autentificare invalide!");
        }
    }

    class ClientLogInActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String user, pass;
            Client client=null;
            user = logInClient.getUsernameText();
            pass = logInClient.getPasswordText();
            logInClient.clearUsernameAndPasswordText();
            if (Client.clientLogIn(user, pass) == true){
                for(Client cl : Client.getClientList()){
                    if(cl.getClientUsername().equals(user) && cl.getClientPassword().equals(pass)){
                        client=cl;
                        break;
                    }
                }
                clientGUI=new ClientGUI(deliveryService,client);
                clientGUI.setComboBox();
                clientGUI.addButtonActionListener(new ClientAddItemActionListener());
                clientGUI.removeButtonActionListener(new ClientRemoveItemActionListener());
                clientGUI.createOrderButtonActionListener(new ClientCreateOrderActionListener());
                clientGUI.searchActionListener(new ClientSearchOrderActionListener());
                clientGUI.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(logInClient, "Date de autentificare invalide!");
        }
    }
    class AdminGenerateRaport4 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String year= adminGUI.getYearTf();
                String month=adminGUI.getMonthTf();
                String day=adminGUI.getDayTf();
                deliveryService.generateReportProductDayNumberOfTimes(year,month,day);
                adminGUI.resetTextFields();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(adminGUI,"Introduceti date numerice!");
            }
        }
    }
    class AdminGenerateRaport3 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String number= adminGUI.getNumberRaport3();
                String minValue=adminGUI.getMinValueRaport3();
                deliveryService.generateReportClientsOrderedMoreAndValue(number,minValue);
                adminGUI.resetTextFields();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(adminGUI,"Introduceti date numerice!");
            }
        }
    }
    class AdminGenerateRaport2 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String number=adminGUI.getNumberRaport2();
                deliveryService.generateReportProductsMoreThanSpecifiedTimes(number);
                adminGUI.resetTextFields();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(adminGUI,"Introduceti date numerice!");
            }
        }
    }
    class AdminGenerateRaport1 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String hour1=adminGUI.getHour1Tf();
                String hour2=adminGUI.getHour2Tf();
                deliveryService.generateReportTimeInterval(hour1,hour2);
                adminGUI.resetTextFields();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(adminGUI,"Introduceti date numerice.");
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(adminGUI, ex.getMessage());
            }
        }
    }
    class AdminAddBaseProductToCompositeProduct implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            int index=adminGUI.getComboBoxIndex();
            deliveryService.addNewProductToComposite(index,listForCreateCompositeProduct);
        }
    }
    class AdminCreateCompositeProduct implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String nameCompositeProduct=adminGUI.getNameForCompositeProductTf();
            if(!nameCompositeProduct.equals("")) {
                deliveryService.addNewCompositeProductToMenuItems(nameCompositeProduct, listForCreateCompositeProduct);
                listForCreateCompositeProduct.clear();
                adminGUI.resetTextFields();
                adminGUI.setComboBox(deliveryService);
                serializator.serializeMenuItems();
            }
            else
                JOptionPane.showMessageDialog(adminGUI,"Introduceti numele produsului compus!");
        }
    }
    class AdminDeleteItemFromCompositeProduct implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                int indexMenuItems = adminGUI.getComboBoxIndex();
                int indexCompositeProduct = Integer.parseInt(adminGUI.getIndexToDelete());
                deliveryService.removeProductFromComposite(indexMenuItems,indexCompositeProduct);
                adminGUI.setComboBox(deliveryService);
                adminGUI.resetTextFields();
                serializator.serializeMenuItems();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(adminGUI,"Date invalide!");
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(adminGUI,"Nu exista acest index in produsul compus!");
            }
        }
    }
    class AdminDeleteFromMenuItems implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int index=adminGUI.getComboBoxIndex();
            deliveryService.removeMenuItem(index);
            adminGUI.setComboBox(deliveryService);
            serializator.serializeMenuItems();
        }
    }
    class AdminModifyBaseProduct implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int index=adminGUI.getComboBoxIndex();
            String title=adminGUI.getCreateNameTf();
            String rating=adminGUI.getCreateRatingTf();
            String calories=adminGUI.getCreateCaloriesTf();
            String proteins=adminGUI.getCreateProteinsTf();
            String fats=adminGUI.getCreateFatsTf();
            String sodium=adminGUI.getCreateSodiumTf();
            String price=adminGUI.getCreatePriceTf();
            try{
                deliveryService.modifyBaseProductFromMenuItems(index,title,rating,calories,proteins,fats,sodium,price);
                adminGUI.resetTextFields();
                adminGUI.setComboBox(deliveryService);
                serializator.serializeMenuItems();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(adminGUI,"Date invalide!");
            }
        }
    }
    class AdminAddBaseProductToMenuItemsActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String titleS,ratingS,caloriesS,proteinsS,fatsS,sodiumS,priceS;
            try{
                titleS=adminGUI.getCreateNameTf();
                ratingS= adminGUI.getCreateRatingTf();
                caloriesS=adminGUI.getCreateCaloriesTf();
                proteinsS=adminGUI.getCreateProteinsTf();
                fatsS=adminGUI.getCreateFatsTf();
                sodiumS=adminGUI.getCreateSodiumTf();
                priceS= adminGUI.getCreatePriceTf();
                deliveryService.addNewBaseProductToMenuItems(titleS,ratingS,caloriesS,proteinsS,fatsS,sodiumS,priceS);
                adminGUI.resetTextFields();
                adminGUI.setComboBox(deliveryService);
                serializator.serializeMenuItems();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(adminGUI,"Date invalide!");
            }
        }
    }
    class AdminImportActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                deliveryService.importInitialSetOfProducts();
                serializator.serializeMenuItems();
                serializator.serializeBaseProducts();
                adminGUI.setComboBox(deliveryService);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(adminGUI,"Nu s-au putut importa produsele de baza!");
            }
        }
    }

    class ClientAddItemActionListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            listForOrder.add(deliveryService.getMenuItems().get(clientGUI.getJComboBoxMenuIndex()));
            MenuItem menuItem=deliveryService.getMenuItems().get(clientGUI.getJComboBoxMenuIndex());
            clientGUI.setTextBox(menuItem.productToString());
        }
    }
    class ClientRemoveItemActionListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            listForOrder.remove(deliveryService.getMenuItems().get(clientGUI.getJComboBoxMenuIndex()));
            clientGUI.setTextBox("");
        }
    }
    class ClientCreateOrderActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (!listForOrder.isEmpty()) {
                try {
                    int year=Integer.parseInt(clientGUI.getYearTextField());
                    int month=Integer.parseInt(clientGUI.getMonthTextField());
                    int day=Integer.parseInt(clientGUI.getDayTextField());
                    int hour=Integer.parseInt(clientGUI.getHourTextField());
                    Date date = new Date(year, month, day, hour);
                    deliveryService.createOrder(clientGUI.getClient().getClientID(), date, listForOrder);
                    String mesaj = "";
                    for (MenuItem item : listForOrder) {
                        mesaj += item.productToString() + "\n";
                    }
                    clientGUI.setTextBox(mesaj);
                    clientGUI.resetTextFields();
                    serializator.serializeOrders();
                    listForOrder.clear();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(clientGUI, "Introduceti date numerice in campurile pentru data!");
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(clientGUI, exception.getMessage());
                }
            }
            else
                JOptionPane.showMessageDialog(clientGUI,"Trebuie sa comandati cel putin un produs!");
        }
    }
    class ClientSearchOrderActionListener implements  ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                String title, rating, calories, proteins, fats, sodium, price;
                title = clientGUI.getNameTextField();
                rating = clientGUI.getRatingTextField();
                calories = clientGUI.getCaloriesTextField();
                proteins = clientGUI.getProteinsTextField();
                fats = clientGUI.getFatsTextField();
                sodium = clientGUI.getSodiumTextField();
                price = clientGUI.getPriceTextField();
                listForSearch = deliveryService.searchByCriteria(title, rating, calories, proteins, fats, sodium, price);
                String mesaj = "";
                for (MenuItem item : listForSearch) {
                    mesaj += item.productToString() + "\n";
                }
                clientGUI.setTextBox(mesaj);
                clientGUI.resetSearchTextFields();
                clientGUI.setComboBox();
                listForSearch.clear();
            }
            catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(clientGUI,"Date invalide!");
            }
        }
    }
}
