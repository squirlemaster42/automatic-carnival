import graphics.Display;
import states.StateManager;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private final Display display;

    private final int width;
    private final int height;
    private final String title;

    public boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    public Game(final String title, final int width, final int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        display = new Display(title, width, height);
    }


    public void tick() {
        StateManager.getInstance().tick();
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        StateManager.getInstance().render(g);

        bs.show();
        g.dispose();
    }

    public void run() {
        final int tps = 60;
        double timePerTick = 1000000000.0 / tps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                ticks++;
                delta--;
            }
            render(); //TODO Check that this does not break  things

            if (timer >= 1000000000) {
                System.out.println(ticks);
                ticks = 0;
                timer = 0;
            }

        }

        stop();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
