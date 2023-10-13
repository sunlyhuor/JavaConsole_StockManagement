package com.cstad.management.utils;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class Utils {
    public static void showTitle(){
        System.out.println();
        System.out.println("\n" +
                " ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗ \n" +
                        "██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗\n" +
                        "██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║\n" +
                        "██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║\n" +
                        "╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║\n" +
                        " ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝\n");
        System.out.println( "Stock Management System".toUpperCase() );
        System.out.println();
    }

    public static void showMainMenu(){
        System.out.println();
        Table table = new Table( 9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.HEADER_FOOTER_AND_COLUMNS);
        table.addCell( " ".repeat(5) + "*)Display" + " ".repeat(5) );
        table.addCell(" ".repeat(5) + "W)rite" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "R)ead" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "U)pdate" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "D)elete" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "F)irst" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "P)revios" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "N)ext" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "L)ast" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "S)earch" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "G)oto" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "Se)t row" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "H)elp" + " ".repeat(5));
        table.addCell(" ".repeat(5) + "E)xit" + " ".repeat(5));
        System.out.println(table.render());
        System.out.println();
    }

    public static void showHelp(){
        System.out.println();
        Table table = new Table( 1, BorderStyle.CLASSIC_COMPATIBLE_LIGHT_WIDE, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        table.addCell("1.\tPress\t* : Display all record of products");
        table.addCell("2.\tPress\tw : Add new product\n" +
                "\t\tPress\tw -->#proname-unitprice-qty : shortcut for add new product" );
        table.addCell("3.\tPress\tr : read Content any content\n" +
                "\t\tPress\tr#proId : Shortcut for read product by Id" );
        table.addCell("4.\tPress\tU : Update Data");
        table.addCell("5.\tPress\tD : Delete Data\n" +
                "\t\tPress d#proId : shortcut for delete product by Id");
        table.addCell("6.\tPress\tF : Display first page");
        table.addCell( "7.\tPress\tP : Display previous page");
        table.addCell("8.\tPress\tN : Display Next page");
        table.addCell("9.\tPress\tL : Display Last page");
        table.addCell("10.\tPress\tS : Search product by name");
        table.addCell("11.\tPress\tH : Help");
        System.out.println(table.render());
        System.out.println();
    }

    public static void showMessage( String message ){
        System.out.println();
        Table table = new Table(1, BorderStyle.DOTS);
        table.addCell( " ".repeat(5) + message + " ".repeat(5)  );
        System.out.println(table.render());
        System.out.println();
    }

    public static void showUpdateMenu(){
        System.out.println();
        Table table = new Table( 9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.HEADER_FOOTER_AND_COLUMNS);
        table.addCell( " ".repeat(5) + "1. Update product name" + " ".repeat(5) );
        table.addCell( " ".repeat(5) + "2. Update product qty" + " ".repeat(5) );
        table.addCell( " ".repeat(5) + "3. Update product unit price" + " ".repeat(5) );
        table.addCell( " ".repeat(5) + "4. Update product all" + " ".repeat(5) );
        System.out.println(table.render());
        System.out.println();
    }

}
