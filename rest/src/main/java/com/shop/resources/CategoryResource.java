package com.shop.resources;

import com.shop.facades.CategoryDO;
import com.shop.facades.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryResource {

    @Autowired
    private CategoryFacade categoryFacade;

    @RequestMapping("/categories")
    public List<CategoryDO> listCategories(){
        return categoryFacade.getAll();
    }

}
