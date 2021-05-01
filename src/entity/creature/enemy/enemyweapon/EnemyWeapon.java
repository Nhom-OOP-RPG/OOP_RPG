package entity.creature.enemy.enemyweapon;

import main.Handler;

public abstract class EnemyWeapon {
    Handler handler;
    protected int damage;

    public EnemyWeapon(Handler handler, int damage){
        this.handler = handler;
        this.damage = damage;
    }

    public abstract void damaging();
}
