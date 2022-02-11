package businessLayer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveryService implements IDeliveryServiceProcessing {

    private List<MenuItem> menuItems;
    private HashMap<Order, List<MenuItem>> orders;
    private List<MenuItem> baseItems;
    private List<Order> orderList;

    public DeliveryService() {
        menuItems = new ArrayList<>();
        orders = new HashMap<>();
        baseItems = new ArrayList<>();
        orderList = new ArrayList<>();
    }

    public boolean invariantMethod() {
        boolean ok = true;
        if (!orders.isEmpty()) {
            for (Map.Entry<Order, List<MenuItem>> entry : orders.entrySet()) {
                if (entry.getValue().get(0).getClass() != BaseProduct.class && entry.getValue().get(0).getClass() != CompositeProduct.class) {
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }

    public void importInitialSetOfProducts() throws IOException {
        assert invariantMethod() == true : "Invariant failed.";
        BaseProduct importedInitialSet = new BaseProduct();
        for (BaseProduct product : importedInitialSet.getBaseProducts()) {
            menuItems.add(product);
            baseItems.add(product);
        }
    }

    public void createOrder(int clientId, Date date, List<MenuItem> list) {
        assert invariantMethod() == true : "Invariant failed.";
        assert clientId >= 0 : "Wrong";
        assert list != null : "Wrong";
        assert date.getYear() > 2019 : "Wrong";
        assert 2022>date.getYear() :"Wrong";
        assert date.getMonth() > 0: "Wrong";
        assert 13>date.getMonth() : "Wrong";
        assert date.getDay() > 0 : "Wrong";
        assert 32>date.getDay() :"Wrong";
        Order order = new Order();
        order.hashCode(clientId, date);
        orderList.add(order);
        int orderPrice = 0;
        List<MenuItem> menuItems = new ArrayList<>();
        for (MenuItem item : list) {
            item.incrementTimesOrdered();
            orderPrice += item.computePrice();
            menuItems.add(item);
        }
        Client.getClientList().get(clientId - 1).incrementTimesOrdered();
        order.setPrice(orderPrice);
        orders.put(order, menuItems);
        this.generateBill(order, list);

    }

    public void generateBill(Order order, List<MenuItem> list) {
        assert invariantMethod() == true : "Invariant failed.";
        assert order!=null : "Wrong";
        assert list!=null:"Wrong";
        try {
            FileWriter file = new FileWriter("order_" + order.getOrderID() + "_" + order.getClientID() + "_" + order.getOrderDate().toString() + ".txt");
            String mesaj = "";
            mesaj = mesaj + "Detalii comanda:\nID comanda: " + order.getOrderID() + "\n" + "ID client: " + order.getClientID() + "\n" + "Pretul total al comenzii: " + order.getPrice() + "\n";
            for (MenuItem item : list) {
                mesaj = mesaj + item.productToString() + "\n";
            }
            file.write(mesaj);
            file.close();
        } catch (IOException e) {
            System.out.println("Nu s-a putut crea sau scrie in fisier.");
        }
    }

    public void addNewBaseProductToMenuItems(String title, String ratingString, String caloriesString, String proteinString, String fatString, String sodiumString, String priceString) {
        assert invariantMethod() == true : "Invariant failed.";
        assert title != null : "Wrong";
        assert ratingString != null : "Wrong";
        assert caloriesString != null : "Wrong";
        assert proteinString != null : "Wrong";
        assert fatString != null : "Wrong";
        assert sodiumString != null : "Wrong";
        assert priceString != null : "Wrong";
        double rating = Double.parseDouble(ratingString);
        int calories = Integer.parseInt(caloriesString);
        int protein = Integer.parseInt(proteinString);
        int fat = Integer.parseInt(fatString);
        int sodium = Integer.parseInt(sodiumString);
        int price = Integer.parseInt(priceString);
        MenuItem menuItem = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        menuItems.add(menuItem);
    }

    public void removeMenuItem(int index) {
        assert invariantMethod() == true : "Invariant failed.";
        assert index >= 0 : "Wrong";
        this.getMenuItems().remove(index);
    }

    public void modifyBaseProductFromMenuItems(int index, String title, String ratingString, String caloriesString, String proteinString, String fatString, String sodiumString, String priceString) {
        assert invariantMethod() == true : "Invariant failed.";
        assert index >= 0 : "Wrong";
        assert title != null : "Wrong";
        assert ratingString != null : "Wrong";
        assert caloriesString != null : "Wrong";
        assert proteinString != null : "Wrong";
        assert fatString != null : "Wrong";
        assert sodiumString != null : "Wrong";
        assert priceString != null : "Wrong";
        double rating = Double.parseDouble(ratingString);
        int calories = Integer.parseInt(caloriesString);
        int protein = Integer.parseInt(proteinString);
        int fat = Integer.parseInt(fatString);
        int sodium = Integer.parseInt(sodiumString);
        int price = Integer.parseInt(priceString);
        this.getMenuItems().get(index).setTitle(title);
        this.getMenuItems().get(index).setRating(rating);
        this.getMenuItems().get(index).setCalories(calories);
        this.getMenuItems().get(index).setProtein(protein);
        this.getMenuItems().get(index).setFat(fat);
        this.getMenuItems().get(index).setSodium(sodium);
        this.getMenuItems().get(index).setPrice(price);

    }

    public void addNewCompositeProductToMenuItems(String title, List<MenuItem> list) {
        assert invariantMethod() == true : "Invariant failed.";
        assert title != null : "Wrong";
        assert list != null : "Wrong";
        assert list.size() >= 1 : "Wrong";
        MenuItem menuItem = new CompositeProduct(title);
        for (MenuItem product : list) {
            menuItem.add(product);
        }
        menuItems.add(menuItem);
        assert menuItem!=null:"Wrong";
    }

    public void removeProductFromComposite(int indexMenuItems, int indexCompositeProduct) {
        assert invariantMethod() == true : "Invariant failed.";
        assert indexMenuItems >= 0 : "Wrong";
        assert indexCompositeProduct >= 0 : "Wrong";
        if (this.menuItems.get(indexMenuItems).getClass() == CompositeProduct.class) {
            this.menuItems.get(indexMenuItems).remove(this.menuItems.get(indexMenuItems).getMenuItem(indexCompositeProduct));
        }
    }

    public void addNewProductToComposite(int indexMenuItems, List<MenuItem> compositeProductList) {
        assert invariantMethod() == true : "Invariant failed.";
        assert indexMenuItems >= 0 : "Wrong";
        assert compositeProductList != null : "Wrong";
        compositeProductList.add(menuItems.get(indexMenuItems));
    }

    public List<MenuItem> searchByCriteria(String title, String ratingString, String caloriesString, String proteinString, String fatString, String sodiumString, String priceString) {
        assert invariantMethod() == true : "Invariant failed.";
        double rating;
        int calories, protein, fat, sodium, price;
        if (!ratingString.equals(""))
            rating = Double.parseDouble(ratingString);
        else
            rating = 10.0;
        if (!caloriesString.equals(""))
            calories = Integer.parseInt(caloriesString);
        else
            calories = -1;
        if (!proteinString.equals(""))
            protein = Integer.parseInt(proteinString);
        else
            protein = -1;
        if (!fatString.equals(""))
            fat = Integer.parseInt(fatString);
        else
            fat = -1;
        if (!sodiumString.equals(""))
            sodium = Integer.parseInt(sodiumString);
        else sodium = -1;
        if (!priceString.equals(""))
            price = Integer.parseInt(priceString);
        else
            price = -1;
        List<MenuItem> result = baseItems.stream()
                .filter(p -> (p.getTitle().contains(title) || title.equals("")) && (p.getRating() >= rating || rating >= 10) && (p.getCalories() == calories || calories == -1) &&
                        (p.getProtein() == protein || protein == -1) && (p.getFat() == fat || fat == -1) && (p.getSodium() == sodium || sodium == -1) && (p.computePrice() == price || price == -1))
                .collect(Collectors.toList());
        return result;
    }

    public void generateReportTimeInterval(String hour1String, String hour2String) throws Exception {
        assert invariantMethod() == true : "Invariant failed.";
        assert hour1String!=null:"Wrong";
        assert hour2String!=null:"Wrong";
        int hour1 = Integer.parseInt(hour1String);
        int hour2 = Integer.parseInt(hour2String);
        if (hour1 < 1 || hour1 > 24) {
            throw new Exception("Ora incompatibila!");
        } else if (hour2 < 1 || hour2 > 24) {
            throw new Exception("Ora incompatibila!");
        } else {
            List<Order> result = orderList.stream()
                    .filter(p -> p.getOrderDate().getHour() >= hour1 && p.getOrderDate().getHour() <= hour2)
                    .collect(Collectors.toList());
            try {
                FileWriter report = new FileWriter("OrdersBetween" + hour1 + "and" + hour2 + ".txt");
                String mesaj = "";
                if (result.isEmpty()) {
                    mesaj = "Nu exista comenzi in intervalul orar " + hour1 + " - " + hour2 + " .";
                } else {
                    mesaj += "Comenzile plasate in intervalul orar " + hour1 + " - " + hour2 + " sunt:\n\n";
                    for (Order order : result) {
                        mesaj += "Detalii comanda:\nID comanda: " + order.getOrderID() + "\n" + "ID client: " + order.getClientID() + "\n" + "Pretul total al comenzii: " + order.getPrice() + "\n";
                        List<MenuItem> items = orders.get(order);
                        for (MenuItem item : items) {
                            mesaj += item.productToString() + "\n";
                        }
                    }
                }
                report.write(mesaj);
                report.close();
            } catch (IOException e) {
                System.out.println("Nu s-a putut genera acest raport!");
            }
        }
    }

    public void generateReportProductsMoreThanSpecifiedTimes(String numberOfTimesString) {
        assert invariantMethod() == true : "Invariant failed.";
        assert numberOfTimesString != null : "Wrong";
        int numberOfTimes = Integer.parseInt(numberOfTimesString);
        List<MenuItem> result = getMenuItems().stream()
                .filter(p -> p.getTimesOrdered() >= numberOfTimes)
                .collect(Collectors.toList());
        try {
            FileWriter report = new FileWriter("ProductsOrderedAtLeast" + numberOfTimes + "Times.txt");
            String mesaj = "";
            if (result.isEmpty()) {
                mesaj = "Nu exista produse comandate de cel putin " + numberOfTimes + " ori.";
            } else {
                mesaj += "Produsele comandate de cel putin " + numberOfTimes + " ori sunt:\n";
                for (MenuItem item : result) {
                    mesaj += item.productToString() + "\n";
                }
            }
            report.write(mesaj);
            report.close();
        } catch (IOException e) {
            System.out.println("Nu s-a putut genera acest raport.");
        }
    }

    public void generateReportClientsOrderedMoreAndValue(String numberOfTimesString, String minimumValueString) {
        assert invariantMethod() == true : "Invariant failed.";
        assert numberOfTimesString!=null:"Wrong";
        assert minimumValueString!=null:"Wrong";
        int numberOfTimes = Integer.parseInt(numberOfTimesString);
        int minimumValue = Integer.parseInt(minimumValueString);
        List<Client> result = Client.getClientList().stream()
                .filter(p -> p.getTimesOrdered() >= numberOfTimes)
                .collect(Collectors.toList());
        List<Order> result1 = new ArrayList<>();
        try {
            FileWriter report = new FileWriter("ClientsReportWith" + numberOfTimes + "TimesAnd" + minimumValue + "MinimumValueOrder.txt");
            String mesaj = "";
            if (result.isEmpty()) {
                mesaj = "Nu exista clienti care sa fi comandat de cel putin " + numberOfTimes + " ori, iar valoarea minima a comenzii sa fie de " + minimumValue;
            } else {

                for (Client client : result) {
                    for (Order order : getOrderList()) {
                        if (order.getClientID() == client.getClientID() && order.getPrice() >= minimumValue)
                            result1.add(order);
                    }
                }
                if (result1.isEmpty()) {
                    mesaj += "Nu exista clienti care sa fi comandat de cel putin " + numberOfTimes + " ori, iar valoarea minima a comenzii sa fie de " + minimumValue;
                } else {
                    mesaj += "Clientii care au comandat de cel putin " + numberOfTimes + " ori si au cel putin o comanda cu valoarea pretului de " + minimumValue + ", sunt:\n";
                    for (Order order : result1) {
                        mesaj += "Clientul numarul " + order.getClientID() + " cu numele de utilizator " + Client.getClientList().get(order.getClientID() - 1).getClientUsername() +
                                " a avut comanda " + order.getOrderID() + " in valoare de " + order.getPrice() + ".\n";
                    }
                }
            }
            report.write(mesaj);
            report.close();
        } catch (IOException e) {
            System.out.println("Nu s-a putut genera acest raport.");
        }
    }

    public void generateReportProductDayNumberOfTimes(String yearString, String monthString, String dayString) {
        assert invariantMethod() == true : "Invariant failed.";
        assert yearString!=null:"Wrong";
        assert monthString!=null:"Wrong";
        assert dayString!=null:"Wrong";
        int year, month, day;
        year = Integer.parseInt(yearString);
        month = Integer.parseInt(monthString);
        day = Integer.parseInt(dayString);
        List<Order> result = orderList.stream()
                .filter(p -> p.getOrderDate().getYear() == year && p.getOrderDate().getMonth() == month && p.getOrderDate().getDay() == day)
                .collect(Collectors.toList());
        try {
            FileWriter report = new FileWriter("ProductsOrderedWithin" + day + "-" + month + "-" + year + ".txt");
            String mesaj = "";
            if (result.isEmpty()) {
                mesaj = "Nu exista produse comandate in data de " + day + " " + month + " " + year + ".";
            } else {
                mesaj += "In data de " + day + " " + month + " " + year + ", au fost comandate:\n";
                for (Order order : result) {
                    List<MenuItem> items = orders.get(order);
                    for (MenuItem item : items) {
                        mesaj += "Produsul " + item.productToString() + " a fost comandat de " + item.getTimesOrdered() + "\n";
                    }
                }
            }
            report.write(mesaj);
            report.close();
        } catch (IOException e) {
            System.out.println("Nu s-a putut genera acest raport.");
        }
    }

    public List<MenuItem> getMenuItems() {
        assert invariantMethod()==true:"Invariant failed.";
        return this.menuItems;
    }
    public List<MenuItem> getBaseItems() {
        assert invariantMethod()==true:"Invariant failed.";
        return this.baseItems;
    }
    public HashMap<Order, List<MenuItem>> getOrders() {
        assert invariantMethod()==true:"Invariant failed.";
        return this.orders;
    }
    public List<Order> getOrderList() {
        assert invariantMethod()==true:"Invariant failed";
        return this.orderList;
    }
    public void setOrders(HashMap<Order, List<MenuItem>> list) {
        assert invariantMethod()==true:"Invariant failed.";
        this.orders = list;
    }
    public void setMenuItems(List<MenuItem> list) {
        assert invariantMethod()==true:"Invariant failed.";
        this.menuItems = list;
    }
    public void setBaseProducts(List<MenuItem> list) {
        assert invariantMethod()==true:"Invariant failed.";
        this.baseItems = list;
    }
    public void setOrderList(List<Order> list) {
        assert invariantMethod()==true:"Invariant failed.";
        this.orderList = list;
    }
}
