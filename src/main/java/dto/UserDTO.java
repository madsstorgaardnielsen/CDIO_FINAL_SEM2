package dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    public UserDTO(int userId, String firstName, String lastName, String initials, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.initials = initials;
        this.role = role;
    }

    private int userId;
    private String firstName;
    private String lastName;
    private String initials;
    private String role;
    private static final long serialVersionUID = 4545864587995944260L;

    public String toString() {
        return userId+" "+firstName+" "+lastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
