package com.brko.persistance.datamodel;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by ppetrov on 9/25/2016.
 */
@Document(collection = "category")
public class Category extends NamedDocument{

    private List<SubCatogory> subCategories;

    public List<SubCatogory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCatogory> subCategories) {
        this.subCategories = subCategories;
    }
}
