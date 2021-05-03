//Là các khối trên bản đồ

package graphic.tile;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile {
    //Static object của class
    //tạm thời mới có 4 loại Tile
    public static Tile[] tiles = new Tile[6];
    public static Tile grassTile = new GrassTile(0);
    public static Tile floorTile = new FloorTile(1);
    public static Tile wallTile = new WallTile(2);
    public static Tile stoneTile = new StoneTile(3);
    public static Tile desertFloorTile = new DesertFloorTile(4);
    public static Tile desertWallTile = new DesertWallTile(5);

    //Thân class

    //Chiều dài rộng trên màn hình
    public static final int TILE_WIDTH = 40;
    public static final int TILE_HEIGHT = 40; 

    //Mã tên Tile
    protected final int ID;

    //Hình ảnh của một Tile
    protected BufferedImage texture;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.ID = id;

        tiles[ID] = this;
    }

    //tạm thời chưa có gai hay lửa gì (như đã nói trong Room)
    //nên hàm này để đấy
    public void tick(){

    }

    //In Tile ra màn hình với góc trên trái ở vị trí (x, y) (tính bằng pixel)
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    //true nếu Tile không đi qua được (tường, đá,...)
    public boolean isSolid(){
        return false;
    }
    
    public int getID(){
        return ID;
    }
}
