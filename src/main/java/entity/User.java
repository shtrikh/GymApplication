package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    Long id;
    String firstName;
    String lastName;
    String username;
    String password;
    boolean isActive;
}
