package entity.item;

import graphic.Asset;
import main.Handler;

public class HealthItem extends Item {
    int health;

    public HealthItem(Handler handler, float x, float y) {
        super(handler, x, y);

        frame = Asset.health;
        
        health = rand.nextInt() % 20 + 10;
    }

    @Override
    protected void affect() {
        handler.getPlayer().increaseHealth(health);
        System.out.println("pick up health");
    }
    
}
