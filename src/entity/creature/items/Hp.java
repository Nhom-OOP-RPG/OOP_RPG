package entity.creature.items;

import java.awt.Graphics;

import entity.Entity;
import graphic.Asset;
import main.Handler;

public class Hp extends Entity{

    private boolean alpha;
    private int x;
    private int y;
    public Hp(Handler handler, float x, float y, int width, int height, boolean alpha) {

        super(handler, x, y, width, height);
        this.x = (int) x;
        this.y = (int) y;
        this.alpha = alpha;
    }

    @Override
    public void tick() {
        
        
    }

    @Override
    public void render(Graphics graphics) {
        
        if (alpha) {
            graphics.drawImage(Asset.heart, x+ 20, y+ 20, null);
        }
        else return;
        
    }
    
    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

}
