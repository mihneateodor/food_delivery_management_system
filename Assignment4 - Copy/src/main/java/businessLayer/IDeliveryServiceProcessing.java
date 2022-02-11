package businessLayer;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
/**
 * Aceasta este interfata care contine metodele pentru administrator: importarea produselor, administrarea produselor si alimentelor din meniu, generarea rapoartelor, si
 * pentru client: creearea unei noi comenzi, generarea unei facturi si cautarea in functie de anumite criterii.
 *
 * @author Circiu Mihnea Teodor {@literal <circiumihnea @ gmail.com>}
 * @since May 24th 2021
 */
public interface IDeliveryServiceProcessing {
    //administrator
    /**
     * Metoda care importa alimentele de baza din fisierul "products.csv" si le adauga in lista pentru meniu si in lista pentru alimente de baza.
     * @Invariant invariantMethod()==true
     * @throws IOException Nu se pot importa corect produsele din fisierul "products.csv".
     */
    void importInitialSetOfProducts() throws IOException;
    /**
     * Metoda care adauga un nou aliment de baza in meniu.
     * @Invariant invariantMethod() == true
     * @Precondition title!=null
     * @Precondition ratingString!=null
     * @Precondition caloriesString!=null
     * @Precondition proteinString!=null
     * @Precondition fatString!=null
     * @Precondition sodiumString!=null
     * @Precondition priceString!=null
     * @PostCondition menuItem!=null
     * @param title Numele produsului de baza.
     * @param ratingString  "Rating"-ul produsului.
     * @param caloriesString Numarul de "calories" al produsului.
     * @param proteinString Numarul de "proteins" al produsului.
     * @param fatString Numarul de "fats" al produsului.
     * @param sodiumString Cat "sodium" contine produsul.
     * @param priceString "Price"-ul produsului de baza.
     */
    void addNewBaseProductToMenuItems(String title, String ratingString, String caloriesString, String proteinString, String fatString, String sodiumString, String priceString);
    /**
     * Metoda care scoate din meniu un anumit produs/aliment.
     * @Invariant invariantMethod() == true
     * @Precondition index>=0
     * @param index Pozitia din lista a produsului de retras.
     */
    void removeMenuItem(int index);
    /**
     * Metoda care modifica datele unui produs de baza din meniu.
     * @Invariant invariantMethod() == true
     * @Precondition index>=0
     * @Precondition title!=null
     * @Precondition ratingString!=null
     * @Precondition caloriesString!=null
     * @Precondition proteinString!=null
     * @Precondition fatString!=null
     * @Precondition sodiumString!=null
     * @Precondition priceString!=null
     * @param index Pozitia din lista a produsului de modificat.
     * @param title Numele produsului de baza.
     * @param ratingString "Rating"-ul produsului.
     * @param caloriesString Numarul de "calories" al produsului.
     * @param proteinString Numarul de "proteins" al produsului.
     * @param fatString Numarul de "fats" al produsului.
     * @param sodiumString Cat "sodium" contine produsul.
     * @param priceString "Price"-ul produsului de baza.
     */
    void modifyBaseProductFromMenuItems(int index, String title, String ratingString, String caloriesString, String proteinString, String fatString, String sodiumString, String priceString);
    /**
     * Metoda care adauga un produs compus in meniu.
     * @Invariant invariantMethod() == true
     * @Precondition title!=null
     * @Precondition list!=null
     * @Precondition list.size()>=1
     * @param title Numele produsului.
     * @param list Lista de alimente din produs.
     */
    void addNewCompositeProductToMenuItems(String title, List<MenuItem> list);
    /**
     * Metoda care scoate dintr-un produs compus, un anumit produs de baza.
     * @Invariant invariantMethod() == true
     * @Precondition indexMenuItems>=0
     * @Precondition indexCompositeProduct>=0
     * @param indexMenuItems Pozitia din lista de meniu al produsului compus.
     * @param indexCompositeProduct Pozitia din lista de alimente din produsul compus de unde se va scoate un aliment.
     */
    void removeProductFromComposite(int indexMenuItems, int indexCompositeProduct);
    /**
     * Metoda care adauga intr-un produs compus, unul sau mai multe produse de baza.
     * @Invariant invariantMethod() == true
     * @Precondition indexMenuItems>=0
     * @Precondition compositeProductList!=null
     * @param indexMenuItems Pozitia din lista de meniu al produsului compus.
     * @param compositeProductList Lista de produse de adaugat in produsul compus.
     */
    void addNewProductToComposite(int indexMenuItems, List<MenuItem> compositeProductList );
    /**
     * Metoda care genereaza primul raport din cerintele temei.
     * @Invariant invariantMethod() == true
     * @Precondition hour1String!=null
     * @Precondition hour2String!=null
     * @param hour1String Ora de inceput pentru cautarea comenzilor.
     * @param hour2String Ora de sfarsit pentru cautarea comenzilor.
     * @throws Exception Ora incompatibila.
     */
    void generateReportTimeInterval(String hour1String, String hour2String) throws Exception;
    /**
     * Metoda care genereaza al doilea raport din cerintele temei.
     * @Invariant invariantMethod() == true
     * @Precondition numberOfTimesString!=null
     * @param numberOfTimesString De cate ori au fost comandate produsele.
     */
    void generateReportProductsMoreThanSpecifiedTimes(String numberOfTimesString);
    /**
     * Metoda care genereaza al treilea raport din cerintele temei.
     * @Invariant invariantMethod() == true
     * @Precondition numberOfTimesString!=null
     * @Precondition minimumValueString!=null
     * @param numberOfTimesString De cate ori a comandat clientul.
     * @param minimumValueString Valoarea minima a comenzii.
     */
    void generateReportClientsOrderedMoreAndValue(String numberOfTimesString, String minimumValueString );
    /**
     * Metoda care genereaza al patrulea raport din cerintele temei.
     * @Invariant invariantMethod() == true
     * @Precondition yearString!=null
     * @Precondition monthString!=null
     * @Precondition dayString!=null
     * @param yearString Anul in care s-a procesat comanda.
     * @param monthString Luna in care s-a procesat comanda.
     * @param dayString Ziua in care s-a procesat comanda.
     */
    void generateReportProductDayNumberOfTimes(String yearString, String monthString, String dayString);
    /**
     * Metoda care creeaza o factura pentru o anumita comanda.
     * @Invariant invariantMethod() == true
     * @Precondition order!=null
     * @Precondition list!=null
     * @param order Comanda.
     * @param list Lista de alimente din comanda.
     */
    void generateBill(Order order, List<MenuItem> list);
    //client
    /**
     * Metoda care creeaza o comanda pentru un anumit client (clientId) dintr-o anumita zi (date), avand o lista de alimente din meniu.
     * @Invariant invariantMethod() == true
     * @Precondition clientId>=0
     * @Precondition list!=null
     * @Precondition date.getYear()>2019
     * @Precondition 2022>date.getYear()
     * @Precondition date.getMonth()>0
     * @Precondition 13>date.getMonth()
     * @Precondition date.getDay()>0
     * @Precondition 32>date.getDay()
     * @param clientId ID-ul clientului care solicita comanda.
     * @param date Data in care s-a efectuat comanda.
     * @param list Lista de produse din comanda.
     */
    void createOrder(int clientId, Date date, List<MenuItem> list);
    /**
     * Metoda de cautare in meniu in functii de anumite criterii.
     * @Invariant invariantMethod() == true
     * @param title Numele produsului.
     * @param ratingString "Rating"-ul produsului.
     * @param caloriesString Numarul de "calories" al produsului.
     * @param proteinString Numarul de "proteins" al produsului.
     * @param fatString Numarul de "fats" al produsului.
     * @param sodiumString Cat "sodium" contine produsul.
     * @param priceString "Price"-ul produsului de baza.
     * @return Lista de produse care prezinta criteriile cerute.
     */
    List<MenuItem> searchByCriteria(String title, String ratingString, String caloriesString, String proteinString, String fatString, String sodiumString, String priceString);
    /**
     * Get-er pentru lista de produse din meniu.
     * @Invariant invariantMethod() == true
     * @return Lista de produse din meniu
     */
    List<MenuItem> getMenuItems();
    /**
     * Get-er pentru lista cu produse de baza.
     * @Invariant invariantMethod() == true
     * @return Lista de produse de baza.
     */
    List<MenuItem> getBaseItems();
    /**
     * Get-er pentru comenzi si listele de produse pe care le au.
     * @Invariant invariantMethod() == true
     * @return Tabela de dispersie Comanda-Lista de produse.
     */
    HashMap<Order,List<MenuItem>> getOrders();
    /**
     * Get-er pentru lista de comenzi.
     * @Invariant invariantMethod() == true
     * @return Lista de comenzi
     */
    List<Order> getOrderList();
    /**
     * Set-er pentru lista de comenzi-produse.
     * @Invariant invariantMethod() == true
     * @param list HashMap-ul de comenzi.
     */
    void setOrders(HashMap<Order,List<MenuItem>> list);
    /**
     * Set-er pentru lista de produse.
     * @Invariant invariantMethod() == true
     * @param list Lista de produse.
     */
    void setMenuItems(List<MenuItem> list);
    /**
     * Set-er pentru lista de produse de baza.
     * @Invariant invariantMethod() == true
     * @param list Lista de produse
     */
    void setBaseProducts(List<MenuItem> list);
    /**
     * Set-er pentru lista de comenzi.
     * @Invariant invariantMethod() == true
     * @param list Lista de comenzi.
     */
    void setOrderList(List<Order> list);
}
