//Thử nghiệm cho một loại quái

package entity.creature.enemy;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import entity.creature.enemy.enemyweapon.EnemyMelee;
import entity.creature.enemy.enemyweapon.EnemyWeapon;
import entity.creature.player.Player;
import graphic.Asset;
import main.Handler;

public class Enemy1 extends Enemy {
    //Mã tên quái
    //private static final int ID = 0;

    //như ở Player, chưa dùng đến
    int atRoom = 0;

    private EnemyWeapon eMelee;

    public Enemy1(Handler handler, float x, float y, Player target) {
        super(handler, x, y, target);
        health = 50;

        eMelee = new EnemyMelee(handler, 5, 50, this);
        attackDelayCount = 0;
        attackDelay = 70;

        currentFrame = Asset.enemy1[0];
    }

    @Override
    public void tick(){
        if (health <= 0) {
            setDead();
            System.out.println("enemy dead");
        }

        updateTarget(40f, 400f);
        move();

        attackDelayCount++;
        if (attackDelayCount >= attackDelay){
            eMelee.damaging();
            attackDelayCount = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        currentFrameUpdate();
        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);

        graphics.setColor(Color.ORANGE);
		graphics.setFont(new Font("arial", Font.PLAIN, 10));
		graphics.drawString(getHealth()+" / 50", (int) this.x, (int) this.y);
    }
    
    //Chuyển đổi Animation
    @Override
    protected void currentFrameUpdate() {
        animationDelayCount++;
        if (animationDelayCount >= animationDelay){
            animationDelayCount = 0;
            currentFrameID = 1 - currentFrameID;
        }

        if (isDamagedDelay){
            damagedAnimationDelayCount++;
            currentFrame = Asset.enemy1Damaged[currentFrameID];
            if (damagedAnimationDelayCount > animationDelay){
                isDamagedDelay = false;
                damagedAnimationDelayCount = 0;
            }
        } else {
            currentFrame = Asset.enemy1[currentFrameID];
        }
    }
}
