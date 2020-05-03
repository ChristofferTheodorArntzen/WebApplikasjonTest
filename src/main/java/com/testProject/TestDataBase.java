package com.testProject;

import java.util.*;

public class TestDataBase {

    private static TestDataBase database = null;

    private Map<Integer,Product> productMap = new HashMap<>();

    public static TestDataBase getDatabase(){

        if(database == null){
            database = new TestDataBase();
        }
        return database;
    }


    public boolean insertProduct(Product product){
        productMap.put(product.getId(), product);
        return true;
    }

    public void deleteProduct(Product product){
        productMap.remove(product.getId());
    }

    public void updateOrCreateProduct(Product product){
        productMap.put(product.getId(), product);
    }

    public Product findProduct(int id){
        return productMap.get(id);
    }

    public int nextId(){

        if(productMap.size() == 0){
            return 1;
        }

        Product product = productMap.values().stream().max(Comparator.comparing(Product::getId)).get();
        return product.getId() + 1;
    }

}
