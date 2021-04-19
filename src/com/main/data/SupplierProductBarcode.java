package com.main.data;

import java.util.Objects;

public class SupplierProductBarcode {

    private Supplier supplier;
    private Sku Sku;
    private String barcode;

    public SupplierProductBarcode(Supplier supplier, Sku sku, String barcode) {
        this.supplier = supplier;
        Sku = sku;
        this.barcode = barcode;
    }

    public Sku getSku() {
        return Sku;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SupplierProductBarcode that = (SupplierProductBarcode) o;
        return supplier.equals(that.supplier) && Objects.equals(barcode, that.barcode);
    }

    @Override public int hashCode() {
        return Objects.hash(supplier, barcode);
    }
}
