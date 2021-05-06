package entity.creature.enemy;

import java.awt.Graphics;

import entity.creature.enemy.enemyweapon.EnemyGun;
import entity.creature.enemy.enemyweapon.EnemyMelee;
import entity.creature.enemy.enemyweapon.EnemyWeapon;
import graphic.Asset;
import main.Handler;
import world.BossRoom;

public class Boss0 extends Enemy {

    private EnemyWeapon eGun, eMelee;

    public Boss0(Handler handler, float x, float y) {
        super(handler, x, y);
        this.health = 80;
        this.maxHealth = 80;

        this.width *= 2;
        this.height *= 2;

        this.bounds.x *= 2;
        this.bounds.y *= 2;
        this.bounds.width *= 2;
        this.bounds.height *= 2;

        eGun = new EnemyGun(handler, 10, 4000f, this, Asset.bulletRock);
        eMelee = new EnemyMelee(handler, 5, 50, this);
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

        updateTarget(40f, 400f);
        move();

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
        animationDelay++;
        if (animationDelay >= 10){
            animationDelay = 0;
            currentFrameID = 1 - currentFrameID;
            currentFrame = Asset.gummy[currentFrameID];
        }
    }
    
}
