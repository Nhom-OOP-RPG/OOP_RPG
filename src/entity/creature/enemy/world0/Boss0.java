package entity.creature.enemy.world0;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.enemyweapon.EnemyGun;
import entity.creature.enemy.enemyweapon.EnemyMelee;
import entity.creature.enemy.enemyweapon.EnemyWeapon;
import graphic.Asset;
import main.Handler;

public class Boss0 extends Enemy {

    private EnemyWeapon eGun, eMelee;

    public Boss0(Handler handler, float x, float y, int level) {
        super(handler, x, y, level);

        this.width = DEFAULT_WIDTH * 2;
        this.height = DEFAULT_HEIGHT * 2;

        this.bounds.x = 15;
        this.bounds.y = 10;
        this.bounds.width = 50;
        this.bounds.height = 69;

        currentFrame = Asset.boss0[0][0];
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
        updateTarget(80f, 1000f);
       
        attackDelayCount++;
        if (attackDelayCount >= attackDelay){
            if (this.health < 100){
                ((EnemyGun) eGun).damaging8Dir();
            } else if (this.health < 200){
                eGun.damaging();
            }

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

        currentFrame = Asset.boss0[currentDirect + 4 * changeToDamagedFrame][currentFrameID];
    }

    @Override
    protected void initDemo() {
        this.maxHealth = 1;
        speed = 1f;
        eGun = new EnemyGun(handler, 1, 4000f, this, Asset.bulletRock);
        eMelee = new EnemyMelee(handler, 1, 100f, this, Asset.scratchRock);
        attackDelay = 100;
    }

    @Override
    protected void initEasy() {
        this.maxHealth = 300;
        speed = 1f;
        eGun = new EnemyGun(handler, 10, 4000f, this, Asset.bulletRock);
        eMelee = new EnemyMelee(handler, 10, 100f, this, Asset.scratchRock);
        attackDelay = 100;
    }

    @Override
    protected void initHard() {
        this.maxHealth = 300;
        speed = 1.5f;
        eGun = new EnemyGun(handler, 10, 4000f, this, Asset.bulletRock);
        eMelee = new EnemyMelee(handler, 10, 100f, this, Asset.scratchRock);
        attackDelay = 100;        
    }
    
}
