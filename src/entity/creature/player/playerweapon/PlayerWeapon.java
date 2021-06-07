//lớp vũ khí

package entity.creature.player.playerweapon;

import java.awt.Graphics;

import main.Handler;

public abstract class PlayerWeapon {
    public static final int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;
    Handler handler;

    //sát thương
    protected int damage;
    //hướng đánh
    protected int direct;
    //năng lượng tiêu hao mỗi lần đánh
    protected int energy;

    //Ulti
    //có đang sử dụng ulti
    protected static boolean isUltimate;
    //năng lượng tiêu hao khi bật ulti
    protected int ultimateEnergy;
    protected static int ultimateEffectFrameID, ultimateDelay, ultimateDelayCount;

    public PlayerWeapon(Handler handler, int damage){
        this.handler = handler;
        this.damage = damage;
        this.energy = 1;

        isUltimate = false;
        ultimateEnergy = 60;
        ultimateDelay = 500;
        ultimateDelayCount = 0;
        ultimateEffectFrameID = 0;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    //thực hiện tấn công
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

    //bật ulti khi gọi
    public void ultimate(){
        if (!isUltimate && handler.getPlayer().decreaseEnergy(ultimateEnergy)){
            isUltimate = true;
            ultimateDelayCount = 0;
        }
    }

    public abstract void resetWeapon();
}
