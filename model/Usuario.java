package model;

import java.util.Date;

public class Usuario {
    private  int id;
    private String username;
    private String password;
    private String email;
    private String genero;
    private String pais;
    private String CP;
    private Date cumple;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public Date getCumple() {
        return cumple;
    }

    public String getEmail() {
        return email;
    }

    public void setCumple(Date cumple) {
        this.cumple = cumple;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
