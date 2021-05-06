package entity.creature.enemy.world1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.EnemyMelee;
import graphic.Asset;
import main.Handler;

public class Skull extends Enemy{

    private EnemyMelee eMelee;

    public Skull(Handler handler, float x, float y) {
        super(handler, x, y);
        health = 50;
        maxHealth = 50;

        bounds.x = 10;
        bounds.y = 20;
        bounds.width = 20;
        bounds.height = 20;

        eMelee = new EnemyMelee(handler, 5, 50, this, Asset.scratchGrey);
        attackDelayCount = 0;
        attackDelay = 70;

        currentFrame = Asset.skull[0][0];
    }

    @Override
    public void tick(){

        if (isDead) return;
        if (health <= 0) {
            setDead();
            return;
        }
        
        currentFrameUpdate();

        move();
        updateTarget(40f, 400f);
        
        attackDelayCount++;
        if (attackDelayCount >= attackDelay){
            eMelee.damaging();
            eMelee.tick();
            attackDelayCount = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);

        if (isDead) return;

        renderHealth(graphics);
    }
    
    //Chuyển đổi Animation
    @Override
    protected void currentFrameUpdate() {
        animationDelayCount++;
        if (animationDelayCount >= animationDelay){
            if (xMove != 0 || yMove != 0){
                currentFrameID = 1 - currentFrameID;
            }
            //changeToDamagedFrame = 0;
            animationDelayCount = 0;
        }

        currentFrame = Asset.skull[currentDirect][currentFrameID];
    }

    @Override
    protected BufferedImage setDeadFrame() {
        return Asset.dead;
    }
}
