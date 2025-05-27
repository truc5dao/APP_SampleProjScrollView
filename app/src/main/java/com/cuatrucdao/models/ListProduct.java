package com.cuatrucdao.models;

import com.cuatrucdao.k22411csampleproject.R;

import java.util.ArrayList;

public class ListProduct {
    ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ListProduct(){
        products = new ArrayList<>();
    }

    public void generate_sample_product_dataset(){
        Product p1 = new Product(1, "Coca Cola", 100, 10.0, R.mipmap.coca);
        Product p2 = new Product(2, "Pepsi", 120, 9.5, R.mipmap.pepsi);
        Product p3 = new Product(3, "7Up", 90, 8.0, R.mipmap.sevenup);
        Product p4 = new Product(4, "Fanta", 85, 8.5, R.mipmap.fanta);
        Product p5 = new Product(5, "Sprite", 95, 9.0, R.mipmap.sprite);
        Product p6 = new Product(6, "Coca Cola", 100, 10.0, R.mipmap.coca);
        Product p7 = new Product(7, "Pepsi", 120, 9.5, R.mipmap.pepsi);
        Product p8 = new Product(8, "7Up", 90, 8.0, R.mipmap.sevenup);
        Product p9 = new Product(9, "Fanta", 85, 8.5, R.mipmap.fanta);
        Product p10 = new Product(10, "Sprite", 95, 9.0, R.mipmap.sprite);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        products.add(p6);
        products.add(p7);
        products.add(p8);
        products.add(p9);
        products.add(p10);
    }
}
