package com.shop.facades;

import com.shop.entities.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDO implements Serializable {
    private Long id;
    private String name;
    private Category parent;
    private List<Category> subcategories = new ArrayList<Category>();

    public CategoryDO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }
}
