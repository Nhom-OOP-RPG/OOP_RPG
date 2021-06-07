package entity.creature.enemy.world0;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.EnemyMelee;
import graphic.Asset;
import main.Handler;

public class Gummy extends Enemy {

    private EnemyMelee eMelee;

    public Gummy(Handler handler, float x, float y, int level) {
        super(handler, x, y, level);

        bounds.x = 10;
        bounds.y = 20;
        bounds.width = 20;
        bounds.height = 19;

        currentFrame = Asset.gummy[0];
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
        updateTarget(40f, 160f);

        attackDelayCount++;
        if (attackDelayCount >= attackDelay){
            eMelee.damaging();
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

        currentFrame = Asset.gummy[currentFrameID + 2 * changeToDamagedFrame];
    }

    @Override
    protected void initDemo() {
        maxHealth = 1;
        speed = 1f;
        eMelee = new EnemyMelee(handler, 1, 50f, this, Asset.scratchGummy);
        attackDelay = 100;
    }

    @Override
    protected void initEasy() {
        maxHealth = 15;
        speed = 0.5f;
        eMelee = new EnemyMelee(handler, 1, 50f, this, Asset.scratchGummy);
        attackDelay = 70;
    }

    @Override
    protected void initHard() {
        maxHealth = 25;
        speed = 1f;
        eMelee = new EnemyMelee(handler, 2, 50f, this, Asset.scratchGummy);
        attackDelay = 50;
    }
}
