package com.main.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SupplierProductBarcodes{

    private List<SupplierProductBarcode> supplierProductBarcodeAList = null;

    public SupplierProductBarcodes(String pathToCsv, Suppliers suppliers , Catalog catalog)
        throws Exception {
        loadSupplierProductBarcode(pathToCsv, suppliers,catalog);
    }


    private void loadSupplierProductBarcode(String pathToCsv, Suppliers suppliers, Catalog catalog) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        csvReader.readLine(); // read header
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if(data.length != 3){
                throw new Exception("Invalid CSV format.");
            }
            if(supplierProductBarcodeAList == null){
                supplierProductBarcodeAList = new ArrayList<>();
            }

            Integer supplierId = Integer.parseInt(data[0]);
            String skuId = data[1];
            String barcode = data[2];

            supplierProductBarcodeAList.add(new SupplierProductBarcode(suppliers.getSupplierById(supplierId), catalog.getSkuById(skuId), barcode));
        }
        csvReader.close();
    }

    public List<SupplierProductBarcode> getSupplierProductBarcodeList() {
        return supplierProductBarcodeAList;
    }

}
