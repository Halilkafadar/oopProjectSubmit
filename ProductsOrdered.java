package oopprojecthalilkayra;

import java.util.ArrayList;


public class ProductsOrdered {
    
    private ArrayList<Product> products= null;
    private ArrayList<Integer> amount = null;
    private CreditCard paymentCard = null;
    

    public ProductsOrdered() {
        this.products = new ArrayList<>();
        this.amount = new ArrayList<>();
        
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Integer> getAmount() {
        return amount;
    }

    public void setAmount(ArrayList<Integer> amount) {
        this.amount = amount;
    }

    public CreditCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(CreditCard paymentCard) {
        this.paymentCard = paymentCard;
    }

}
