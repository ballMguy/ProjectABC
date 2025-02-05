package projectabc;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Task> task = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        // task.add(new Task("",""));
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }



    
}

