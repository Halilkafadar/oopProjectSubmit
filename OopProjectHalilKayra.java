package oopprojecthalilkayra;

import java.awt.Point;
import java.util.ArrayList;


public class OopProjectHalilKayra {
    
    public static User activeUser = null;
    public static ArrayList<Product> marketplace = null;
    
    
    public static void main(String[] args) {
        
        Database db = new Database();
        marketplace = db.getMarketplace();

        
        CommandLineUi.start();
        
    }
    
    public static int getProductIndexinMarketplace(String productName, String productColor){
        int productId =-1;
        
        for(int i = 0; i < marketplace.size(); i++){
            
            if(marketplace.get(i).getProductName().equals(productName) &&
                    marketplace.get(i).getProductColor().equals(productColor)){
                productId = i;
            } else{
            }
            
        }
        
        return productId;
    }

}