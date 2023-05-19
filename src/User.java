// Class to represent a user in a system
public class User
{
    String fullName;
    String username;
    String password;


    // Constructor to initialize the full name, username, and password of the user
    public User(String fullName, String username, String password)
    {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    // Getter method to return the full name of the user
    public String getFullName()
    {
        return fullName;
    }

    // Setter method to set the full name of the user
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    // Getter method to return the username of the user
    public String getUsername()
    {
        return username;
    }

    // Setter method to set the username of the user
    public void setUsername(String username)
    {
        this.username = username;
    }

    // Getter method to return the password of the user
    public String getPassword()
    {
        return password;
    }

    // Setter method to set the password of the user
    public void setPassword(String password)
    {
        this.password = password;
    }
}