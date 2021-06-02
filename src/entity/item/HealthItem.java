package entity.item;

import graphic.Asset;
import main.Handler;

public class HealthItem extends Item {
    int health;

    public HealthItem(Handler handler, float x, float y) {
        super(handler, x, y);

        frame = Asset.health;

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 30;
        
        health = rand.nextInt() % 20 + 10;
    }

    @Override
    protected void affect() {
        handler.getPlayer().increaseHealth(health);
        handler.getPlayer().setOverlayFrame(Asset.health_effect);
        System.out.println("pick up health");
    }
    
}
