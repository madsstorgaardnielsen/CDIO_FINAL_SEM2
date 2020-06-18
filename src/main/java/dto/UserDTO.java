package dto;

import dto.idto.IUserDTO;

public class UserDTO implements IUserDTO {
    private static final long serialVersionUID = 4545864587995944260L;
    private int userId;
    private String firstName;
    private String lastName;
    private String initials;
    private String role;
    private boolean active;
    public UserDTO(int userId, String firstName, String lastName, String initials, String role, boolean active) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.initials = initials;
        this.role = role;
        this.active = active;
    }
    public UserDTO() {
    }

    @Override
    public String toString() {
        return userId + " " + firstName + " " + lastName;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getInitials() {
        return initials;
    }

    @Override
    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void setActive(String active) {
        if (active.equals("true"))
            this.active = true;
        else {
            if (active.equals("false"))
                this.active = false;
        }
    }
}
