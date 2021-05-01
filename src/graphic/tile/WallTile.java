//Đọc class Tile là được

package graphic.tile;

import graphic.Asset;

public class WallTile extends Tile {
    public WallTile(int id){
        super(Asset.wall, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
