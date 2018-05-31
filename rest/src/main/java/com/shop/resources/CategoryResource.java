package com.shop.resources;

import com.shop.objects.Item;
import com.shop.facades.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryResource {

    @Autowired
    private CategoryFacade categoryFacade;

    @RequestMapping("/items")
    public List<Item> listItems(){
        return categoryFacade.getAll();
    }

}
