package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Colony {

    private static final int[][] DIRECTIONS = new int[][]{
            {0, 1}, //Down
            {0, -1}, //Up
            {1, 0}, //Right
            {-1, 0} //Left
    };

    private static final int WORLD_COLOR = -14578139;
    private static final int STARTING_AGE = 1; //Start at 1 to make the math nicer
    private static final double DEATH_RATE = 0.001;
    private static final double REPRODUCTION_RATE = 0.1;
    private static final double AGE_RATE = 0.4;

    private BufferedImage world;
    private final Random rnd;
    private ArrayList<Cell> cells;
    private int currID;
    private final int colColor;

    public Colony(BufferedImage world, final int colColor) {
        this.world = world;
        this.colColor = colColor;
        rnd = new Random();
        cells = new ArrayList<>();

        int startX;
        int startY;

        do {
            startX = rnd.nextInt(world.getWidth());
            startY = rnd.nextInt(world.getHeight());
        } while (world.getRGB(startX, startY) != -14578139);

        //When move to colonies, can use some sort of data struct to store nodes and nodes are
        //removed when they are surrounded by allies. Will need to figure out how to add back
        //when adjacent allies are removed

        //Can think about changing how cells move. Instead of current, change to older cells move and when
        //reproduce, leave child behind at last point

        world.setRGB(startX, startY, colColor);
        cells.add(new Cell(currID, startX, startY, STARTING_AGE));

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
            double rand = rnd.nextDouble();
            if (rand/c.getAge() < DEATH_RATE) {
                world.setRGB(c.getX(), c.getY(), WORLD_COLOR);
                cellIter.remove();
                continue;
            }

            if (world.getRGB(c.getX(), c.getY()) == colColor) {
                int[] dir = DIRECTIONS[rnd.nextInt(DIRECTIONS.length)];
                if (world.getRGB(c.getX() + dir[0], c.getY() + dir[1]) == WORLD_COLOR) {
                    world.setRGB(c.getX(), c.getY(), WORLD_COLOR);
                    xMove = dir[0];
                    yMove = dir[1];
                    if (Math.random() < REPRODUCTION_RATE) {
                        currID++;
                        newCells.add(new Cell(currID, c.getX(), c.getY(), STARTING_AGE));
                    }
                }
            }

            if (Math.random() < AGE_RATE) {
                age = true;
            }

            c.update(xMove, yMove, age);
            world.setRGB(c.getX(), c.getY(), colColor);
        }
        cells.addAll(newCells);
    }
}
