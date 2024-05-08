package com.example.smartlist.Domain;

public class CloseProductsDomain {
    private String title;
    private String pic;
    private String precio;

    public CloseProductsDomain(String title, String pic, String precio) {
        this.title = title;
        this.pic = pic;
        this.precio=precio;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getPrecio() {
        return precio;
    }

    public void getPrecio(String precio) {
        this.precio = precio;
    }

}
