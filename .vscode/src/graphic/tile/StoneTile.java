//Đọc class Tile là được

package graphic.tile;

import graphic.Asset;

public class StoneTile extends Tile{
    public StoneTile(int id){
        super(Asset.stone, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
