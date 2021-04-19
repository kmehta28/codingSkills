package com.main.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private Map<String, Sku> skus = null;

    public Catalog(String pathToCsv) throws Exception {
        loadCalalog(pathToCsv);
    }

    private void loadCalalog(String pathToCsv) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        csvReader.readLine(); // read header
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (data.length != 2) {
                throw new Exception("Invalid CSV format.");
            }
            if (skus == null) {
                skus = new HashMap<>();
            }
            if (data[0] == null || data[0].isEmpty()) {
                throw new Exception("Invalid SKU ID.");
            }
            skus.put(data[0], new Sku(data[0], data[1]));
        }
        csvReader.close();
    }

    Sku getSkuById(String skuId) {
        if (skus == null || skus.isEmpty()) {
            return null;
        }
        return skus.get(skuId);
    }

    public Map<String, Sku> getSkus() {
        return skus;
    }

}
