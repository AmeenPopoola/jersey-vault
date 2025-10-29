package com.example.jerseyvault.model;

public class Jersey {
    private String league;
    private String team;
    private String season;
    private String type;
    private String imageUrl;
    private String retailer;
    private double price;
    private String link;

    // Default constructor
    public Jersey() {
    }

    // Parameterized constructor
    public Jersey(String league, String team, String season, String type, 
                 String imageUrl, String retailer, double price, String link) {
        this.league = league;
        this.team = team;
        this.season = season;
        this.type = type;
        this.imageUrl = imageUrl;
        this.retailer = retailer;
        this.price = price;
        this.link = link;
    }

    // Getters and Setters
    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
