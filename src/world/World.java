package world;

import entity.Colony;
import graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class World {

    private BufferedImage world;

    private ArrayList<Colony> colonies;

    public World() {
        world = ImageLoader.loadImage("/Map.png");
        colonies = new ArrayList<>();
        //TODO Make colors auto generated
        //TODO Make it so colony starting pos cannot overlap (Move location determination here)
        colonies.add(new Colony(world, new Color(104, 9, 9).getRGB()));
        colonies.add(new Colony(world, new Color(179, 16, 178).getRGB()));
    }

    public void tick() {
        colonies.forEach(Colony::tick);
    }

    public void render(Graphics g) {
        g.drawImage(world, 0, 0, null);
    }
}
