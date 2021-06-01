package entity.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.Handler;

public abstract class Item extends Entity {
    public static final int HEALTH_ITEM = 1, ENERGY_ITEM = 2;
    
    protected BufferedImage frame;
    protected boolean isPickup;

    protected static Random rand = new Random();

    private static int healthRate, energyRate;


    public Item(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_HEIGHT, DEFAULT_WIDTH);
        frame = null;
        isPickup = false;

        healthRate = 25;
        energyRate = healthRate + 25;
    }

    public static int randItemID(){
        int i = rand.nextInt() % 100;

        //return HEALTH_ITEM;
        if (i < healthRate){
            return HEALTH_ITEM;
        } else if (i < energyRate){
            return ENERGY_ITEM;
        }
        return 0;
    }

    protected abstract void affect();

    @Override
    public void tick() {
        if (this.getMovingBounds().intersects(handler.getPlayer().getMovingBounds())){
            this.affect();
            this.isPickup = true;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(frame, (int) x, (int) y, width, height, null);
    }

    //get
    public boolean isPickup() {
        return isPickup;
    }
    
}