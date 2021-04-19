package com.main;

import com.main.data.MasterCatalog;
import com.main.data.Sku;
import com.main.data.Supplier;
import com.main.data.SupplierProductBarcode;
import com.main.data.SupplierProductBarcodes;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MainTest {

    @Test public void TestMasterCatalogForEmptyCatalog() {

        MasterCatalog masterCatalog = new MasterCatalog();
        masterCatalog.loadMasterCatalog(null, null);
        Assert.assertNotEquals(masterCatalog.getMasterCatalogMap(), null);
        Assert.assertEquals(masterCatalog.getMasterCatalogMap().size(), 0);

    }


    /*
     * Company A SupplierProductBarcodes
     * SupplierId        Sku         Barcode
     * 1                 Sku1        xyz
     * 2                 Sku2        abc
     * 2                 Sku3        efg
     *
     * Company B SupplierProductBarcodes
     * SupplierId        Sku         Barcode
     * 1                 Sku_1       xyz
     * 2                 Sku1        www
     * 3                 SkuX        zzz
     *
     * Expected Merged Catalog
     * SKU           Description        Company
     * Sku1          Mug                 A
     * Sku2          Tea                 A
     * Sku3          Coffee              A
     * Sku1          Pan                 B
     * SkuX          Coffee              B
     * */

    @Test public void TestLoadMasterCatalog() {

        SupplierProductBarcodes mockSupplierProductBarcodesA = Mockito.mock(
            SupplierProductBarcodes.class);

        List<SupplierProductBarcode> supplierProductBarcodeListA = new ArrayList<>();
        Sku skuA1 = new Sku("Sku1", "Mug");
        Sku skuA2 = new Sku("Sku2", "Tea");
        Sku skuA3 = new Sku("Sku3", "Coffee");
        SupplierProductBarcode supplierProductBarcodeA1 = new SupplierProductBarcode(
            new Supplier(1, "Camie"), skuA1, "xyz");
        SupplierProductBarcode supplierProductBarcodeA2 = new SupplierProductBarcode(
            new Supplier(2, "Brilliant"), skuA2, "abc");
        SupplierProductBarcode supplierProductBarcodeA3 = new SupplierProductBarcode(
            new Supplier(2, "Brilliant"), skuA3, "efg");
        supplierProductBarcodeListA.add(supplierProductBarcodeA1);
        supplierProductBarcodeListA.add(supplierProductBarcodeA2);
        supplierProductBarcodeListA.add(supplierProductBarcodeA3);
        Mockito.when(mockSupplierProductBarcodesA.getSupplierProductBarcodeList()).thenReturn(
            supplierProductBarcodeListA);

        SupplierProductBarcodes mockSupplierProductBarcodesB = Mockito.mock(
            SupplierProductBarcodes.class);

        List<SupplierProductBarcode> supplierProductBarcodeListB = new ArrayList<>();
        Sku skuB1 = new Sku("Sku_1", "Mug");
        Sku skuB2 = new Sku("Sku1", "Pan");
        Sku skuB3 = new Sku("SkuX", "Coffee");
        SupplierProductBarcode supplierProductBarcodeB1 = new SupplierProductBarcode(
            new Supplier(1, "Camie"), skuB1, "xyz");
        SupplierProductBarcode supplierProductBarcodeB2 = new SupplierProductBarcode(
            new Supplier(2, "Brilliant"), skuB2, "www");
        SupplierProductBarcode supplierProductBarcodeB3 = new SupplierProductBarcode(
            new Supplier(3, "Flash"), skuB3, "zzz");
        supplierProductBarcodeListB.add(supplierProductBarcodeB1);
        supplierProductBarcodeListB.add(supplierProductBarcodeB2);
        supplierProductBarcodeListB.add(supplierProductBarcodeB3);
        Mockito.when(mockSupplierProductBarcodesB.getSupplierProductBarcodeList()).thenReturn(
            supplierProductBarcodeListB);

        MasterCatalog masterCatalog = new MasterCatalog();

        masterCatalog.loadMasterCatalog(mockSupplierProductBarcodesA, mockSupplierProductBarcodesB);

        System.out.println(masterCatalog.getMasterCatalogMap());

        Assert.assertNotEquals(masterCatalog.getMasterCatalogMap(), null);
        Assert.assertEquals(masterCatalog.getMasterCatalogMap().size(), 5);

        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().containsKey(skuB3));
        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().get(skuB3).equals("B"));

        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().containsKey(skuA3));
        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().get(skuA3).equals("A"));

        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().containsKey(skuA1));
        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().get(skuA1).equals("A"));

        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().containsKey(skuA2));
        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().get(skuA2).equals("A"));

        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().containsKey(skuB2));
        Assert.assertEquals(true, masterCatalog.getMasterCatalogMap().get(skuB2).equals("B"));

    }

}
