package oopprojecthalilkayra;

import java.util.*;

// user class ı içerisinde kullanıcı bilgileri ile kullanıcının sahip olduğu 
public class User {

    private String username;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String password;
    private String email;
    private String homeAddress;
    private String workAddress;
    private ProductsOrdered productsOrdered;
    private ArrayList<Product> favoriteProducts;
    private ArrayList<CreditCard> creditCards;
    private ArrayList<Product> cart;

    public User(String username, String name, String surname, Date dateOfBirth, String password, String email,
            String homeAddress, String workAddress) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.productsOrdered = new ProductsOrdered();
        this.favoriteProducts = new ArrayList<>();
        this.creditCards = new ArrayList<>();
        this.cart = new ArrayList<>();
    }

    //getters setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public ProductsOrdered getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(ProductsOrdered productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public ArrayList<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(ArrayList<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(ArrayList<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }

    //kredi kartı ekleme çıkarma
    public boolean addCreditCard(CreditCard creditCard) {

        if (!this.creditCards.contains(creditCard)) {

            Database db = new Database();

            getCreditCards().add(creditCard);
            db.addCreditCard(creditCard);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeCreditCard(CreditCard creditCard) {

        if (this.getCreditCards().contains(creditCard)) {

            Database db = new Database();

            getCreditCards().remove(creditCard);
            db.removeCreditCard(creditCard);
            return true;
        } else {
            return false;
        }

    }

    // favori ekleme ve çıkarma methodları
    public boolean addToFavorites(Product product) {
        if (!favoriteProducts.contains(product)) {

            getFavoriteProducts().add(product);

            Database db = new Database();
            db.addToFavoritesDatabase(product);

            return true;
        } else {
            return false;
        }

    }

    public boolean removeFromFavorites(Product product) {

        if (getFavoriteProducts().contains(product)) {
            getFavoriteProducts().remove(product);

            Database db = new Database();
            db.removeFromFavoritesDatabase(product);

            return true;
        } else {
            return false;
        }

    }

    // sepete ekleme çıkarma
    public boolean addToCart(Product product) {

        Database db = new Database();

        if (db.addToCartDatabase(product, 1)) {
            OopProjectHalilKayra.activeUser.getCart().add(product);
            return true;
        } else {
            return false;
        }

    }

    public void confirmOrder(Order order) {

        Database db = new Database();

        this.getProductsOrdered().getProducts().add(order.getOrderedProduct());
        this.getProductsOrdered().getAmount().add(order.getOrderAmount());
        db.addOrderToDatabase(order);

        order.getOrderedProduct().decreaseStock(order.getOrderAmount());
        db.decreaseMarketplaceStockDatabase(order.getOrderedProduct(), order.getOrderAmount());

    }

    public CreditCard getCreditCardByCardNumber(String cardNumber) {

        for (CreditCard card : this.getCreditCards()) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }
    
}