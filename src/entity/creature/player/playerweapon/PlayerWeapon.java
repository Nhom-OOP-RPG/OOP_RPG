package entity.creature.player.playerweapon;

import java.awt.Graphics;

import main.Handler;

public abstract class PlayerWeapon {
    public static final int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;
    Handler handler;

    protected int damage;
    protected int direct;
    protected int energy;

    public PlayerWeapon(Handler handler, int damage){
        this.handler = handler;
        this.damage = damage;
        energy = 1;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public abstract void damaging();

    public void increaseDamage(int n){
        damage += n;
    }

    public void decreaseDamage(int n){
        damage -= n;
    }

    public void setDamage(int n){
        damage = n;
    }

    protected void setDirect(){
        this.direct = handler.getPlayer().attackDirect;
    }
}
