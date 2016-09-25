package com.brko.persistance.datamodel;

import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Paper summery with its properties.
 *
 * Created by Petre on 9/3/2016.
 */
@Document(collection = "papers")
public class PaperSummary extends NamedDocument{

    private String arxivId;

    private String title;

    private String abstractContent;

    private List<Author> authors;

    private Category category;

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArxivId() {
        return arxivId;
    }

    public void setArxivId(String arxivId) {
        this.arxivId = arxivId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        StrBuilder strBuilder = new StrBuilder();
        strBuilder.appendln("arxivId : " + arxivId);
        strBuilder.appendln("title : " + title);
        strBuilder.appendln("category : " + category);
        strBuilder.appendln("abstractContent : " + abstractContent);
        return strBuilder.toString();
    }
}
