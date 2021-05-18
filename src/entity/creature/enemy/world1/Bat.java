package entity.creature.enemy.world1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.EnemyGun;
import graphic.Asset;
import main.Handler;

public class Bat extends Enemy {

    private EnemyGun eGun;

    public Bat(Handler handler, float x, float y, int level) {
        super(handler, x, y, level);
        
        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 20;

        currentFrame = Asset.bat[0];
    }

    @Override
    public void tick(){
        eGun.tick();
        if (health <= 0) {
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
        
        if (isDead) return;

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

    @Override
    protected void initDemo() {
        maxHealth = 1;
        speed = 1f;
        eGun = new EnemyGun(handler, 1, 200f, this);
        attackDelay = 100;
    }

    @Override
    protected void initEasy() {
        maxHealth = 35;
        speed = 0.75f;
        eGun = new EnemyGun(handler, 5, 200f, this);
        attackDelay = 100;
    }

    @Override
    protected void initHard() {
        maxHealth = 35;
        speed = 1f;
        eGun = new EnemyGun(handler, 5, 200f, this);
        attackDelay = 100;
    }
}
