package entity.item;

import graphic.Asset;
import main.Handler;

public class HealthItem extends Item {
    int health;

    public HealthItem(Handler handler, float x, float y) {
        super(handler, x, y);

        frame = Asset.health;

        bounds.x = 15;
        bounds.y = 20;
        bounds.width = 10;
        bounds.height = 20;
        
        health = rand.nextInt(10) + 10;
    }

    @Override
    protected void affect() {
        handler.getPlayer().increaseHealth(health);
        handler.getPlayer().setOverlayFrame(Asset.health_effect);
        System.out.println("pick up health " + health);
    }
    
}
