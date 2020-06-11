package dto;

import java.io.Serializable;

public interface IUserDTO extends Serializable {
    String toString();

    int getUserId();

    void setUserId(int userId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getInitials();

    void setInitials(String initials);

    String getRole();

    void setRole(String role);

    void setActive(boolean active);

    void setActive(String active) throws Exception;

    boolean isActive();
}
