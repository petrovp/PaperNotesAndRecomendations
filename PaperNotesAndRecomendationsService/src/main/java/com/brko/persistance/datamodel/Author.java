package com.brko.persistance.datamodel;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author charactestics.
 *
 * Created by Petre on 9/3/2016.
 */
@Document(collection = "author")
public class Author extends NamedDocument {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
