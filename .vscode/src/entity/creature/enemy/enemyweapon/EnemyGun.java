package entity.creature.enemy.enemyweapon;

import java.util.ArrayList;
import java.awt.Graphics;

import entity.bullet.Bullet;
import entity.creature.enemy.Enemy;
import main.Handler;

public class EnemyGun extends EnemyWeapon{
    ArrayList<Bullet> shootedBullet;

    public EnemyGun(Handler handler, int damage, float range, Enemy user) {
        super(handler, damage, range, user);

        shootedBullet = new ArrayList<Bullet>();
    }

    @Override
    public void damaging() {
        if (user.getDistanceToTarget() <= range){
            shoot(user.getAngleToTarget());
        }
    }

    private void shoot(double angle){
        float startX = user.getX();
        float startY = user.getY();

        shootedBullet.add(new Bullet(handler, startX, startY, false, damage, 4f, angle));
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
