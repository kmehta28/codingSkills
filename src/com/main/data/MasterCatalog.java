package com.main.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterCatalog {

    private Map<Sku, String> masterCatalogMap = new HashMap<>();
    private static BufferedWriter bufferedWriter = null;

    public MasterCatalog() {
    }

    public MasterCatalog(String outputFolder) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(new File(outputFolder)));
    }

    private void addToMasterCatalog(Sku sku, String companyId) {
        masterCatalogMap.put(sku, companyId);

    }

    public Map<Sku, String> getMasterCatalogMap() {
        return masterCatalogMap;
    }

    /*
     * Method to output Master catalog into CSV.
     * */
    public void generateMasterCatalogInCSV() {
        final String header = "SKU,Description,Source";
        try {
            // write header
            bufferedWriter.write(header);
            bufferedWriter.newLine();

            // write data
            for (Map.Entry<Sku, String> masterCatalogEntry : masterCatalogMap.entrySet()) {
                bufferedWriter.append(masterCatalogEntry.getKey().getSkuId()).append(",").append(
                    masterCatalogEntry.getKey().getDescription()).append(",").append(
                    masterCatalogEntry.getValue());
                bufferedWriter.newLine();
            }

            // closing writer connection
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * Method to load master catalog based on Comapany A and Company B's Supplier Product Barcodes data.
     * */
    public void loadMasterCatalog(SupplierProductBarcodes supplierProductBarcodesA,
                                  SupplierProductBarcodes supplierProductBarcodesB) {

        if (supplierProductBarcodesA == null || supplierProductBarcodesB == null) {
            return;
        }
        List<SupplierProductBarcode> supplierProductBarcodesListA = supplierProductBarcodesA.getSupplierProductBarcodeList();

        List<SupplierProductBarcode> supplierProductBarcodesListB = supplierProductBarcodesB.getSupplierProductBarcodeList();

        //Temporary list to keep track of added products
        List<String> added = new ArrayList<>();

        for (SupplierProductBarcode supplierProductBarcodeA : supplierProductBarcodesListA) {
            if (added.contains(supplierProductBarcodeA.getSku().getSkuId() + "_" + "A")) {
                continue;
            }
            for (SupplierProductBarcode supplierProductBarcodeB : supplierProductBarcodesListB) {
                if (added.contains(supplierProductBarcodeB.getSku().getSkuId() + "_" + "B")) {
                    continue;
                }
                if (supplierProductBarcodeA.equals(supplierProductBarcodeB)) {
                    if (!added.contains(supplierProductBarcodeA.getSku().getSkuId() + "_" + "A")
                        && !added.contains(
                        supplierProductBarcodeB.getSku().getSkuId() + "_" + "B")) {
                        added.add(supplierProductBarcodeA.getSku().getSkuId() + "_" + "A");
                        added.add(supplierProductBarcodeB.getSku().getSkuId() + "_" + "B");
                        addToMasterCatalog(supplierProductBarcodeA.getSku(), "A");
                        break;
                    }
                } else {
                    if (!added.contains(supplierProductBarcodeA.getSku().getSkuId() + "_" + "A")
                        && !added.contains(
                        supplierProductBarcodeB.getSku().getSkuId() + "_" + "B")) {
                        added.add(supplierProductBarcodeA.getSku().getSkuId() + "_" + "A");
                        added.add(supplierProductBarcodeB.getSku().getSkuId() + "_" + "B");

                        addToMasterCatalog(supplierProductBarcodeA.getSku(), "A");
                        addToMasterCatalog(supplierProductBarcodeB.getSku(), "B");
                        break;
                    }
                }
            }
        }

    }

}
