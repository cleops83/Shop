package com.shop.facades;

import com.shop.entities.Category;
import com.shop.entities.Product;
import com.shop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryFacade {

    @Autowired
    private CategoryService categoryService;

    public List<CategoryDO> getAll(){
        List<CategoryDO> categoryDOS = new ArrayList<CategoryDO>();
        Iterable<Category> categories = categoryService.getAll();
        List<Category> topCategories = new ArrayList<Category>();

        for (Category cat : categories) {
            if(cat.getParent() == null){
                topCategories.add(cat);
            }
        }

        for(Category category:topCategories){
            recursiveTree(category);
        }

        return categoryDOS;
    }

    public void recursiveTree(Category cat) {
        System.out.println("C ->" + cat.getName());
        if (cat.getSubcategories().size() > 0) {
            for (Category c : cat.getSubcategories()) {

                for(Product p:c.getProducts()){
                    System.out.println("P ->" + p.getName());
                }
                recursiveTree(c);
            }
        }
    }
}
