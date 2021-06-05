package entity.creature.enemy.world0;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.EnemyMelee;
import graphic.Asset;
import main.Handler;

public class Snake extends Enemy {

    private EnemyMelee eMelee;

    public Snake(Handler handler, float x, float y, int level) {
        super(handler, x, y, level);

        bounds.x = 10;
        bounds.y = 5;
        bounds.width = 20;
        bounds.height = 34;

        currentFrame = Asset.snake[0][0];
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
        updateTarget(40f, 200f);
        
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

        currentFrame = Asset.snake[currentDirect + 4 * changeToDamagedFrame][currentFrameID];
    }

    @Override
    protected void initDemo() {
        maxHealth = 1;
        speed = 1f;
        eMelee = new EnemyMelee(handler, 1, 50f, this, Asset.scratchVenom);
        attackDelay = 100;
    }

    @Override
    protected void initEasy() {
        maxHealth = 50;
        speed = 1f;
        eMelee = new EnemyMelee(handler, 5, 50f, this, Asset.scratchVenom);
        attackDelay = 70;
    }

    @Override
    protected void initHard() {
        maxHealth = 50;
        speed = 1.5f;
        eMelee = new EnemyMelee(handler, 5, 50f, this, Asset.scratchVenom);
        attackDelay = 70;
    }
}
