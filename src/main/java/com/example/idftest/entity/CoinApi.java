package com.example.idftest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "crypto_coin")
public class CoinApi {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_API")
    int idApi;

    @Column(name = "symbol")
    String symbol;

    @Column(name = "price")
    Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoinApi)) return false;
        CoinApi coinApi = (CoinApi) o;
        return getIdApi() == coinApi.getIdApi() && Objects.equals(getId(), coinApi.getId()) && Objects.equals(getSymbol(), coinApi.getSymbol()) && Objects.equals(getPrice(), coinApi.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdApi(), getSymbol(), getPrice());
    }
}
