package org.gerdoc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.PasswordAuthentication;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ALUMNO")
public class Login
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PASSWORD")
    private String password;

}