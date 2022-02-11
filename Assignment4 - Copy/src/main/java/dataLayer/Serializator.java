package dataLayer;

import businessLayer.Client;
import businessLayer.IDeliveryServiceProcessing;
import businessLayer.MenuItem;
import businessLayer.Order;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class Serializator implements Serializable {
    private IDeliveryServiceProcessing deliveryServiceProcessing;

    public Serializator(IDeliveryServiceProcessing deliveryServiceProcessing){
        this.deliveryServiceProcessing=deliveryServiceProcessing;
    }

    public void serializeOrders(){
        try {
            FileOutputStream fos = new FileOutputStream("orders.txt");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(this.deliveryServiceProcessing.getOrders());
            oos.close();
            fos.close();
        }
        catch(IOException e){
            System.out.println("Couldn't serialize orders HashMap!");
        }
    }
    public void deserializeOrders(){
        try{
            FileInputStream fis=new FileInputStream("orders.txt");
            ObjectInput ois=new ObjectInputStream(fis);
            this.deliveryServiceProcessing.setOrders((HashMap<Order, List<MenuItem>>) ois.readObject());
            ois.close();
            fis.close();
        }
        catch(Exception ex){
            System.out.println("Couldn't deserialize orders HashMap!");
        }
    }
    public void serializeMenuItems(){
        try{
            FileOutputStream fos=new FileOutputStream("menuItems.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(this.deliveryServiceProcessing.getMenuItems());
            oos.close();
            fos.close();
        }
        catch(IOException e){
            System.out.println("Couldn't serialize menuItem list!");
        }
    }
    public void deserializeMenuItems(){
        try{
            FileInputStream fis=new FileInputStream("menuItems.txt");
            ObjectInput ois=new ObjectInputStream(fis);
            this.deliveryServiceProcessing.setMenuItems((List<MenuItem>) ois.readObject());
            ois.close();
            fis.close();
        }
        catch(Exception ex){
            System.out.println("Couldn't deserialize menuItem list!");
        }
    }
    public void serializeClientsList(){
        try{
            FileOutputStream fos=new FileOutputStream("clients.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(Client.getClientList());
            oos.close();
            fos.close();
        }
        catch(IOException e){
            System.out.println("Couldn't serialize clients list!");
        }
    }
    public void deserializeClients(){
        try{
            FileInputStream fis=new FileInputStream("clients.txt");
            ObjectInput ois=new ObjectInputStream(fis);
            Client.setClientList((List<Client>) ois.readObject());
            ois.close();
            fis.close();
        }
        catch(Exception ex){
            System.out.println("Couldn't deserialize clients list!");
        }
    }
    public void serializeBaseProducts(){
        try{
            FileOutputStream fos=new FileOutputStream("baseProducts.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(this.deliveryServiceProcessing.getBaseItems());
            oos.close();
            fos.close();
        }
        catch(IOException e){
            System.out.println("Couldn't serialize baseProducts list!");
        }
    }
    public void deserializeBaseProducts(){
        try{
            FileInputStream fis=new FileInputStream("baseProducts.txt");
            ObjectInput ois=new ObjectInputStream(fis);
            this.deliveryServiceProcessing.setBaseProducts((List<MenuItem>) ois.readObject());
            ois.close();
            fis.close();
        }
        catch(Exception ex){
            System.out.println("Couldn't deserialize baseProducts list!");
        }
    }
}
