package businessLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {
    private List<MenuItem> compositeProducts;

    private final String compositeTitle;
    private int price;

    public CompositeProduct(String compositeTitle){
        this.compositeTitle= compositeTitle;
        compositeProducts=new ArrayList<>();
    }

    public void add(MenuItem menuItem){
        compositeProducts.add(menuItem);
    }
    public void remove(MenuItem menuItem){
        compositeProducts.remove(menuItem);
    }
    public String getTitle(){
        return this.compositeTitle;
    }
    public MenuItem getMenuItem(int menuItemIndex){
        return compositeProducts.get(menuItemIndex);
    }

    public String productToString(){
        String mesaj=compositeTitle+":\n";
        for(MenuItem product: compositeProducts){
            mesaj=mesaj+product.productToString()+"\n";
        }
        return mesaj;
    }

    public int computePrice(){
        int priceComposite=0;
        for(MenuItem product : compositeProducts){
            priceComposite+= product.computePrice();
        }
        this.price=priceComposite;
        return this.price;
    }
    public void incrementTimesOrdered() {this.timesOrdered++;}
    public int getTimesOrdered() {return this.timesOrdered;}
}
