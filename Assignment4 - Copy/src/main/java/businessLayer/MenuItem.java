package businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    int timesOrdered=0;

    public void add(MenuItem menuItem){
        throw new UnsupportedOperationException();
    }
    public void remove(MenuItem menuItem){
        throw new UnsupportedOperationException();
    }
    public MenuItem getMenuItem(int menuItemIndex){
        throw new UnsupportedOperationException();
    }
    public String getTitle(){
        throw new UnsupportedOperationException();
    }
    public void setTitle(String title){
        throw new UnsupportedOperationException();
    }
    public double getRating(){
        throw new UnsupportedOperationException();
    }
    public void setRating(double rating){
        throw new UnsupportedOperationException();
    }
    public int getCalories(){
        throw new UnsupportedOperationException();
    }
    public void setCalories(int calories){
        throw new UnsupportedOperationException();
    }
    public int getProtein(){
        throw new UnsupportedOperationException();
    }
    public void setProtein(int protein){
        throw new UnsupportedOperationException();
    }
    public int getFat(){
        throw new UnsupportedOperationException();
    }
    public void setFat(int fat){
        throw new UnsupportedOperationException();
    }
    public int getSodium(){
        throw new UnsupportedOperationException();
    }
    public void setSodium(int sodium){
        throw new UnsupportedOperationException();
    }
    public int computePrice(){
        throw new UnsupportedOperationException();
    }
    public void setPrice(int price){
        throw new UnsupportedOperationException();
    }
    public int getPrice(){
        throw new UnsupportedOperationException();
    }
    public String productToString(){
        throw new UnsupportedOperationException();
    }
    public void incrementTimesOrdered(){
        throw new UnsupportedOperationException();
    }
    public int getTimesOrdered(){ throw new UnsupportedOperationException();}
}
