package entity.item;

import graphic.Asset;
import main.Handler;

public class EnergyItem extends Item {
    int energy;

    public EnergyItem(Handler handler, float x, float y) {
        super(handler, x, y);
        frame = Asset.energy;
        
        energy = rand.nextInt() % 10 + 5;
    }

    @Override
    protected void affect() {
        handler.getPlayer().increaseEnergy(energy);
        System.out.println("pick up energy");
    }
    
}
