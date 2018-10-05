package org.bensonvzw.mvn;

import java.util.ArrayList;
import java.util.List;

public class ProductenLijst {
    private static List<Product> producten = new ArrayList<>();
    static{
        producten.add(new Product(1, "potlood", 2));
        producten.add(new Product(2, "gom", 5));
    }
    public static List<Product> getProducten(){
        return producten;
    }
}