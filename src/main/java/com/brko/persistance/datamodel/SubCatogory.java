package com.brko.persistance.datamodel;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ppetrov on 9/25/2016.
 */
@Document(collection = "sub_category")
public class SubCatogory extends NamedDocument{

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
