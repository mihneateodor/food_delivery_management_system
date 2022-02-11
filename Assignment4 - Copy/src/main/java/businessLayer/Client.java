package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {
    private String clientUsername;
    private String clientPassword;
    private int clientID;
    private static int clientCounter=0;
    private int timesOrdered=0;

    private static List<Client> clientList=new ArrayList<>();

    public Client(){}

    public String getClientUsername() { return this.clientUsername;}
    public String getClientPassword(){ return this.clientPassword; }
    public int getClientID() {return this.clientID;}
    public int getTimesOrdered() {return this.timesOrdered;}
    public void incrementTimesOrdered() {this.timesOrdered++;}

    public static List<Client> getClientList(){ return clientList;}
    public static void setClientList(List<Client> list){
        clientList=list;
    }
    public boolean clientRegister(String clientUsername, String clientPassword){
        for(Client client : clientList){
            if(client.getClientUsername().equals(clientUsername) && client.getClientPassword().equals(clientPassword))
                return false;
        }
        this.clientUsername=clientUsername;
        this.clientPassword=clientPassword;
        this.clientID=++clientCounter;
        clientList.add(this);
        return true;
    }
    public static boolean clientLogIn( String clientUsername, String clientPassword){
        for(Client client : clientList){
            if( client.getClientUsername().equals(clientUsername)  && client.getClientPassword().equals(clientPassword))
                return true;
        }
        return false;
    }

    public String toString(){
        return "Clientul cu numarul "+this.clientID+": username= "+this.clientUsername+"      password="+this.clientPassword;
    }
}
