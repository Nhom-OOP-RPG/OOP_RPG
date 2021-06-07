package entity.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.Handler;

public abstract class Item extends Entity {
    public static final int HEALTH_ITEM = 1, ENERGY_ITEM = 2, MELEE_DAMAGE_ITEM = 3, GUN_DAMAGE_ITEM = 4, SPEED_ITEM = 5;
    
    protected BufferedImage frame;
    protected boolean isPickup;

    protected static Random rand = new Random();

    private static final int HEALTH_RATE  = 25, ENERGY_RATE = 50, MELEE_RATE = 60, GUN_RATE = 70, SPEED_RATE = 75;

    public Item(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_HEIGHT, DEFAULT_WIDTH);
        frame = null;
        isPickup = false;  
    }

    public static int randItemID(){
        int i = rand.nextInt(100);

        //return HEALTH_ITEM;
        if (i < HEALTH_RATE){
            return HEALTH_ITEM;
        }
        if (i < ENERGY_RATE){
            return ENERGY_ITEM;
        }
        if (i < MELEE_RATE){
            return MELEE_DAMAGE_ITEM;
        }
        if (i < GUN_RATE){
            return GUN_DAMAGE_ITEM;
        }
        if (i < SPEED_RATE){
            return SPEED_ITEM;
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
