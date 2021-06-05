package graphic.tile;

import graphic.Asset;

public class DesertWallTile extends Tile {
    public DesertWallTile(int id) {
        super(Asset.desertwall, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
