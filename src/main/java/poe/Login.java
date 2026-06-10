package poe;

//Storing Login details of user
public class Login {
    
    private final String StoredUsername;
    private final String StoredPassword;
    private final String StoredCellPhone;

    //Constructor for Login
    public Login(String username, String password, String cellPhone) {
        this.StoredUsername = username;
        this.StoredPassword = password;
        this.StoredCellPhone = cellPhone;
    }
    
    //Username Validation
    public boolean CheckUsername(){
        return StoredUsername.contains("_") && StoredUsername.length() <= 5;
    }

    //Password Validation
    public boolean CheckPassword(){
        return StoredPassword.length() >= 8 && StoredPassword.matches(".*[A-Z].*") && StoredPassword.matches(".*[0-9].*") && StoredPassword.matches(".*[!@#$%^&*()].*");
    }

    //Cell Phone Validation
    public boolean CheckCellPhone(){
       return StoredCellPhone.matches("\\+27\\d{9}$");
    }

    //Registration error messages for user
    public String RegisterUser(){
        if (!CheckUsername()){
            return "Username is not correctly formatted.";
        }
        if (!CheckPassword()){
            return "Password does not meet the complexity requirements";
        }
        if (!CheckCellPhone()){
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }else {
            return "Above conditions have been met. User successfully registered.";
        }
    }

    //Login check
    public boolean loginUser(String enteredUsername, String enteredPassword, String enteredCellPhone){
        return enteredUsername.equals(StoredUsername) && enteredPassword.equals(StoredPassword) && enteredCellPhone.equals(StoredCellPhone);
    }

    //Results for letting user know which login detail was incorrect
    public String LoginErrorMessage(String enteredUsername, String enteredPassword, String enteredCellPhone){
        if (!enteredUsername.equals(StoredUsername)){
            return "Username is incorrect.";
        }
        if (!enteredPassword.equals(StoredPassword)){
            return "Password is incorrect.";
        }
        if (!enteredCellPhone.equals(StoredCellPhone)){
            return "Cell phone number is incorrect.";
        } else {
            return "Login successful";
        }
    }
}
