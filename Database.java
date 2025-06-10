package oopprojecthalilkayra;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    User user = null;
    ArrayList<Product> marketplace = null;

    PreparedStatement pst = null;
    String query = null;
    ResultSet rs = null;
    DatabaseConnection dbc = null;
    static String usernameOfActiveUser = null;



    public User getUser(String username) {
        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        query = "SELECT * FROM users Where username = ?  ";

        try {
            pst = dbc.getCn().prepareStatement(query);
            pst.setString(1, username);

            rs = pst.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String homeAddress = rs.getString("homeAddress");
                String workAddress = rs.getString("workAddress");

                java.sql.Date sqlBirthDate = rs.getDate("dateOfBirth");
                java.util.Date dateOfBirth = new java.util.Date(sqlBirthDate.getTime());

                user = new User(username, name, surname, dateOfBirth, password, email, homeAddress, workAddress);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public boolean addUser(String username, String name, String surname, String email, String password,
            String homeAddress, String workAddress, java.util.Date dateOfBirth) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        query = "INSERT INTO users (username, name, surname, email, password, homeAddress, workAddress, dateOfBirth) VALUES (?,?,?,?,?,?,?,?)";
        java.sql.Date sqlDate = new java.sql.Date(dateOfBirth.getTime());

        try {
            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, name);
            pst.setString(3, surname);
            pst.setString(4, email);
            pst.setString(5, password);
            pst.setString(6, homeAddress);
            pst.setString(7, workAddress);
            pst.setDate(8, sqlDate);

            pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }



    public ArrayList<Product> getMarketplace() {

        marketplace = new ArrayList<>();

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        query = "SELECT * FROM products";
        try {
            pst = dbc.getCn().prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String color = rs.getString("color");
                String category = rs.getString("category");
                int stock = rs.getInt("stock");
                double weight = rs.getDouble("weight");
                String description = rs.getString("description");
                double price = rs.getDouble("price");

                marketplace.add(new Product(id, name, color, category, stock,
                        weight, description, price));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return marketplace;
    }

    public void getActiveUserCart(User user) {

        try {
            dbc = new DatabaseConnection();
            dbc.setCn(dbc.getConnection());

            String productName = null;
            String productColor = null;

            query = "SELECT * FROM cart WHERE usernameOfOwner = ?";

            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, user.getUsername());

            rs = pst.executeQuery();
            while (rs.next()) {
                productName = rs.getString("productName");
                productColor = rs.getString("productColor");

                if (OopProjectHalilKayra.getProductIndexinMarketplace(productName, productColor) != -1) {
                    user.getCart().add(
                            OopProjectHalilKayra.marketplace.get(
                                    OopProjectHalilKayra.getProductIndexinMarketplace(productName, productColor)
                            ));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getActiveUserFavorites(User user) {

        try {
            dbc = new DatabaseConnection();
            dbc.setCn(dbc.getConnection());

            String productName = null;
            String productColor = null;

            query = "SELECT * FROM favoriteProducts WHERE usernameOfOwner = ?";

            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, user.getUsername());

            rs = pst.executeQuery();

            while (rs.next()) {

                productName = rs.getString("productName");
                productColor = rs.getString("productColor");

                user.getFavoriteProducts().add(OopProjectHalilKayra.marketplace.get(
                        OopProjectHalilKayra.getProductIndexinMarketplace(productName, productColor)));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getActiveUserCreditCards(User user) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        try {

            query = "SELECT * FROM creditCards WHERE usernameOfUser = ?";

            pst = dbc.getCn().prepareStatement(query);
            pst.setString(1, user.getUsername());

            rs = pst.executeQuery();

            while (rs.next()) {

                String usernameOfUser = rs.getString("usernameOfUser");
                String creditCardNumber = rs.getString("creditCardNumber");
                String creditCardOwner = rs.getString("creditCardOwner");
                String securityCode = rs.getString("securityCode");
                java.sql.Date sqlExpiration = rs.getDate("expirationDate");
                java.util.Date expirationDate = new java.util.Date(sqlExpiration.getTime());

                user.getCreditCards().add(new CreditCard(creditCardNumber, creditCardOwner,
                        securityCode, expirationDate));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getActiveUserOrderedProducts(User user) {

        try {

            dbc = new DatabaseConnection();
            dbc.setCn(dbc.getConnection());

            query = "SELECT * FROM orderedProducts WHERE usernameOfOwner = ?";

            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, user.getUsername());

            rs = pst.executeQuery();

            while (rs.next()) {

                String productName = rs.getString("productName");
                String productColor = rs.getString("productColor");
                int amount = rs.getInt("amount");
                String paymentCardNumber = rs.getString("paymentCardNumber");

                user.getProductsOrdered().getProducts().add(OopProjectHalilKayra.marketplace.get(
                        OopProjectHalilKayra.getProductIndexinMarketplace(productName, productColor)));

                user.getProductsOrdered().getAmount().add(amount);

                CreditCard paymentCard = OopProjectHalilKayra.activeUser.getCreditCardByCardNumber(paymentCardNumber);

                user.getProductsOrdered().setPaymentCard(paymentCard);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addToFavoritesDatabase(Product product) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        query = "INSERT INTO favoriteProducts (usernameOfOwner, productName, productColor) VALUES (?,?,?) ";

        try {
            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, usernameOfActiveUser);
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getProductColor());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeFromFavoritesDatabase(Product product) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        query = "DELETE FROM favoriteProducts WHERE usernameOfOwner = ? AND productName = ? AND productColor = ?";

        try {
            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, usernameOfActiveUser);
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getProductColor());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean addToCartDatabase(Product product, int amount) {

        if (getAmountForCartFromDatabase(product) > 0) {

            increaseCartAmountDatabase(product, amount);
            return true;
        } else {

            dbc = new DatabaseConnection();
            dbc.setCn(dbc.getConnection());

            query = "INSERT INTO cart (usernameOfOwner, productName, productColor, amount) VALUES (?,?,?,?) ";

            try {
                pst = dbc.getCn().prepareStatement(query);

                pst.setString(1, usernameOfActiveUser);
                pst.setString(2, product.getProductName());
                pst.setString(3, product.getProductColor());
                pst.setInt(4, amount);

                pst.executeUpdate();

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }

    }

    public void clearCartDatabase() {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        try {

            query = "DELETE FROM cart WHERE usernameOfOwner = ?";

            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, usernameOfActiveUser);

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getAmountForCartFromDatabase(Product product) {

        int amount = -1; // if this method return -1 that means an error occured

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        query = "SELECT amount FROM cart WHERE usernameOfOwner = ? AND productName = ? AND productColor = ?";

        try {
            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, usernameOfActiveUser);
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getProductColor());

            rs = pst.executeQuery();

            if (rs.next()) {
                amount = rs.getInt("amount");
                return amount;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return amount;
    }

    public void increaseCartAmountDatabase(Product product, int amount) {

        int newAmount;

        if (getAmountForCartFromDatabase(product) != -1) {

            newAmount = getAmountForCartFromDatabase(product) + amount;
        } else {
            return; //to stop method
        }

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        try {

            query = "UPDATE cart SET amount = ? WHERE usernameOfOwner = ? AND productName = ? AND productColor = ?";

            pst = dbc.getCn().prepareStatement(query);

            pst.setInt(1, newAmount);
            pst.setString(2, usernameOfActiveUser);
            pst.setString(3, product.getProductName());
            pst.setString(4, product.getProductColor());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean addCreditCard(CreditCard card) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        try {

            query = "INSERT INTO creditCards (usernameOfUser,creditCardNumber,creditCardOwner,securityCode,expirationDate) VALUES (?,?,?,?,?)";

            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, usernameOfActiveUser);
            pst.setString(2, card.getCardNumber());
            pst.setString(3, card.getCardOwnerName());
            pst.setString(4, card.getSecurityCode());

            java.sql.Date sqlExpirationDate = new java.sql.Date(card.getExpirationDate().getTime());
            pst.setDate(5, sqlExpirationDate);

            pst.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean removeCreditCard(CreditCard card) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        try {

            query = "DELETE FROM creditCards WHERE creditCardNumber = ?";

            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, card.getCardNumber());

            pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }

    }

    public boolean addOrderToDatabase(Order order) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        query = "INSERT INTO orderedProducts (usernameOfOwner, productName, productColor, amount, paymentCardNumber) VALUES (?, ?, ?, ?, ?)";

        try {
            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, order.getOrderingUser().getUsername());
            pst.setString(2, order.getOrderedProduct().getProductName());
            pst.setString(3, order.getOrderedProduct().getProductColor());
            pst.setInt(4, order.getOrderAmount());
            pst.setString(5, order.getPaymentCard().getCardNumber());

            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean decreaseMarketplaceStockDatabase(Product product, int amount) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        int newAmount;

        try {

            if (isProductExist(product)) {

                newAmount = product.getProductStock() - amount;

                query = "UPDATE products SET stock = ? WHERE name = ? AND color = ? ";

                pst = dbc.getCn().prepareStatement(query);

                pst.setInt(1, newAmount);
                pst.setString(2, product.getProductName());
                pst.setString(3, product.getProductColor());

                pst.executeUpdate();
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean isProductExist(Product product) {

        dbc = new DatabaseConnection();
        dbc.setCn(dbc.getConnection());

        try {

            query = "SELECT * FROM products WHERE name = ? AND color = ?";

            pst = dbc.getCn().prepareStatement(query);

            pst.setString(1, product.getProductName());
            pst.setString(2, product.getProductColor());

            rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}