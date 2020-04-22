package world;

import entity.Cell;
import graphics.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class World {

    private static final int[][] DIRECTIONS = new int[][]{
            {0, 1}, //Down
            {0, -1}, //Up
            {1, 0}, //Right
            {-1, 0} //Left
    };

    private static final int WORLD_COLOR = -14578139;
    private static final int COL_COLOR = new Color(114, 4, 21).getRGB();
    private static final int STARTING_AGE = 20;

    private BufferedImage world;
    private final Random rnd;
    private ArrayList<Cell> cells;
    private int currID = 0;

    public World() {
        world = ImageLoader.loadImage("/Map.png");
        rnd = new Random();
        cells = new ArrayList<>();

        int startX;
        int startY;

        for(int i = 0; i < 10; i++) {
            do {
                startX = rnd.nextInt(world.getWidth());
                startY = rnd.nextInt(world.getHeight());
            } while (world.getRGB(startX, startY) != -14578139);

            //When move to colonies, can use some sort of data struct to store nodes and nodes are
            //removed when they are surrounded by allies. Will need to figure out how to add back
            //when adjacent allies are removed

            //Can think about changing how cells move. Instead of current, change to older cells move and when
            //reproduce, leave child behind at last point

            world.setRGB(startX, startY, COL_COLOR);
            cells.add(new Cell(currID, startX, startY, STARTING_AGE));
        }
    }

    //Change aging to make dying more likely instead of dying at 20
    public void tick() {
        ArrayList<Cell> newCells = new ArrayList<>();
        Iterator<Cell> cellIter = cells.iterator();
        Cell c;
        while (cellIter.hasNext()) {
            c = cellIter.next();
            boolean age = false;
            int xMove = 0, yMove = 0;
            if (c.getAge() == 0) {
                world.setRGB(c.getX(), c.getY(), WORLD_COLOR);
                cellIter.remove();
                continue;
            }

            if (world.getRGB(c.getX(), c.getY()) == COL_COLOR) {
                int[] dir = DIRECTIONS[rnd.nextInt(DIRECTIONS.length)];
                if (world.getRGB(c.getX() + dir[0], c.getY() + dir[1]) == WORLD_COLOR) {
                    world.setRGB(c.getX(), c.getY(), WORLD_COLOR);
                    xMove = dir[0];
                    yMove = dir[1];
                    if (Math.random() < 0.03) {
                        currID++;
                        newCells.add(new Cell(currID, c.getX(), c.getY(), STARTING_AGE));
                    }
                }
            }

            if (Math.random() < 0.4) {
                age = true;
            }

            c.update(xMove, yMove, age);
            world.setRGB(c.getX(), c.getY(), COL_COLOR);
        }
        cells.addAll(newCells);
    }

    public void render(Graphics g) {
        g.drawImage(world, 0, 0, null);
    }
}
