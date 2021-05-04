//Đọc class Tile là được

package graphic.tile;

import graphic.Asset;

public class EarthTile extends Tile{
    public EarthTile(int id){
        super(Asset.earth, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
