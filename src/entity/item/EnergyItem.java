package entity.item;

import graphic.Asset;
import main.Handler;

public class EnergyItem extends Item {
    int energy;

    public EnergyItem(Handler handler, float x, float y) {
        super(handler, x, y);
        
        frame = Asset.energy;

        bounds.x = 15;
        bounds.y = 20;
        bounds.width = 10;
        bounds.height = 20;
        
        energy = rand.nextInt(10) + 5;
    }

    @Override
    protected void affect() {
        handler.getPlayer().increaseEnergy(energy);
        handler.getPlayer().setOverlayFrame(Asset.energy_effect);
        System.out.println("pick up energy " + energy);
    }
    
}
