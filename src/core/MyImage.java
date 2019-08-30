package core;

import javafx.scene.image.Image;

public class MyImage{

    private String IMAGE_URL;
    private String image_name;
    private Image image;

    public MyImage(){
        IMAGE_URL = "";
        image_name = "";
        image = new Image("");
    }

    public MyImage(String IMAGE_URL, String image_name){
        this.IMAGE_URL = IMAGE_URL;
        this.image_name = image_name;
        image = new Image(IMAGE_URL);
    }

    public String getIMAGE_URL(){
        return IMAGE_URL;
    }

    public String getImageName(){
        return image_name;
    }

    public Image getImageObject(){
        return image;
    }

}
