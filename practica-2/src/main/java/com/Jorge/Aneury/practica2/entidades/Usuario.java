package com.Jorge.Aneury.practica2.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario implements Serializable {
    @Id
    private String userName;

    private String passWord;

    private String nombre;

    private boolean activo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Rol> roles;

    @OneToMany(mappedBy = "user")
    private List<Mockup> mockups;

    public Usuario() {

    }

    public Usuario(String userName, String passWord, String nombre, boolean activo, List<Rol> roles) {
        this.userName = userName;
        this.passWord = passWord;
        this.nombre = nombre;
        this.activo = activo;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Mockup> getMockups() {
        return mockups;
    }

    public void setMockups(List<Mockup> mockups) {
        this.mockups = mockups;
    }
}
