package learning.DSWII.code.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tuser2")
public class TUser2 implements Serializable{
    @Id
    @Column(name = "idUser2")
    private String idUser2;

    @Column(name = "nameUser")
    private String nameUser;

    @Column(name = "password")
    private String password;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;
/*
    // Implementación básica sin roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // O devuelve una lista vacía si es requerido
    }

    @Override
    public String getUsername() {
        return nameUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; 
    }

    @Override
    public boolean isEnabled() {
        return true; 
    }*/
}
