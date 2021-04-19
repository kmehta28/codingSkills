package com.main.data;

import java.util.Objects;

public class Supplier {

    private int id;
    private String name;

    public Supplier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Supplier supplier = (Supplier) o;
        return id == supplier.id;
    }

    @Override public int hashCode() {
        return Objects.hash(id);
    }

}
