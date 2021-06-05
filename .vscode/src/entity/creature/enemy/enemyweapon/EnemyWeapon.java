package entity.creature.enemy.enemyweapon;

import java.awt.Graphics;

import entity.creature.enemy.Enemy;
import main.Handler;

public abstract class EnemyWeapon {
    protected Handler handler;
    protected Enemy user;

    protected float range;
    protected int damage;

    public EnemyWeapon(Handler handler, int damage, float range, Enemy user){
        this.handler = handler;
        this.user = user;
        this.range = range;
        this.damage = damage;
    }

    public abstract void damaging();

    public abstract void tick();

    public abstract void render(Graphics graphics);
}
