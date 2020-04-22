package graphics;

import java.awt.image.BufferedImage;

public abstract class Asset {

    private final String name;

    public Asset(final String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract BufferedImage getImage();
}
