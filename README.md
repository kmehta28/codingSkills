# Catalog Merger


### Business Requirement:

- Company A have acquired Company B, A and B sell some common products, sourced from suppliers (Sometimes the same supplier, sometimes a different one). 
- The business wants to consolidate the product catalog into one superset (merged catalog). 

### There are possibilities like:

- Company A and B could have conflicting product codes (SKUs).
- Product codes might be same, but they are different products.
- Product codes are different, but they are same product.
- You should not be duplicating product records in merged catalog.
- Product on merged catalog must have information about the company it belongs to originally.  

### Considerations:
- Products have associated suppliers, each supplier provides 1 or many barcodes for a product, 
- A product may have many suppliers
- If any supplier barcode matches for one product of company A with Company B then we can consider that those products as the same.


### Technologies Used
- Java 1.7

### Technical Description
Please place input CSVs in a directory and copy absolute path to this folder:
1. catalogA.csv - Products for Company A
1. catalogB.csv - Products for Company B
1. suppliersA.csv - List of Suppliers for Company A
1. suppliersB.csv - List of Suppliers for Company B
1. barcodesA.csv - Product barcodes provided by supplier for company A
1. barcodesB.csv - Product barcodes provided by supplier for company B


Please refer input folder for sample CSVs:
1. [catalogA.csv](input/catalogA.csv) - Products for Company A
1. [catalogB.csv](input/catalogB.csv) - Products for Company B
1. [suppliersA.csv](input/suppliersA.csv) - List of Suppliers for Company A
1. [suppliersB.csv](input/suppliersB.csv) - List of Suppliers for Company B
1. [barcodesA.csv](input/barcodesA.csv) - Product barcodes provided by supplier for company A
1. [barcodesB.csv](input/barcodesB.csv) - Product barcodes provided by supplier for company B
1. [result_output.csv](output/result_output.csv) - The correct results based on the above sample data


### How to Run
Execute com.main.Main class to pass path to input folder and output folder. Once it is successfully executed, merged catalog can be found in output folder in result_output.csv

