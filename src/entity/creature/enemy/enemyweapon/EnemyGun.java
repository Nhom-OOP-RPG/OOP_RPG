package entity.creature.enemy.enemyweapon;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entity.bullet.Bullet;
import entity.creature.enemy.Enemy;
import graphic.Asset;
import main.Handler;

public class EnemyGun extends EnemyWeapon{
    ArrayList<Bullet> shootedBullet;
    BufferedImage[] bulletFrame  = Asset.bulletRed;

    public EnemyGun(Handler handler, int damage, float range, Enemy user) {
        super(handler, damage, range, user);

        shootedBullet = new ArrayList<Bullet>();
    }

    public EnemyGun(Handler handler, int damage, float range, Enemy user, BufferedImage[] bulletFrame) {
        this(handler, damage, range, user);

        this.bulletFrame = bulletFrame;
    }

    @Override
    public void damaging() {
        if (user.getDistanceToTarget() <= range){
            shoot(user.getAngleToTarget());
        }
    }

    public void damaging8Dir() {
        shoot(0);
        shoot(45);
        shoot(90);
        shoot(135);
        shoot(180);
        shoot(225);
        shoot(270);
        shoot(315);
    }

    private void shoot(double angle){
        float startX = user.getCenterX();
        float startY = user.getCenterY();

        shootedBullet.add(new Bullet(handler, startX, startY, false, damage, 4f, angle, bulletFrame));
    }

    @Override
    public void tick() {
        for (Bullet b : shootedBullet){
            if (b.getExploded()){
                shootedBullet.remove(b);
                break;
            } else {
                b.tick();
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        for (Bullet b : shootedBullet){
            b.render(graphics);
        }
    }
}
