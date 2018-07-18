package com.example.myosr.hyperdesigncodingtask.dataProccess;


public class DataEncap {

    private String img;
    private String desc;
    private int id;
    private int price;
    int height;

    public DataEncap() {
    }

    public DataEncap(int id ,String desc,int price,String img,int height) {

        this.desc = desc;
        this.price = price;
        this.id = id;
        this.img=img;
        this.height= height;


    }



    public String getImg() {
        return img;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getPrice() {
        return price;
    }
}
