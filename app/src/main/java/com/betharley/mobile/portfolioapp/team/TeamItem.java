package com.betharley.mobile.portfolioapp.team;

public class TeamItem {
    private String name;
    private String description;
    private int imagem;

    public TeamItem() {
    }

    public TeamItem(String name, String description, int imagem) {
        this.name = name;
        this.description = description;
        this.imagem = imagem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
