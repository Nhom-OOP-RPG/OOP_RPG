package entity.creature.enemy.enemyweapon;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import main.Handler;

public class EnemyMelee extends EnemyWeapon{

    public EnemyMelee(Handler handler, int damage, float range, Enemy user) {
        super(handler, damage, range, user);
    }
    
    @Override
    public void damaging(){
        if (user.getDistanceToTarget() <= range){
            handler.getPlayer().decreaseHealth(damage);
            System.out.println("enemy attack");
        }
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics graphics) {}
}
