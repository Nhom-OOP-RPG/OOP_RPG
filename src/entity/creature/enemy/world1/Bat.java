package entity.creature.enemy.world1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.EnemyGun;
import entity.creature.items.Hp;
import graphic.Asset;
import main.Handler;

public class Bat extends Enemy {

    private EnemyGun eGun;

    private Hp hp;

    private int i = 1;

    public Bat(Handler handler, float x, float y) {
        super(handler, x, y);
        health = 35;
        maxHealth = 35;

        eGun = new EnemyGun(handler, 5, 200f, this);
        attackDelayCount = 0;
        attackDelay = 100;

        currentFrame = Asset.bat[0];
    }

    @Override
    public void tick(){
        eGun.tick();

        if (isDead) return;
        if (health <= 0) {
            if (i == 1) {
                hp = new Hp(handler, x, y, width, height, true);
                if(Math.abs(handler.getPlayer().getCenterX() - hp.getX()) <= 30 && Math.abs(handler.getPlayer().getCenterY() - hp.getY()) <= 30){
    
                    hp.setAlpha(false);
                    if (100-handler.getPlayer().getHealth() < 90) {
                        handler.getPlayer().setHealth(handler.getPlayer().getHealth() + 10);
                    } else handler.getPlayer().setHealth(100);
                    i--;
                }
            }
            setDead();
            return;
        }

        currentFrameUpdate();

        move();
        updateTarget(40f, 200f);

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
        
        if (isDead) hp.render(graphics);

        renderHealth(graphics);
    }
    
    //Chuyển đổi Animation
    @Override
    protected void currentFrameUpdate() {
        animationDelayCount++;

        if (isDamaged){
            changeToDamagedFrame = 1;
            isDamaged = false;
            animationDelayCount = 0;
        }

        if (animationDelayCount >= animationDelay){
            animationDelayCount = 0;
            currentFrameID = 1 - currentFrameID;
            changeToDamagedFrame = 0;
        }

        currentFrame = Asset.bat[currentFrameID + 2 * changeToDamagedFrame];
    }

    @Override
    protected BufferedImage setDeadFrame() {
        return Asset.dead;
    }
}
