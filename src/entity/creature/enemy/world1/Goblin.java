package entity.creature.enemy.world1;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.EnemyGun;
import graphic.Asset;
import main.Handler;

public class Goblin extends Enemy {

    private EnemyGun eGun;

    public Goblin(Handler handler, float x, float y, int level) {
        super(handler, x, y, level);

        bounds.x = 5;
        bounds.y = 5;
        bounds.width = 30;
        bounds.height = 34;

        currentFrame = Asset.goblin[0][0];
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

        currentFrame = Asset.goblin[currentDirect + 4 * changeToDamagedFrame][currentFrameID];
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
        updateTarget(80f, 240f);
        

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

    @Override
    protected void initDemo() {
        maxHealth = 1;
        speed = 1f;
        eGun = new EnemyGun(handler, 1, 240f, this, Asset.bulletBomb);
        attackDelay = 100;
    }

    @Override
    protected void initEasy() {
        maxHealth = 60;
        speed = 1.5f;
        eGun = new EnemyGun(handler, 5, 240f, this, Asset.bulletBomb);
        attackDelay = 100;
    }

    @Override
    protected void initHard() {
        maxHealth = 120;
        speed = 2f;
        eGun = new EnemyGun(handler, 8, 240f, this, Asset.bulletBomb);
        attackDelay = 70;
    }
    
}
