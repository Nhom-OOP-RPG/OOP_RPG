package entity.creature.items;

import java.awt.Graphics;

import entity.Entity;
import graphic.Asset;
import main.Handler;

public class Hp extends Entity{

    private boolean alpha;
    private int xx;
    private int yy;
    public Hp(Handler handler, float x, float y, int width, int height, boolean alpha) {

        super(handler, x, y, width, height);
        this.xx = (int) x;
        this.yy = (int) y;
        this.alpha = alpha;
    }

    @Override
    public void tick() {
        
        
    }

    @Override
    public void render(Graphics graphics) {
        
        if (alpha) {
            graphics.drawImage(Asset.heart, xx + 20, yy + 20, null);
        }
        else return;
        
    }
    
    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

    public int getXX(){
        return xx;
    }

    public int getYY(){
        return yy;
    }
}
