package org.example.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {

    private String Id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String address;
}
