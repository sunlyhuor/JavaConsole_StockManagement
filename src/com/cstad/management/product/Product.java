package com.cstad.management.product;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer Qty;
    private LocalDate importDate;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", Qty=" + Qty +
                ", importDate=" + importDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(Qty, product.Qty) && Objects.equals(importDate, product.importDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, Qty, importDate);
    }

    public Product(Integer id, String name, Double price, Integer qty, LocalDate importDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        Qty = qty;
        this.importDate = importDate;
    }
}
