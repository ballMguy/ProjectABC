package projectabc;

import java.util.HashMap;

public class Database {
    private static HashMap<String, User> users = new HashMap<>();

    public static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password));
        return true;
    }

    public static User authenticate(String username, String password) {
        User user = users.get(username);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }

    public static void clear() {
        users.clear();
    }
    
}