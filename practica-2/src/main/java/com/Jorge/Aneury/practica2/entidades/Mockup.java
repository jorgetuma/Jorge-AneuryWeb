package com.Jorge.Aneury.practica2.entidades;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class Mockup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String httpMethod;

    private String headers;

    private int responseCode;

    private String contentType;

    private String responseBody;

    private String description;

    private Date expirationDate;

    private float responseDelay;

    private boolean JwtEnabled;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Nullable
    private Usuario user;

    public Mockup(UUID id, String name, String httpMethod, String headers, int responseCode, String contentType, String responseBody, String description, Date expirationDate, float responseDelay, boolean jwtEnabled, Usuario user) {
        this.id = id;
        this.name = name;
        this.httpMethod = httpMethod;
        this.headers = headers;
        this.responseCode = responseCode;
        this.contentType = contentType;
        this.responseBody = responseBody;
        this.description = description;
        this.expirationDate = expirationDate;
        this.responseDelay = responseDelay;
        JwtEnabled = jwtEnabled;
        this.user = user;
    }

    public Mockup() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public float getResponseDelay() {
        return responseDelay;
    }

    public void setResponseDelay(float responseDelay) {
        this.responseDelay = responseDelay;
    }

    public boolean isJwtEnabled() {
        return JwtEnabled;
    }

    public void setJwtEnabled(boolean jwtEnabled) {
        JwtEnabled = jwtEnabled;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mockup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", headers='" + headers + '\'' +
                ", responseCode=" + responseCode +
                ", contentType='" + contentType + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", description='" + description + '\'' +
                ", expirationDate=" + expirationDate +
                ", responseDelay=" + responseDelay +
                ", JwtEnabled=" + JwtEnabled +
                ", user=" + user +
                '}';
    }
}
