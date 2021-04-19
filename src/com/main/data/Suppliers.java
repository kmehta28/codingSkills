package com.main.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Suppliers {

    private Map<Integer, Supplier> suppliers = null;

    public Suppliers(String pathToCsv) throws Exception {
        loadSuppliers(pathToCsv);
    }

    private void loadSuppliers(String pathToCsv) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        csvReader.readLine(); // read header
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (data.length != 2) {
                throw new Exception("Invalid CSV format.");
            }
            if (suppliers == null) {
                suppliers = new HashMap<>();
            }
            try {
                Integer supplierId = Integer.valueOf(data[0]);
                suppliers.put(supplierId, new Supplier(supplierId, data[1]));
            } catch (NumberFormatException ex) {
                throw new Exception("Invalid supplier ID.");
            }
        }
        csvReader.close();
    }

    Supplier getSupplierById(Integer id) {
        if (suppliers == null || suppliers.isEmpty()) {
            return null;
        }
        return suppliers.get(id);
    }
}
