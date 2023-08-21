package com.example.salebookapp.model;

public class Sach {
    private int id;
    private String title;
    private TacGia author;
    private String img;

    private Float price;
    private KhuyenMai discount;

    public Sach(String title, String img, Float price) {
        this.title = title;
        this.img = img;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TacGia getAuthor() {
        return author;
    }

    public String getImg() {
        return img;
    }

    public Float getPrice() {
        return price;
    }

    public KhuyenMai getDiscount() {
        return discount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(TacGia author) {
        this.author = author;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDiscount(KhuyenMai discount) {
        this.discount = discount;
    }
}
