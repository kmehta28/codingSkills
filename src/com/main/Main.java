package com.main;

import com.main.data.Catalog;
import com.main.data.MasterCatalog;
import com.main.data.SupplierProductBarcodes;
import com.main.data.Suppliers;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            throw new Exception("Incorrect program agrs");
        }

        String inputFolder = args[0];

        String outputFolder = args[1];

        Catalog catalogA = loadCatalog(inputFolder + "\\catalogA.csv");
        Catalog catalogB = loadCatalog(inputFolder + "\\catalogB.csv");

        Suppliers suppliersA = loadSuppliers(inputFolder + "\\suppliersA.csv");
        Suppliers suppliersB = loadSuppliers(inputFolder + "\\suppliersB.csv");

        SupplierProductBarcodes supplierProductBarcodesA = loadSupplierProductBarcodes(
            inputFolder + "\\barcodesA.csv", suppliersA, catalogA);
        SupplierProductBarcodes supplierProductBarcodesB = loadSupplierProductBarcodes(
            inputFolder + "\\barcodesB.csv", suppliersB, catalogB);

        MasterCatalog masterCatalog = new MasterCatalog(outputFolder + "\\result_output.csv");
        masterCatalog.loadMasterCatalog(supplierProductBarcodesA, supplierProductBarcodesB);
        masterCatalog.generateMasterCatalogInCSV();

    }

    private static SupplierProductBarcodes loadSupplierProductBarcodes(String path,
                                                                       Suppliers suppliers,
                                                                       Catalog catalog)
        throws Exception {
        try {
            return new SupplierProductBarcodes(path, suppliers, catalog);
        } catch (Exception e) {
            throw new Exception(
                "Error while loading supplier product barcode combination for Company: "
                    + e.getMessage());
        }
    }

    private static Suppliers loadSuppliers(String path) throws Exception {
        try {
            return new Suppliers(path);

        } catch (Exception e) {
            throw new Exception("Error while loading Suppliers : " + e.getMessage());
        }
    }

    private static Catalog loadCatalog(String path) throws Exception {
        try {
            return new Catalog(path);

        } catch (Exception e) {
            throw new Exception("Error while loading catalog : " + e.getMessage());
        }
    }
}















