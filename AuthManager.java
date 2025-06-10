package oopprojecthalilkayra;

public class AuthManager {

    public static boolean login(String username, String password) {

        Database db = new Database();

        User user = db.getUser(username);

        if (user != null && user.getPassword().equals(password)) {

            initializeActiveUser(user);

            return true;
        } else {
            return false;
        }
    }

    public static boolean register(String username, String name, String surname, String email, String password,
            String homeAddress, String workAddress, java.util.Date dateOfBirth) {

        Database db = new Database();

        return db.addUser(username, name, surname, email, password, homeAddress, workAddress, dateOfBirth);
    }

    public static boolean initializeActiveUser(User user) {

        Database db = new Database();

        OopProjectHalilKayra.activeUser = user;
        db.getActiveUserCart(user);
        db.getActiveUserFavorites(user);

        db.getActiveUserOrderedProducts(user);

        db.getActiveUserCreditCards(user);
        return true;

    }

    public static boolean isUserExist(String username) {

        Database db = new Database();

        User user = db.getUser(username);

        return (user != null);

    }

}