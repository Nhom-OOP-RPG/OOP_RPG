//Là các khối trên bản đồ

package graphic.tile;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile {
    //các loại Tile
    public static Tile[] tiles = new Tile[7];
    public static Tile blankTile = new BlankTile(0);
    public static Tile grassTile = new GrassTile(1);
    public static Tile earthTile = new EarthTile(2);
    public static Tile wallTile = new WallTile(3);
    public static Tile desertfloor = new DesertFloorTile(4);
    public static Tile rock = new RockTile(5);
    public static Tile desertwall = new DesertWallTile(6);
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

    public void tick(){}

    //In Tile ra màn hình với góc trên trái ở vị trí (x, y) (tính bằng pixel)
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, null);
    }

    //true nếu Tile không đi qua được (tường)
    public boolean isSolid(){
        return false;
    }
}
