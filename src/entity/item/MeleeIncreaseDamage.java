package entity.item;

import graphic.Asset;
import main.Handler;

public class MeleeIncreaseDamage extends Item {
    int damage;

    public MeleeIncreaseDamage(Handler handler, float x, float y) {
        super(handler, x, y);

        frame = Asset.meleeDamage;

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 20;
        
        damage = rand.nextInt() % 5 + 5;
    }

    @Override
    protected void affect() {
        handler.getPlayer().getWeapon(0).increaseDamage(damage);
        System.out.println("pick up melee increase damage");
    }
    
}
