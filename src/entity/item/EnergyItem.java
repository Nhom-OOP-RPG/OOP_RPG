package entity.item;

import graphic.Asset;
import main.Handler;

public class EnergyItem extends Item {
    int energy;

    public EnergyItem(Handler handler, float x, float y) {
        super(handler, x, y);
        
        frame = Asset.energy;

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 30;
        
        energy = rand.nextInt() % 10 + 5;
    }

    @Override
    protected void affect() {
        handler.getPlayer().increaseEnergy(energy);
        System.out.println("pick up energy");
    }
    
}