package entity.creature.enemy.enemyweapon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.creature.enemy.Enemy;
import graphic.Asset;
import main.Handler;

public class EnemyMelee extends EnemyWeapon{

    private BufferedImage scratchFrame;

    public EnemyMelee(Handler handler, int damage, float range, Enemy user) {
        super(handler, damage, range, user);

        scratchFrame = Asset.scratchGrey;
    }

    public EnemyMelee(Handler handler, int damage, float range, Enemy user, BufferedImage scratchFrame) {
        super(handler, damage, range, user);

        this.scratchFrame = scratchFrame;
    }
    
    @Override
    public void damaging(){
        if (user.getDistanceToTarget() <= range){
            handler.getPlayer().decreaseHealth(damage);
            handler.getPlayer().setScratchedFrame(scratchFrame);
            System.out.println("enemy attack");
        }
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics graphics) {}

    public void setScratchedFrame(BufferedImage frame){
        this.scratchFrame = frame;
    }
}
