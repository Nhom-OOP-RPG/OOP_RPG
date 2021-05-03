package graphic.tile;

import graphic.Asset;

public class DesertWallTile extends Tile {
    public DesertWallTile(int id){
        super(Asset.desertWall, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}