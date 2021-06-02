package entity.item;

import graphic.Asset;
import main.Handler;

public class SpeedItem extends Item {

    public SpeedItem(Handler handler, float x, float y) {
        super(handler, x, y);
        
        frame = Asset.speed;

        bounds.x = 15;
        bounds.y = 20;
        bounds.width = 10;
        bounds.height = 20;
    }

    @Override
    protected void affect() {
        handler.getPlayer().increaseSpeed(1.5f);
        System.out.println("pick up melee increase speed 1.5");
    }
    
}
