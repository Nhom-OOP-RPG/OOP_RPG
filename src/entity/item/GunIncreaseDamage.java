package entity.item;

import graphic.Asset;
import main.Handler;

public class GunIncreaseDamage extends Item {
    int damage;

    public GunIncreaseDamage(Handler handler, float x, float y) {
        super(handler, x, y);

        frame = Asset.gunDamage;

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 20;
        
        damage = rand.nextInt(3) + 2;
    }

    @Override
    protected void affect() {
        handler.getPlayer().getWeapon(1).increaseDamage(damage);
        System.out.println("pick up gun increase damage " + damage);
    }
    
}
