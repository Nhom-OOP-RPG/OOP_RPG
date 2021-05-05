package entity.creature.enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphic.Asset;
import main.Handler;

public class Boss0 extends Enemy {

    public Boss0(Handler handler, float x, float y) {
        super(handler, x, y);

        this.width *= 2;
        this.height *= 2;

        this.bounds.x *= 2;
        this.bounds.y *= 2;
        this.bounds.width *= 2;
        this.bounds.height *= 2;
    }

    @Override
    public void tick(){
        if (health <= 0) {
            setDead();
            return;
        }
        
        currentFrameUpdate();

        updateTarget(40f, 400f);
        move();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);
    }
    
    //Chuyển đổi Animation
    @Override
    protected void currentFrameUpdate() {
        animationDelay++;
        if (animationDelay >= 10){
            animationDelay = 0;
            currentFrameID = 1 - currentFrameID;
            currentFrame = Asset.gummy[currentFrameID];
        }
    }

    @Override
    protected BufferedImage setDeadFrame() {
        return Asset.dead;
    }
}
