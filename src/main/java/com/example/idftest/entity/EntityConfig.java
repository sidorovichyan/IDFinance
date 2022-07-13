package com.example.idftest.entity;

import java.util.Objects;

public class EntityConfig {

    int id;

    String symbol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityConfig)) return false;
        EntityConfig that = (EntityConfig) o;
        return getId() == that.getId() && Objects.equals(getSymbol(), that.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSymbol());
    }
}
