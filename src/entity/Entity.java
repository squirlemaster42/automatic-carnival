package entity;

import graphics.Asset;

import java.awt.Graphics;

public abstract class Entity {

    private final Asset asset;
    private int x, y;

    public Entity(final Asset asset, final int x, final int y){
        this.asset = asset;
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public void render(Graphics g){
        g.drawImage(asset.getImage(), x, y, null);
    }

    public boolean isSolid(){
        return false;
    }
}
