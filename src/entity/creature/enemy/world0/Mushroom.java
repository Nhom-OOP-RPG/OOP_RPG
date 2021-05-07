package entity.creature.enemy.world0;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.EnemyGun;
import graphic.Asset;
import main.Handler;

public class Mushroom extends Enemy{
    private EnemyGun eGun;

    public Mushroom(Handler handler, float x, float y) {
        super(handler, x, y);
        health = 40;
        maxHealth = 40;

        bounds.x = 10;
        bounds.y = 20;
        bounds.width = 20;
        bounds.height = 20;

        eGun = new EnemyGun(handler, 3, 200f, this, Asset.bulletGreen);
        attackDelayCount = 0;
        attackDelay = 100;

        currentFrame = Asset.mushroom[0][0];
    }

    @Override
    protected void currentFrameUpdate() {
        animationDelayCount++;

        if (isDamaged){
            changeToDamagedFrame = 1;
            isDamaged = false;
            animationDelayCount = 0;
        }

        if (animationDelayCount >= animationDelay){
            if (xMove != 0 || yMove != 0){
                currentFrameID = 1 - currentFrameID;
            }
            changeToDamagedFrame = 0;
            animationDelayCount = 0;
        }

        currentFrame = Asset.mushroom[currentDirect + 4 * changeToDamagedFrame][currentFrameID];
    }

    @Override
    public void tick() {
        eGun.tick();

        if (isDead) return;
        if (health <= 0) {
            setDead();
            return;
        }

        currentFrameUpdate();

        move();
        updateTarget(80f, 200f);
        

        attackDelayCount++;
        if (attackDelayCount >= attackDelay){
            eGun.damaging();
            attackDelayCount = 0;
        }
        
    }

    @Override
    public void render(Graphics graphics) {
        eGun.render(graphics);
        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);
        
        if (isDead) return;

        renderHealth(graphics);
    }
    
}
