package com.cstad.management.product;

import com.cstad.management.utils.Utils;
import jdk.jshell.execution.Util;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    private Integer page  = 1;
    private Integer row = 2;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private List<Product> products = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void init(){
        products.add( new Product( 1, "Coca", 10.2, 2, LocalDate.now() ) );
        products.add( new Product( 2, "Pepsi", 10.2, 2, LocalDate.now() ) );
        products.add( new Product( 3, "Sting", 10.2, 2, LocalDate.now() ) );
        products.add( new Product( 4, "Anchor", 10.2, 2, LocalDate.now() ) );
        products.add( new Product( 5, "Krud", 10.2, 2, LocalDate.now() ) );
        Utils.showTitle();
        main_loop: while ( true ){
            Utils.showMainMenu();
            System.out.print("Command --> ");
            String opt = input.next();
            switch (opt){
                case "*" -> displayProduct();
                case "w", "W" -> writeProduct();
                case "h", "H" -> Utils.showHelp();
                case "u", "U" -> updateProductById();
                case "Se", "se", "SE", "sE" -> setRow();
                case "G", "g" -> gotoPage();
                case "S", "s" -> searchByName();
                case "R", "r" -> readProductById();
                case "D", "d" -> deleteProductById();
                case "F", "f" -> gotoFirstPage();
                case "L", "l" -> gotoLastPage();
                case "N", "n" -> nextPage();
                case "P", "p" -> previousPage();
                case "E", "e" -> {
                    Utils.showMessage("Thank You!");
                    break main_loop;
                }
                default -> {
                    String[] shortCut = opt.split("#");
                    if( shortCut.length < 2 ){
                        Utils.showMessage("Please follow system shortcut for do something!");
                    }else{
                        switch ( shortCut[0] ){
                            case "w", "W" -> {
                                if( shortCut[1].split("-").length < 3 ) Utils.showMessage("Please follow system format!");
                                else{
                                    try{
                                        Product newPro = new Product();
                                        String[] proData = shortCut[1].split("-");
                                        newPro.setId(products.size() + 1);
                                        newPro.setName( proData[0] );
                                        newPro.setPrice(Double.parseDouble( proData[1]));
                                        newPro.setQty(Integer.parseInt( proData[2] ));
                                        newPro.setImportDate(LocalDate.now());
                                        writeProductByShortCut(newPro);
                                    }catch (Exception ex){
                                        Utils.showMessage("Please follow system shortcut format!");
                                    }
                                }
                            }
                            case "r", "R" -> {
                                try{
                                    int id = Integer.parseInt( shortCut[1] );
                                    readProductByIdShortCut(id);
                                }catch (Exception ex){
                                    Utils.showMessage("Please make sure search product id is number!");
                                }
                            }

                            case "d", "D" -> {
                                try{
                                    int id = Integer.parseInt( shortCut[1] );
                                    deleteProductByIdShortCut(id);
                                }catch (Exception ex){
                                    Utils.showMessage("Please make sure delete product id is number!");
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    private void writeProductByShortCut( Product product ){
        Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
        table.addCell( "ID              : " + product.getId() );
        table.addCell( "Name            : " + product.getName() );
        table.addCell( "Unit Price      : " + product.getPrice() );
        table.addCell( "Qty             : " + product.getQty() );
        table.addCell( "Imported Date   : " + product.getImportDate() );
        System.out.println( table.render() );
        System.out.print("Are you sure to add this product Yes(y,Y) | No(n, N) : ");
        char o = input.next().charAt(0);
        if( o == 'Y' || o == 'y' ) {
            products.add(product);
            Utils.showMessage("Added Successfully!");
        }
//        products.add( product );
//        Utils.showMessage("Product writed successfully!");
    }
    private void writeProduct(){
        try {
            Product product = new Product();
            System.out.print("Product Id : ");
            product.setId( Integer.parseInt( input.next() ) );
            System.out.print("Product's Name : ");
            product.setName( input.next() );
            System.out.print("Product's price : ");
            product.setPrice( Double.parseDouble( input.next() ) );
            System.out.print("Product Qty : ");
            product.setQty( Integer.parseInt( input.next() ) );
            product.setImportDate( LocalDate.now() );
            Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
            table.addCell( "ID              : " + product.getId() );
            table.addCell( "Name            : " + product.getName() );
            table.addCell( "Unit Price      : " + product.getPrice() );
            table.addCell( "Qty             : " + product.getQty() );
            table.addCell( "Imported Date   : " + product.getImportDate() );
            System.out.println( table.render() );
            System.out.print("Are you sure to add this product Yes(y,Y) | No(n, N) : ");
            char o = input.next().charAt(0);
            if( o == 'Y' || o == 'y' ) {
                products.add(product);
                Utils.showMessage("Added Successfully!");
            }
            else Utils.showMessage("Canceled to add product");
        }catch (Exception e){
            Utils.showMessage("Something wrong!");
        }


    }

    private void displayProduct(){
        if( products.isEmpty() ) {
            Utils.showMessage("No product");
            System.out.println();
            Table t1 = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
            t1.addCell( String.format("Page : %s of %s", page, ((int )Math.ceil( (float) products.size() / row ) == 0 ) ? 1 : (int )Math.ceil( (float) products.size() / row ) ) );
            t1.addCell( String.format("Display rows : %s", row ));
            System.out.println( t1.render() );
        }
        else{
            Table table = new Table( 5, BorderStyle.UNICODE_BOX );
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Unit Price");
            table.addCell("Qty");
            table.addCell("Imported Date");
            products.subList( (page - 1) * row , (row * page) < products.size() ? row * page : products.size() ).forEach(product -> {
               table.addCell( product.getId().toString() );
               table.addCell( product.getName() );
               table.addCell( product.getPrice().toString() );
               table.addCell( product.getQty().toString() );
               table.addCell( product.getImportDate().toString() );
            });
            Table t1 = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
            t1.addCell( String.format("Page : %s of %s", page, (int )Math.ceil( (float) products.size() / row ) ) );
            t1.addCell( String.format("Display rows : %s", row ));
            System.out.println(table.render());
            System.out.println();
            System.out.println( t1.render() );
        }
    }

    private void updateProductById(){
        System.out.print("Enter product id : ");
        Integer searchId = Integer.parseInt(input.next());
        Product newProduct = new Product();
        boolean isFound = false;
        outer_loop: for( Product product : products ){
            if( product.getId().equals(searchId) ){
                Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                table.addCell( "ID              : " + product.getId() );
                table.addCell( "Name            : " + product.getName() );
                table.addCell( "Unit Price      : " + product.getPrice() );
                table.addCell( "Qty             : " + product.getQty() );
                table.addCell( "Imported Date   : " + product.getImportDate() );
                System.out.println( table.render() );
                isFound = true;
                newProduct = product;
                break;
            }else isFound = false;
        }

        if( !isFound ) Utils.showMessage("Product not found");
        else{
            Utils.showUpdateMenu();
            int index = products.indexOf( newProduct );
            main_loop : while ( true ) {
                try{
                    System.out.print("Choose option : ");
                    Integer op = Integer.parseInt(input.next());
                    switch (op) {
                    case 1 -> {
                        System.out.print("Enter new product name : ");
                        String newProductName = input.next();
                        newProduct.setName(newProductName);
                        updateProduct(index, newProduct);
                        break main_loop;
                    }
                    case 2 -> {
                        System.out.print("Enter new product qty : ");
                        Integer newProductQty = Integer.parseInt(input.next());
                        newProduct.setQty(newProductQty);
                        updateProduct(index, newProduct);
                        break main_loop;
                    }
                    case 3 -> {
                        System.out.print("Enter new product unit price : ");
                        Double newProductunitPrice = Double.parseDouble(input.next());
                        newProduct.setPrice(newProductunitPrice);
                        updateProduct(index, newProduct);
                        break main_loop;
                    }
                    case 4 -> {
//                      Update Product Name
                        System.out.print("Enter new product name : ");
                        String newProductName = input.next();
                        newProduct.setName(newProductName);
//                      Update Product QTY
                        System.out.print("Enter new product qty : ");
                        Integer newProductQty = Integer.parseInt(input.next());
                        newProduct.setQty(newProductQty);
//                      Update Product Unit Price
                        System.out.print("Enter new product unit price : ");
                        Double newProductunitPrice = Double.parseDouble(input.next());
                        newProduct.setPrice(newProductunitPrice);
                        updateProduct(index, newProduct);
                        break main_loop;
                    }
                    default -> Utils.showMessage("Please choose number match with lists!");
                }
                }catch (Exception e){
                    Utils.showMessage("Please enter option only number");
                }
            }

        }

    }
    private void updateProduct( int index, Product newProduct ){
            products.set(index, newProduct );
            Utils.showMessage("Updated successfully");
    }

    private  void setRow(){
        while (true){
            try{
                System.out.print("Enter display row : ");
                int numRow = Integer.parseInt( input.next());
                row = numRow;
                page = 1;
                Utils.showMessage("Updated row successfully!");
                break;
            }catch ( Exception ex ){
                Utils.showMessage("Please enter only number!");
            }
        }
    }

    private  void gotoPage(){
        while( true ){
            try{
                System.out.print("Enter goto page : ");
                int numPage = Integer.parseInt( input.next());
                if( numPage <= getNumPage() ){
                    page = numPage;
                    displayProduct();
                }else Utils.showMessage( String.format("Page not found! Page has %s", getNumPage() ) );
                break;
            }catch (Exception ex){
                Utils.showMessage("Please enter only number!");
            }
        }
    }

    private void searchByName(){
        System.out.print("Enter search name : ");
        List<Product> searchResults = new ArrayList<>();
        String searchName = input.next().toLowerCase();
        products.forEach( product -> {
            if( product.getName().toLowerCase().contains( searchName ) ){
                searchResults.add( product );
            }
        } );

        if( searchResults.isEmpty() ) Utils.showMessage("Product not found!");
        else{
            Table table = new Table( 5, BorderStyle.UNICODE_BOX );
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Unit Price");
            table.addCell("Qty");
            table.addCell("Imported Date");
            searchResults.forEach(product -> {
                table.addCell( product.getId().toString() );
                table.addCell( product.getName() );
                table.addCell( product.getPrice().toString() );
                table.addCell( product.getQty().toString() );
                table.addCell( product.getImportDate().toString() );
            });
            System.out.println("\t\tSearch results");
            System.out.println( table.render() );
            System.out.println();
        }

    }

    private void readProductById(){
        while (true){
            try{
                System.out.print("Enter product id : ");
                int searchId = Integer.parseInt(input.next());
                Product searchProduct = null;
                for ( Product product : products ){
                    if( product.getId().equals(searchId) ){
                        searchProduct = product;
                    }
                }
                if( searchProduct == null ) Utils.showMessage("Product not found!");
                else{
                    Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                    table.addCell( "ID              : " + searchProduct.getId() );
                    table.addCell( "Name            : " + searchProduct.getName() );
                    table.addCell( "Unit Price      : " + searchProduct.getPrice() );
                    table.addCell( "Qty             : " + searchProduct.getQty() );
                    table.addCell( "Imported Date   : " + searchProduct.getImportDate() );
                    System.out.println( table.render() );
                }
                break;
            }catch (Exception ex){
                Utils.showMessage("Please enter only number");
            }
        }

    }

    private void readProductByIdShortCut( int id ){
        boolean isFound = false;
        main_loop : for( Product product : products ){
            if( product.getId().equals(id) ){
                Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                table.addCell( "ID              : " + product.getId() );
                table.addCell( "Name            : " + product.getName() );
                table.addCell( "Unit Price      : " + product.getPrice() );
                table.addCell( "Qty             : " + product.getQty() );
                table.addCell( "Imported Date   : " + product.getImportDate() );
                System.out.println( table.render() );
                isFound = true;
                break main_loop;
            }
        }
        if( !isFound )  Utils.showMessage("Product not found!");
    }

    private void deleteProductById() {
        System.out.print("Enter product id : ");
        Integer searchId = Integer.parseInt(input.next());
        boolean isFound = false;
        outer_loop:
        for (Product product : products) {
            if (product.getId().equals(searchId)) {
                Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                table.addCell("ID              : " + product.getId());
                table.addCell("Name            : " + product.getName());
                table.addCell("Unit Price      : " + product.getPrice());
                table.addCell("Qty             : " + product.getQty());
                table.addCell("Imported Date   : " + product.getImportDate());
                System.out.println(table.render());
                isFound = true;
                System.out.print("Are you sure to delete this product Yes(y,Y) | No(n, N) : ");
                char o = input.next().charAt(0);
                if( o == 'Y' || o == 'y' ) {
                    products.remove( product );
                    Utils.showMessage("Product deleted successfully!");
                }
                else Utils.showMessage("Canceled to delete product");
                break;
            } else isFound = false;
        }

        if (!isFound) Utils.showMessage("Product not found!");
    }

    private void deleteProductByIdShortCut(int searchId){
        boolean isFound = false;
        outer_loop:
        for (Product product : products) {
            if (product.getId().equals(searchId)) {
                Table table = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_WIDE, ShownBorders.SURROUND);
                table.addCell("ID              : " + product.getId());
                table.addCell("Name            : " + product.getName());
                table.addCell("Unit Price      : " + product.getPrice());
                table.addCell("Qty             : " + product.getQty());
                table.addCell("Imported Date   : " + product.getImportDate());
                System.out.println(table.render());
                isFound = true;
                System.out.print("Are you sure to delete this product Yes(y,Y) | No(n, N) : ");
                char o = input.next().charAt(0);
                if( o == 'Y' || o == 'y' ) {
                    products.remove( product );
                    Utils.showMessage("Product deleted successfully!");
                }
                else Utils.showMessage("Canceled to delete product");
                break;
            } else isFound = false;
        }
        if (!isFound) Utils.showMessage("Product not found!");
    }

    private void gotoFirstPage(){
        page = 1;
        displayProduct();
    }

    private int getNumPage(){
        return ( ( (int )Math.ceil( (float) products.size() / row )  ) == 0 ) ? 1 : ( (int )Math.ceil( (float) products.size() / row )  );
    }
    private void gotoLastPage(){
        page = getNumPage();
        displayProduct();
    }

    private void nextPage(){
        if( page < getNumPage() ){
            page ++;
            displayProduct();
        }else{
            displayProduct();
            Utils.showMessage("You stand on the last page!");
        }
    }

    private void previousPage(){
        if( page > 1 ){
            page --;
            displayProduct();
        }else{
            displayProduct();
            Utils.showMessage("You stand on the first page!");
        }
    }

}
