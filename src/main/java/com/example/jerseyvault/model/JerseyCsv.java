package com.example.jerseyvault.model;

public class JerseyCsv {
    private String league;
    private String team;
    private String season;
    private String type;
    private String imageUrl;
    private String retailer;
    private double price;
    private String link;

    public JerseyCsv() {}

    public JerseyCsv(String league, String team, String season, String type,
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

    // Getters and setters
    public String getLeague() { return league; }
    public void setLeague(String league) { this.league = league; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { 
        // Truncate the URL if it's too long
        if (imageUrl != null && imageUrl.length() > 1000) {
            this.imageUrl = imageUrl.substring(0, 1000);
        } else {
            this.imageUrl = imageUrl;
        }
    }
    public String getRetailer() { return retailer; }
    public void setRetailer(String retailer) { this.retailer = retailer; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getLink() { return link; }
    public void setLink(String link) { 
        // Truncate the URL if it's too long
        if (link != null && link.length() > 1000) {
            this.link = link.substring(0, 1000);
        } else {
            this.link = link;
        }
    }
}
