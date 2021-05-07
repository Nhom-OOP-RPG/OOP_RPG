//Thử nghiệm cho một loại quái

package entity.creature.enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.creature.enemy.enemyweapon.EnemyMelee;
import entity.creature.enemy.enemyweapon.EnemyWeapon;
import entity.creature.items.Hp;
import graphic.Asset;
import main.Handler;

public class Enemy1 extends Enemy {
    //Mã tên quái
    //private static final int ID = 0;

    //như ở Player, chưa dùng đến
    int atRoom = 0;

    private EnemyWeapon eMelee;

    private Hp hp;

    private int i = 1;

    public Enemy1(Handler handler, float x, float y) {
        super(handler, x, y);
        health = 50;
        maxHealth = 50;

        bounds.x = 10;
        bounds.y = 20;
        bounds.width = 20;
        bounds.height = 20;

        eMelee = new EnemyMelee(handler, 5, 50, this, Asset.scratchGummy);
        attackDelayCount = 0;
        attackDelay = 70;

        currentFrame = Asset.gummy[0];
    }

    @Override
    public void tick(){

        //if (isDead) return;
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

        updateTarget(40f, 400f);
        move();

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

        if (isDead)  hp.render(graphics);

        renderHealth(graphics);
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

    @Override
    protected BufferedImage setDeadFrame() {
        return Asset.dead;
    }
}
