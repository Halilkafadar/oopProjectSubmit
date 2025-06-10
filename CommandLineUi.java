package oopprojecthalilkayra;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Scanner;

public class CommandLineUi {

    private static final Database db = new Database();
    private static final Scanner input = new Scanner(System.in);
    

    public static void start() {

        System.out.println("Weloceme\n\n1 - Register\n2 - Login");
        int inputVar = input.nextInt();
        input.nextLine();

        switch (inputVar) {
            case 1 -> register();
            case 2 -> login();
            default -> {
                System.out.println("Invalid input");
                start();  // Geçersiz girişte tekrar menüyü göster
            }
        }

    }

    private static void login() {

        System.out.println("\nLogin menu\nPlease enter your username");
        String usernameInput = input.nextLine();

        System.out.println("\nPlease enter your password");
        String passwordInput = input.nextLine();

        System.out.println("please wait...");

        if (AuthManager.login(usernameInput, passwordInput)) {
            System.out.println("Welcome " + OopProjectHalilKayra.activeUser.getName());
            db.getMarketplace();
            Database.usernameOfActiveUser = OopProjectHalilKayra.activeUser.getUsername();
            mainMenu();
        } else {
            System.out.println("Your username or password incorrect.");
            tryAgainLogin();
        }

    }

    private static void tryAgainLogin() {

        System.out.println("1 - Try Again\n 2 - Return");
        int inputVar = input.nextInt();
        input.nextLine();

        switch (inputVar) {
            case 1 -> login();
            case 2 -> start();
            default -> {
                System.out.println("You should input 1 or 2!!");
                tryAgainLogin();
            }
        }

    }

    private static void register() {

        System.out.println("\nRegiste Menu\n");

        String username = takeUsernameForRegister();

        System.out.println("\nPlease enter your name");
        String name = input.nextLine();

        System.out.println("\nPlease enter your surname");
        String surname = input.nextLine();

        System.out.println("\nPlease enter your email");
        String email = input.nextLine();

        System.out.println("\nPlease enter your password");
        String password = input.nextLine();

        System.out.println("\nPlease enter your home address");
        String homeAddress = input.nextLine();

        System.out.println("\nPlease enter your workd address");
        String workAddress = input.nextLine();

        java.util.Date birthDate = takeInputBirthDate();

        if (AuthManager.register(username, name, surname, email, password,
                homeAddress, workAddress, birthDate)) {

            System.out.println("Register completed");
            login();
        } else {
            System.out.println("An error occured do you want try again\n1 - yes\n2 - no");
            String inputVar = input.nextLine();

            if (inputVar.equals("1")) {
                register();
            } else {
                start();
            }

        }

    }

    private static String takeUsernameForRegister() {

        System.out.println("Please enter your username");
        String username = input.nextLine();

        if (AuthManager.isUserExist(username)) {
            System.out.println("\nThis username is invalid");
            takeUsernameForRegister();
        }
        if (username.contains(" ")) {
            System.out.println("Your username can not be involved space( ) character");
            takeUsernameForRegister();
        }

        return username;

    }

    private static java.util.Date takeInputBirthDate() {

        SimpleDateFormat birthDateStr = new SimpleDateFormat("dd-MM-yyyy");
        
        birthDateStr.setLenient(false); // date check

        while (true) {
            System.out.print("\nPlease enter your birth date as (dd-MM-yyyy): ");
            String dateStr = input.nextLine();

            try {
                java.util.Date realDate = birthDateStr.parse(dateStr);
                return realDate;
            } catch (ParseException e) {
                System.out.println("Invalid format or non-existent date.\nPlease try again.");
            }
        }
    }

    private static void mainMenu() {

        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Show all products");
        System.out.println("2. Show product by category");
        System.out.println("3. show cart");
        System.out.println("4. Show favorites");
        System.out.println("5. Show credit cards ");
        System.out.println("6. Add credit Card");
        System.out.println("0. Log out and exıt");

        System.out.print("\nYour choise: ");
        while (true) {
            int choice = input.nextInt();
            input.nextLine(); // Buffer temizle

            switch (choice) {
                case 1 -> showAllProducts();
                case 2 -> showProductsByCategory();
                case 3 -> showCart();
                case 4 -> showFavorites();
                case 5 -> showCreditCards();
                case 6 -> addCreditCard();
                case 0 -> {
                    System.out.println("\nLogging out........");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid selection");
                    mainMenu();
                }
            }
        }

    }

    private static void showAllProducts() {

        for (Product product : OopProjectHalilKayra.marketplace) {

            System.out.println(
                    "ID: " + product.getId()
                    + " | " + product.getProductName()
                    + " | Price:" + product.getPrice()
                    + " | Stock: " + product.getProductStock()
                    + " | " + product.getProductCategory()
            );
        }

        showProductDetail();

    }

    private static void showProductDetail() {
        System.out.print("Please enter porduct Id: ");
        System.out.println("Enter 0 for return main menu");
        int productId = input.nextInt();
        input.nextLine();

        if (productId == 0) {
            mainMenu();
        }

        Product product = getProductbyProductId(productId);
        if (product != null) {
            System.out.println("\n=== Product Detail ===");
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Description: " + product.getProductDescription());
            System.out.println("Price: ₺" + product.getPrice());
            System.out.println("Category: " + product.getProductCategory());
            System.out.println("Stock: " + product.getProductStock());
        } else {
            System.out.println("Invalid product id!");
            showProductDetail();
        }

        takeInputForProduct(product);

    }

    private static Product getProductbyProductId(int productId) {

        for (Product product : OopProjectHalilKayra.marketplace) {
            if (product.getId() == productId) {
                return product;
            }
        }

        return null;
    }

    private static void takeInputForProduct(Product product) {

        System.out.println("1 - add to favorites\n2 - add to cart");
        int inputVar = input.nextInt();
        input.nextLine();

        switch (inputVar) {
            case 1 -> {
                OopProjectHalilKayra.activeUser.addToFavorites(product);
                System.out.println(product.getProductName() + " added to your favorites");
                mainMenu();
            }
            case 2 -> {
                OopProjectHalilKayra.activeUser.addToCart(product);
                System.out.println(product.getProductName() + " added to your cart");
                mainMenu();
            }
            default -> {
                System.out.println("invalid input\nReturnin to the main menu");
                mainMenu();
            }
        }

    }

    private static void showProductsByCategory() {

        System.out.println("\nPlease enter a product category");
        String category = input.nextLine();

        for (Product product : OopProjectHalilKayra.marketplace) {
            if (product.getProductCategory().equalsIgnoreCase(category)) {
                System.out.println(
                        "ID: " + product.getId()
                        + " | " + product.getProductName()
                        + " | price:" + product.getPrice()
                        + " | Stock: " + product.getProductStock()
                        + " | " + product.getProductCategory()
                );
            }

        }

        showProductDetail();

    }

    private static void showCart() {

        for (Product product : OopProjectHalilKayra.activeUser.getCart()) {
            System.out.println(
                    "ID: " + product.getId()
                    + " | " + product.getProductName()
                    + " | price:" + product.getPrice()
                    + " | Stock: " + product.getProductStock()
                    + " | " + product.getProductCategory()
            );

        }

        System.out.println("Total price:" + getTotalPriceOfCart());

        System.out.println("1 - check out cart\n2 - clear cart\n3 - return to main menu");
        int inputVar = input.nextInt();
        input.nextLine();

        switch (inputVar) {
            case 1 -> checkOutCart();
            case 2 -> clearCart();
            case 3 -> mainMenu();
            default -> {
                System.out.println("Invalid input");
                showCart();
            }

        }

    }

    private static void clearCart() {

        db.clearCartDatabase();
        OopProjectHalilKayra.activeUser.getCart().clear();
        System.out.println("Your cart cleared");

    }

    private static void checkOutCart() {

        if (isStockEnoughForCart()) {

            java.util.Date now = new java.util.Date();

            CreditCard cardForCheckOut = selectCreditCardForCheckOut();

            if (now.before(cardForCheckOut.getExpirationDate())) {

                for (Product product : OopProjectHalilKayra.activeUser.getCart()) {

                    Order order = new Order(OopProjectHalilKayra.activeUser, product, 1, cardForCheckOut);
                    order.createOrder();

                }
                
                System.out.println("Your orders created");

                clearCart();

            } else {
                System.out.println("Your card's expiration date has passed.");
                mainMenu();
            }

        } else {
            System.out.println("Not enogh stock for your cart");
            mainMenu();
        }

    }

    private static boolean isStockEnoughForCart() {

        for (Product product : OopProjectHalilKayra.activeUser.getCart()) {

            if (product.getProductStock() <= 0) {
                return false;
            }

        }

        return true;

    }

    private static CreditCard selectCreditCardForCheckOut() {

        for (int i = 1; i <= OopProjectHalilKayra.activeUser.getCreditCards().size(); i++) {

            System.out.println(i + " - " + OopProjectHalilKayra.activeUser.getCreditCards().get(i - 1).getCardNumber());

        }

        System.out.println("Please select credit card");
        int inputVar = input.nextInt();
        input.nextLine();

        if (inputVar >= 0 && inputVar <= OopProjectHalilKayra.activeUser.getCreditCards().size()
                && OopProjectHalilKayra.activeUser.getCreditCards().get(inputVar - 1) != null) {
            return OopProjectHalilKayra.activeUser.getCreditCards().get(inputVar - 1);
        } else {
            System.out.println("Invalid selection");
            mainMenu();
        }

        return null;

    }

    private static void showFavorites() {

        for (Product product : OopProjectHalilKayra.activeUser.getFavoriteProducts()) {
            System.out.println(
                    "ID: " + product.getId()
                    + " | " + product.getProductName()
                    + " | Price" + product.getPrice()
                    + " | Stock: " + product.getProductStock()
                    + " | " + product.getProductCategory()
            );

        }

        System.out.println("1 - Add to cart \n2 - Remove from favorites\n3 - clear favorites\n4 - return to main menu");
        int inputVar = input.nextInt();
        input.nextLine();

        switch (inputVar) {
            case 1 -> addToCartFromFavorites();
            case 2 -> removeFromFavorites();
            case 3 -> clearFavorites();
            case 4 -> mainMenu();
            default -> {
                System.out.println("Invalid input");
                showFavorites();
            }

        }

    }
    
    private static void clearFavorites(){
        
        for(Product product : OopProjectHalilKayra.activeUser.getFavoriteProducts()){
            
            db.removeFromFavoritesDatabase(product);
            
            //System.out.println(product.getProductName() + "removed from favoirtes");
            
        }
        
        OopProjectHalilKayra.activeUser.getFavoriteProducts().clear();
        
        System.out.println("Your favorites cleared");
        mainMenu();
        
        
    }

    private static void addToCartFromFavorites() {

        System.out.println("Please enter prdocut Id for add to cart");
        int inputVar = input.nextInt();
        input.nextLine();

        if (OopProjectHalilKayra.activeUser.getFavoriteProducts().contains(getProductbyProductId(inputVar))) {
            OopProjectHalilKayra.activeUser.addToCart(getProductbyProductId(inputVar));
            System.out.println("Selected product add to cart");
            mainMenu();
        } else {
            System.out.println("Selected product isn't in your favorites");
            mainMenu();
        }

    }

    private static void removeFromFavorites() {

        System.out.println("Please enter prdocut Id for remove from favorites");
        int inputVar = input.nextInt();
        input.nextLine();

        if (OopProjectHalilKayra.activeUser.getFavoriteProducts().contains(getProductbyProductId(inputVar))) {
            OopProjectHalilKayra.activeUser.removeFromFavorites(getProductbyProductId(inputVar));

            System.out.println("Selected product removed from your favorites");
            showFavorites();

        } else {
            System.out.println("Selected product isn't in your favorites");
            mainMenu();
        }

    }

    private static double getTotalPriceOfCart() {

        double totalPrice = 0;

        for (Product product : OopProjectHalilKayra.activeUser.getCart()) {

            totalPrice += product.getPrice();

        }

        return totalPrice;
    }

    private static void showCreditCards() {

        for (CreditCard card : OopProjectHalilKayra.activeUser.getCreditCards()) {
            System.out.println(
                    "\nCard Number: " + card.getCardNumber()
                    + " | Security Code: " + card.getSecurityCode()
                    + " | Expiration Date" + card.getExpirationDate().toString()
            );

        }

        System.out.println("\n1 - Remove credit card\n2 - Add credit card\n3 - Main menu");
        int inputVar = input.nextInt();
        input.nextLine();

        switch (inputVar) {
            case 1 -> removeCreditCard();
            case 2 -> addCreditCard();
            case 3 -> mainMenu();
        }

    }

    private static void removeCreditCard() {

        System.out.println("Pleaase enter card number for remove card");
        String cardNumber = input.nextLine();

        CreditCard cardForRemove = OopProjectHalilKayra.activeUser.getCreditCardByCardNumber(cardNumber);

        if (cardForRemove != null) {
            OopProjectHalilKayra.activeUser.removeCreditCard(cardForRemove);
            System.out.println("Selected credit card removed");
            mainMenu();

        } else {
            System.out.println("The credit card isn't exist");
            //    System.out.println("This credit card doesn't exist");
            mainMenu();
        }

    }

    private static void addCreditCard() {

        String cardNumber = takeCreditCardNumber();

        if (cardNumber == null) {
            System.out.println("An error occured while setting card number");
            mainMenu();
        }

        System.out.println("Please enter name of the card owner(Written on the card)");
        String cardOwnerName = input.nextLine();

        String securityCode = takeCardSecurityCode();

        if (securityCode == null) {
            System.out.println("An error occured while setting card security code");
            mainMenu();
        }

        java.util.Date expirationDate = takeExpirationDateForAddCard();

        if (OopProjectHalilKayra.activeUser.addCreditCard(new CreditCard(cardNumber, cardOwnerName, securityCode, expirationDate))) {

            System.out.println("Added to your credit Cards");
            mainMenu();
        } else {
            System.out.println("Something Went Wrong Returning to Main Menu...");
            mainMenu();
        }

    }

    private static String takeCreditCardNumber() {

        System.out.println("Please enter card number");
        String cardNumber = input.nextLine();

        if (cardNumber.length() != 16 || !cardNumber.matches("\\d{16}")) { // also check are all chasrs digit
            System.out.println("Card number should be exactly 16 digits");
            return takeCreditCardNumber();
        }

        return cardNumber;
    }

    private static String takeCardSecurityCode() {

        System.out.println("Please enter Security code");
        String securityCode = input.nextLine();

        if (securityCode.length() != 3) {
            System.out.println("Security code should be 3 digits");
            takeCardSecurityCode();
        } else {
            return securityCode;
        }

        return null;
    }

    private static java.util.Date takeExpirationDateForAddCard() {

        int yearOfExpiration = takeCreditMonth();

        int monthOfExpiration = takeCreditCardMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, yearOfExpiration);
        calendar.set(Calendar.MONTH, monthOfExpiration - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        java.util.Date expirationDate = calendar.getTime();

        return expirationDate;

    }

    private static int takeCreditCardMonth() {

        System.out.println("Please enter expiration date's MONTH");
        int monthOfExpiration = input.nextInt();
        input.nextLine();

        if (monthOfExpiration < 1 || monthOfExpiration > 12) {
            System.out.println("Please enter expiration date's MONTH shold be between 1 and 12 (including 1 and 12)");
            monthOfExpiration = takeCreditCardMonth();
        }

        return monthOfExpiration;

    }

    private static int takeCreditMonth() {

        System.out.println("Please enter expiration date's YEAR");
        int yearOfExpiration = input.nextInt();
        input.nextLine();

        if (String.valueOf(Math.abs(yearOfExpiration)).length() != 4) {
            System.out.println("expiration date's YEAR should be 4 digits");
            yearOfExpiration = takeCreditMonth();
        }

        return yearOfExpiration;

    }

}