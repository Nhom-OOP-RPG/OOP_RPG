package entity.creature.enemy.world1;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.*;
import graphic.Asset;
import main.Handler;

public class Boss1 extends Enemy {

    private EnemyWeapon eGun, eMelee;

    public Boss1(Handler handler, float x, float y) {
        super(handler, x, y);
        this.health = 400;
        this.maxHealth = 400;

        this.width = DEFAULT_WIDTH * 2;
        this.height = DEFAULT_HEIGHT * 2;

        this.bounds.x = 15;
        this.bounds.y = 10;
        this.bounds.width = 50;
        this.bounds.height = 60;

        eGun = new EnemyGun(handler, 10, 4000f, this, Asset.bulletFlame);
        eMelee = new EnemyMelee(handler, 10, 100, this, Asset.scratchRed);
        attackDelayCount = 0;
        attackDelay = 100;
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

        move();
        updateTarget(80f, 400f);

        attackDelayCount++;
        if (attackDelayCount >= attackDelay){
            ((EnemyGun) eGun).damaging8Dir();
            eMelee.damaging();
            attackDelayCount = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        eGun.render(graphics);
        eMelee.render(graphics);
        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);
        
        if (isDead) return;

        renderBossHealth(graphics);
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

        currentFrame = Asset.boss1[currentDirect][currentFrameID];
    }
    
}

