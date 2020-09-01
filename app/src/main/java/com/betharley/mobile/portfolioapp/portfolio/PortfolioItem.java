package com.betharley.mobile.portfolioapp.portfolio;

import java.io.Serializable;

public class PortfolioItem implements Serializable {
    private String key;
    private String url;
    private String title;
    private String description;

    public PortfolioItem() {
    }

    public PortfolioItem(String url, String title, String description, String key) {
        this.key = key;
        this.url = url;
        this.title = title;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
