package businessLayer;

public class Employee {
    private final String employeeUsername;
    private final String employeePassword;

    public Employee(String employeeUsername, String employeePassword){
        this.employeeUsername=employeeUsername;
        this.employeePassword=employeePassword;
    }

    public boolean employeeLogIn(String employeeUsername, String employeePassword){
        return this.employeeUsername.equals(employeeUsername) && this.employeePassword.equals(employeePassword);
    }
}
