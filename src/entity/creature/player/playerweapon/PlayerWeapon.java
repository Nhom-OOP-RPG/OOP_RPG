package entity.creature.player.playerweapon;

import java.awt.Graphics;

import main.Handler;

public abstract class PlayerWeapon {
    public static final int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;
    Handler handler;

    protected int damage;
    protected int direct;
    protected int energy;

    protected static boolean isUltimate;
    protected int ultimateEnergy;
    protected static int isUltimateToInt, ultimateEffectFrameID, ultimateDelay, ultimateDelayCount;

    public PlayerWeapon(Handler handler, int damage){
        this.handler = handler;
        this.damage = damage;
        this.energy = 1;

        isUltimate = false;
        isUltimateToInt = 0;
        ultimateEnergy = 60;
        ultimateDelay = 500;
        ultimateDelayCount = 0;
        ultimateEffectFrameID = 0;
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
        this.direct = handler.getPlayer().getCurrentDirect();
    }

    public void ultimate(){
        if (!isUltimate && handler.getPlayer().decreaseEnergy(ultimateEnergy)){
            isUltimate = true;
            isUltimateToInt = 1;
            ultimateDelayCount = 0;
        }
    }

    public abstract void resetWeapon();
}
