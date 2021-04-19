package com.main.data;

public class Sku {

    private String skuId;
    private String description;

    public Sku(String sku, String description) {
        this.skuId = sku;
        this.description = description;
    }

    String getDescription() {
        return description;
    }

    String getSkuId() {
        return skuId;
    }

    @Override public String toString() {
        return "Sku{" + "skuId='" + skuId + '\'' + ", description='" + description + '\'' + '}';
    }
}
