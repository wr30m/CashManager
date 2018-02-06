package com.example.kirazavrik.cashmanager;

import com.example.kirazavrik.cashmanager.models.Category;

import java.util.ArrayList;

/**
 * Created by kirazavrik on 3.2.18.
 */

class CategoryManager {
    private static CategoryManager categoryManager;
    private String[] categoryNames = {
            "Food",
            "Medical",
            "Alcohol"
    };
    private ArrayList<Category> categories;

    private CategoryManager () {
        initCategoryArray();
    }

    public static CategoryManager getInstance() {
        if (categoryManager == null) {
            categoryManager = new CategoryManager();
        }
        return categoryManager;
    }

    private void initCategoryArray() {
        categories = new ArrayList<>();
        for (String label : categoryNames) {
            categories.add(new Category(label));
        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

}
