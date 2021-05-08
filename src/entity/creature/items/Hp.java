package entity.creature.items;

import java.awt.Graphics;

import graphic.Asset;

import java.awt.Rectangle;
public class Hp{

    private boolean alpha;
    private float x;
    private float y;


    public Hp(float x, float y, boolean alpha) {

        this.x =  x;
        this.y =  y;
        this.alpha = alpha;
    }

    public void render(Graphics graphics) {
        
        if (alpha) {
            graphics.drawImage(Asset.heart, (int)x + 20, (int)y + 20, null);
        }
        else return;
        
    }
    
    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x + 20 , (int) y + 20 , 20, 20);
    }
}
