package ru.vinogradov.SpringSecurityLibApp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private String role;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 1, max = 100, message = "Between 1 and 100")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Employee(String username) {
        this.username = username;
    }
}
