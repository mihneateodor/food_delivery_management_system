package presentationLayer;

import businessLayer.IDeliveryServiceProcessing;
import businessLayer.MenuItem;
import businessLayer.Order;
import dataLayer.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.List;

public class EmployeeGUI extends JFrame implements Subscriber<Order> {
    private JPanel content=new JPanel();
    private JLabel ordersLabel=new JLabel("Comenzi procesate:");
    private JEditorPane textBox=new JEditorPane();

    private Subscription subscription;
    private int counter=0;
    private Serializator serializator;

    private IDeliveryServiceProcessing deliveryService;
    private String mesaj;

    public EmployeeGUI(IDeliveryServiceProcessing deliveryService,Serializator serializator){
        this.deliveryService=deliveryService;
        this.serializator=serializator;
        this.mesaj="";

        this.setBounds(100, 100, 819, 489);
        this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
        content.setLayout(null);
        this.setTitle("Angajat");

        ordersLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        ordersLabel.setBounds(10, 11, 295, 35);
        content.add(ordersLabel);

        textBox.setEditable(false);
        content.add(textBox);

        final JScrollPane jScrollPane=new JScrollPane(textBox);
        jScrollPane.setBounds(10, 49, 783, 373);
        content.add(jScrollPane);

        this.setContentPane(content);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription=subscription;
        this.subscription.request(1);
    }
    @Override
    public void onNext(Order item) {
        List<MenuItem> products=deliveryService.getOrders().get(item);
        mesaj+="\nComanda numarul "+item.getOrderID()+" a clientului cu ID-ul "+item.getClientID()+" din data de "+item.getOrderDate().toString()+" cu pretul total de "+item.getPrice()+":\n";
        for(MenuItem product : products){
            mesaj+=product.productToString()+"\n";
        }
        textBox.setText(mesaj);
        this.subscription.request(1);
    }
    @Override
    public void onError(Throwable throwable) {
        this.mesaj=this.mesaj+"\n"+"Eroare la procesarea comenzilor!\n";
        textBox.setText(mesaj);
    }
    @Override
    public void onComplete() {
        this.mesaj=this.mesaj+"\nProcesarea comenzilor finalizata cu succes!\n";
        textBox.setText(mesaj);
    }
    public int getCounter() {return this.counter;}
}
