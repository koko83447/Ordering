package com.example.android.lab15_coffee;

import java.io.Serializable;

public class Burger implements Serializable {
    private int burgerID;
    private String burgerName;
    private int burgerPrice;
    private String burgerText;
    private String burgerImage;
    private String burgerMenu;
    private String burgerPic;
    private int burgerTotal;
    private String burgerTea;
    private int burgurQuantuty;

    public Burger(int total,String tea,int quantity){
        this.burgerTotal = total;
        this.burgerTea = tea;
        this.burgurQuantuty = quantity;
        setTotal(burgerTotal);
        setTea(burgerTea);
        setQuantity(burgurQuantuty);
    }

    public Burger(int burgerID,String burgerName,int burgerPrice,String burgerText,String burgerImage,String burgerMenu,String burgerPic){
        setID(burgerID);
        setName(burgerName);
        setPrice(burgerPrice);
        setText(burgerText);
        setImage(burgerImage);
        setMenu(burgerMenu);
        setPic(burgerPic);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Burger)) return false;

        return (this.burgerID == ((Burger) obj).getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + burgerID;
        hash = hash * prime + burgerPrice;
        hash = hash * prime + (burgerName == null ? 0 :burgerName.hashCode());
        hash = hash * prime + (burgerText == null ? 0 :burgerText.hashCode());
        hash = hash * prime + (burgerImage == null ? 0 :burgerImage.hashCode());
        hash = hash * prime + (burgerMenu == null ? 0 :burgerMenu.hashCode());
        hash = hash * prime + (burgerPic == null ? 0 :burgerPic.hashCode());

        return hash;
    }

    public void setID(int id) {
        this.burgerID = id;
    }

    public void setName(String name) {
        this.burgerName = name;
    }

    public void setText(String text) {
        this.burgerText = text;
    }

    public void setImage(String image) {
        this.burgerImage = image;
    }

    public void setMenu(String menu) {
        this.burgerMenu = menu;
    }

    public void setPic(String pic) {
        this.burgerPic = pic;
    }

    public void setPrice(int price) {
        this.burgerPrice = price;
    }

    public void setTotal(int total) {
        this.burgerTotal = total;
    }

    public void setTea(String tea) {
        this.burgerTea = tea;
    }

    public void setQuantity(int quantity) {
        this.burgurQuantuty = quantity;
    }

    public int getId() {
        return burgerID;
    }

    public String getName() {
        return burgerName;
    }

    public String getText() {
        return burgerText;
    }

    public String getImage() {
        return burgerImage;
    }

    public String getMenu() {
        return burgerMenu;
    }

    public String getPic() {
        return burgerPic;
    }

    public int getPrice() {
        return burgerPrice;
    }

    public int getTotal() {
        return burgerTotal;
    }

    public String getTea() {
        return burgerTea;
    }

    public int getQuantity() {
        return burgurQuantuty;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "title='" + burgerName + '\'' +
                ", price=" + burgerPrice +
                ", img_resource_id=" + burgerPic +
                '}';
    }
}
