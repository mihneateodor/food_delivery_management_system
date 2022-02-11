package businessLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseProduct extends MenuItem  {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    private List<BaseProduct> baseProductsList=new ArrayList<>();

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price){
        this.title=title;
        this.calories=calories;
        this.protein=protein;
        this.fat=fat;
        this.price=price;
        this.rating=rating;
        this.sodium=sodium;
    }
    public BaseProduct() throws IOException{
        BufferedReader buffer=new BufferedReader(new FileReader("products.csv"));
        List<String[]> rows = buffer.lines()
                .skip(1)
                .map(s->s.split(","))
                .collect(Collectors.toList());
        for(String[] mesaj : rows){
            String title=mesaj[0];
            double rating=Double.parseDouble(mesaj[1]);
            int calories = Integer.parseInt(mesaj[2]);
            int protein = Integer.parseInt(mesaj[3]);
            int fat = Integer.parseInt(mesaj[4]);
            int sodium = Integer.parseInt(mesaj[5]);
            int price = Integer.parseInt(mesaj[6]);
            baseProductsList.add(new BaseProduct(title,rating,protein,calories,fat,sodium,price));
        }
        baseProductsList=eliminateDuplicates();
    }
    public List<BaseProduct> eliminateDuplicates(){
        List<BaseProduct> listWithoutDuplicates= new ArrayList<>();
        List<String> titles= new ArrayList<>();
        for(BaseProduct product : baseProductsList){
            if(!titles.contains(product.getTitle())){
                titles.add(product.getTitle());
                listWithoutDuplicates.add(product);
            }
        }
        return listWithoutDuplicates;
    }

    public String productToString(){
        return this.getTitle()+" "+this.getRating()+" "+this.getCalories()+" "+this.getProtein()+" "+this.getFat()+" "+this.getSodium()+" "+this.computePrice();
    }

    public List<BaseProduct> getBaseProducts(){
        return this.baseProductsList;
    }
    public MenuItem getMenuItem(int menuItemIndex){
        return baseProductsList.get(menuItemIndex);
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public int getProtein() {
        return protein;
    }
    public void setProtein(int protein) {
        this.protein = protein;
    }
    public int getFat() {
        return fat;
    }
    public void setFat(int fat) {
        this.fat = fat;
    }
    public int getSodium() {
        return sodium;
    }
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }
    public int computePrice() { return this.price; }
    public void setPrice(int price){ this.price=price; }
    public void incrementTimesOrdered() {this.timesOrdered++;}
    public int getTimesOrdered() {return this.timesOrdered;}

}
