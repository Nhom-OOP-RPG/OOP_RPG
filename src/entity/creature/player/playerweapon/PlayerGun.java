package entity.creature.player.playerweapon;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
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
            int d = this.damage * 2;
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle + 40, Asset.bulletPlayer_ultimate));
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle + 20, Asset.bulletPlayer_ultimate));
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle, Asset.bulletPlayer_ultimate));
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle - 20, Asset.bulletPlayer_ultimate));
            shootedBullet.add(new Bullet(handler, startX, startY, true, d, 7f, angle - 40, Asset.bulletPlayer_ultimate));
        } else {
            shootedBullet.add(new Bullet(handler, startX, startY, true, this.damage, 5f, angle, Asset.bulletPlayer));
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

            if (ultimateDelayCount % 4 == 0){
                ultimateEffectFrameID = 1 - ultimateEffectFrameID;
            }

            if (ultimateDelayCount > ultimateDelay){
                isUltimate = false;
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
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("arial", Font.PLAIN, 15));
        graphics.drawString("Using Gun", 360, 590);

        for (Bullet b : shootedBullet){
            b.render(graphics);
        }

        if (isUltimate){
            int x = (int) handler.getPlayer().getX();
            int y = (int) handler.getPlayer().getY();

            graphics.drawImage(Asset.ultimate_effect[ultimateEffectFrameID], x - 10, y, 60, 40, null);

            float ratio = (float) (ultimateDelay - ultimateDelayCount) / ultimateDelay;
            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y - 10, (int) 40, 5);
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(x, y - 10, (int) (40 * ratio), 5);
        }
    }

    @Override
    public void resetWeapon() {
        isUltimate = false;
        ultimateDelayCount = 0;
        this.shootedBullet.clear();
    }
}
