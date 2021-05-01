package entity.creature.enemy.enemyweapon;

import entity.creature.enemy.Enemy;
import main.Handler;

public class EnemyMelee extends EnemyWeapon{
    float range;
    Enemy user;

    public EnemyMelee(Handler handler, int damage, float range, Enemy user) {
        super(handler, damage);
        this.range = range;
        this.user = user;
    }
    
    @Override
    public void damaging(){
        if (user.getDistanceToTarget() <= range){
            handler.getPlayer().decreaseHealth(damage);
            System.out.println("enemy attack");
        }
    }
}
