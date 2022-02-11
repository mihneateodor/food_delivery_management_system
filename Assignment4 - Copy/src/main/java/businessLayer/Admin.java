package businessLayer;

public class Admin {
    private final String adminUsername;
    private final String adminPassword;

    public Admin(String adminUsername, String adminPassword){
        this.adminUsername=adminUsername;
        this.adminPassword=adminPassword;
    }

    public boolean adminLogIn(String adminUsername, String adminPassword){
        return this.adminUsername.equals(adminUsername) && this.adminPassword.equals(adminPassword);
    }
}
