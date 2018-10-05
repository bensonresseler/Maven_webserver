package org.bensonvzw.mvn;

public class Product{
    private int id;
    private String naam;
    private int prijs;

    public Product(int id, String naam, int prijs) {
        this.id = id;
        this.naam = naam;
        this.prijs = prijs;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getPrijs() {
        return prijs;
    }
}