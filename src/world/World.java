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
        world = ImageLoader.loadImage("/Map1.png");
        colonies = new ArrayList<>();
        //TODO Make colors auto generated
        //TODO Make it so colony starting pos cannot overlap (Move location determination here)
        //TODO Make it so colonies fight
        //Need to figure out if it is a problem that fighting will happen twice.
        //Each cell with have its own fight
        //Maybe centralize is some way (a fight HashSet with cells that will fight)
        //TODO Make it so colonies start with more than 1 cell
        //TODO Make it so reproduction occurs when two cells meet (Above must happen first or else colonies will not be able to reproduce)
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
