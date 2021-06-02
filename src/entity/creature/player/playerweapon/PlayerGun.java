package entity.creature.player.playerweapon;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

import entity.bullet.Bullet;
import graphic.Asset;
import main.Handler;

public class PlayerGun extends PlayerWeapon{
    private ArrayList<Bullet> shootedBullet;

    public PlayerGun(Handler handler, int damage) {
        super(handler, damage);

        energy = 4;
        shootedBullet = new ArrayList<Bullet>();
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

        if (isUltimate){
            int d = this.damage + isUltimateToInt * ultimateDamage;
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle + 40, Asset.bulletPlayer_ultimate));
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle + 20, Asset.bulletPlayer_ultimate));
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle, Asset.bulletPlayer_ultimate));
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle - 20, Asset.bulletPlayer_ultimate));
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle - 40, Asset.bulletPlayer_ultimate));
        } else {
            shootedBullet.add(new Bullet(handler, startX, startY, true, this.damage, 7f, angle, Asset.bulletPlayer));
        }
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

        if (isUltimate){
            ultimateDelayCount++;
            if (ultimateDelayCount > ultimateDelay){
                isUltimate = false;
                isUltimateToInt = 0;
            }
        }
    }

    @Override
    public void damaging(){
        if (!isUltimate && !handler.getPlayer().decreaseEnergy(energy)) {
            return;
        }

        setDirect();
        shoot();
    }

    @Override
    public void render(Graphics graphics) {
        for (Bullet b : shootedBullet){
            b.render(graphics);
        }

        if (isUltimate){
            int x = (int) handler.getPlayer().getX();
            int y = (int) handler.getPlayer().getY();
            float ratio = (float) (this. ultimateDelay - this.ultimateDelayCount) / this.ultimateDelay;
            graphics.setColor(Color.GREEN.darker());
            graphics.fillRect(x, y - 10, (int) (40 * ratio), 5);
        }
    }
}
