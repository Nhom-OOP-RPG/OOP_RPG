//Thử nghiệm cho một loại quái

package entity.creature.enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.creature.enemy.enemyweapon.EnemyGun;
import entity.creature.enemy.enemyweapon.EnemyWeapon;
import graphic.Asset;
import main.Handler;

public class Enemy2 extends Enemy {
    //Mã tên quái
    //private static final int ID = 0;

    //như ở Player, chưa dùng đến
    int atRoom = 0;

    private EnemyWeapon eGun;

    public Enemy2(Handler handler, float x, float y) {
        super(handler, x, y);
        health = 40;
        maxHealth = 40;

        eGun = new EnemyGun(handler, 10, 4000f, this);
        attackDelayCount = 0;
        attackDelay = 100;

        animationDelay = 50;
        currentFrame = Asset.bat[0];
    }

    @Override
    public void tick(){
        eGun.tick();

        if (isDead) return;
        if (health <= 0) {
            setDead();
            return;
        }

        currentFrameUpdate();

        updateTarget(40f, 1000f);
        move();

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
    
    //Chuyển đổi Animation
    @Override
    protected void currentFrameUpdate() {
        animationDelay++;
        if (animationDelay >= 10){
            animationDelay = 0;
            currentFrameID = 1 - currentFrameID;
            currentFrame = Asset.bat[currentFrameID];
        }
    }

    @Override
    protected BufferedImage setDeadFrame() {
        return Asset.dead;
    }
}
