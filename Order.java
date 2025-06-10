package oopprojecthalilkayra;


public class Order {

    //Sepetteki her ürün için ayrı bir Order objesi oluşturulacak
    
    //okey bu class'ta sorun yok bence 
    
    private User orderingUser;
    private Product orderedProduct;
    private CreditCard paymentCard;
    private int orderAmount;
    
    // constructor
    public Order(User user, Product orderedProduct, int amount, CreditCard paymentCard){
        this.orderingUser = user;
        this.orderedProduct = orderedProduct;
        this.orderAmount = amount;
        this.paymentCard = paymentCard;
        
    }
    
    // getters and setters
    public User getOrderingUser() {
        return orderingUser;
    }
    
    public void setOrderingUser(User orderingUser) {
        this.orderingUser = orderingUser;
    }
    
    public Product getOrderedProduct() {
        return orderedProduct;
    }
    
    public void setOrderedProduct(Product orderedProduct) {
        this.orderedProduct = orderedProduct;
    }
    
    public CreditCard getPaymentCard() {
        return paymentCard;
    }
    
    public void setPaymentCard(CreditCard paymentCard) {
        this.paymentCard = paymentCard;
    }
    
    public int getOrderAmount() {
        return orderAmount;
    }
    
    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }
    
    // order class'ındaki method
    public void createOrder(){
        
        this.getOrderingUser().confirmOrder(this);
        
    }
    
}