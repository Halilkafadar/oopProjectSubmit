package oopprojecthalilkayra;


public class Product {
    
    // combination of productName and productColor is unique for every product object 
    
    private int id;
    private String productName;  
    private String productColor;
    private String productCategory;
    private int productStock;
    private double productWeight;
    private String productDescription;
    private double price;
    
    
    // constructor
    public Product(int id,String productName, String productColor, String productCategory,
                   int productStock, double productWeight, String productDescription, double price) {
         
        this.id = id;
        this.productName = productName;
        this.productColor = productColor;
        this.productCategory = productCategory;
        this.productStock = productStock;
        this.productWeight = productWeight;
        this.productDescription = productDescription;
        this.price = price;
    }
    
    // getters and setters
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductColor() {
        return productColor;
    }
    
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
    
    public String getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    
    public int getProductStock() {
        return productStock;
    }
    
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
    
    public double getProductWeight() {
        return productWeight;
    }
    
    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }
    
    public String getProductDescription() {
        return productDescription;
    }
    
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isStockEnough(int amount){
        return this.getProductStock()>= amount;
    }
    
    public boolean decreaseStock(int amount){
        
        if(this.isStockEnough(amount)){
            
            this.setProductStock(this.getProductStock() - amount);
            return true;
            
        } else{
            return false;
        }
        
        
    }

}