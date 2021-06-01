package entity.creature.player.playerweapon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.bullet.Bullet;
import graphic.Asset;
import main.Handler;

public class PlayerGun extends PlayerWeapon{
    public static final int DEFAULT_NUM_BULLET = 1000;
    
    private int numOfBullet;
    private ArrayList<Bullet> shootedBullet;
    BufferedImage[] bulletFrame;

    public PlayerGun(Handler handler, int damage) {
        super(handler, damage);

        energy = 4;

        numOfBullet = DEFAULT_NUM_BULLET;
        shootedBullet = new ArrayList<Bullet>();
        bulletFrame = Asset.bulletPlayer;
    }

    public void shoot() {
        float startX = handler.getPlayer().getCenterX();
        float startY = handler.getPlayer().getCenterY();

        double angle;
        switch (direct){
            case EAST:
                angle = 0;
                break;
            case WEST:
                angle = 180;
                break;
            case SOUTH:
                angle = 90;
                break;
            case NORTH:
                angle = 270;
                break;
            default:
                return;
        }

        shootedBullet.add(new Bullet(handler, startX, startY, true, this.damage, 7f, angle, bulletFrame));
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
    public void damaging(){
        if (!handler.getPlayer().decreaseEnergy(energy)) {
            System.out.println("out of energy");
            return;
        }

        setDirect();
        if (numOfBullet > 0){
            shoot();
            numOfBullet--;
        } else {
            System.out.println("out of bullet");
        }
    }
    
    public void addBullet(int n){
        numOfBullet += n;
    }

    @Override
    public void render(Graphics graphics) {
        for (Bullet b : shootedBullet){
            b.render(graphics);
        }
    }
}
