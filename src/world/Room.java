/*
Lớp Room: chứa dữ liệu liên quan tới phòng của màn chơi gồm có 
*/

package world;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import entity.creature.enemy.Enemy;
import entity.creature.enemy.world0.*;
import entity.creature.enemy.world1.*;
import entity.item.EnergyItem;
import entity.item.HealthItem;
import entity.item.Item;
import graphic.tile.Tile;
import main.Handler;
import utility.Utility;

public class Room {
    public static final int WIDTH = 20, HEIGHT = 15;
    public static final int GUMMY = 1, MUSHROOM = 2, SNAKE = 3, BAT = 4, SKULL = 5, GOBLIN = 6;
    public static final int HEALTH_ITEM = 1, ENERGY_ITEM = 2;

    Handler handler;
    
    public int roomName, worldName;
    
    //Mức độ (DEMO, EASY, HARD)
    int level;

    //lưu tên phòng nằm cạnh phòng hiện tại
    //thứ tự lần lượt là tên phòng phía Đông, Tây, Nam, Bắc
    protected int[] exits = {0, 0, 0, 0};

    //lưu bản đồ của phòng dưới dạng mã tên của các Tile
    protected int[][] roomMap;

    //Danh sách quái trong phòng
    protected ArrayList<Enemy> enemyList;
    protected ArrayList<Item> itemList;

    public Room(Handler handler, String path, int level){
        this.handler = handler;

        loadRoom(path);

        this.level = level;
        enemyList = new ArrayList<Enemy>();
        itemList = new ArrayList<Item>();
    }
    
    //Cập nhật quái
    public void tick(){
        for (Enemy e : enemyList){
            e.tick();
        }

        for (int i = itemList.size() - 1; i >= 0; i--){
            itemList.get(i).tick();
            if (itemList.get(i).isPickup()){
                itemList.remove(i);
            }
        }
    }

    //In phòng chơi ra (Tile, Quái)
    public void render(Graphics graphics){
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                getTile(x, y).render(graphics, x, y);
            }
        }

        graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.PLAIN, 15));
		graphics.drawString("World: " + worldName + ", Room: " + roomName, Tile.TILE_HEIGHT * 2/3 + 600, 20);

        for (Enemy e : enemyList){
                e.render(graphics);
        }

        for (Item i : itemList){
            i.render(graphics);
        }
    }

    //LOAD PHÒNG CHƠI
    //dòng đầu tiên ghi 2 số là tên thế giới chứa phòng và tên phòng
    //dòng thứ hai ghi số lối ra của phòng (0 <= exitNum = n <= 4)

    //n dòng tiếp theo, mỗi dòng ghi 2 số là hướng ra và tên phòng tiếp theo ở hướng ra đó
    //      hướng ra: 0 = Đông, 1 = Tây, 2 = Nam, 3 = Bắc

    //cuối cùng là ma trận 15x20 ghi mã tên các Tile của phòng (nhớ đoạn nào không ra được thì bao bằng tường để tránh lỗi)
    void loadRoom(String path){
        String file = Utility.loadFileAString(path);
        String[] tokens = file.split("\\s+");

        //tên thế giới chứa phòng và tên phòng
        worldName = Utility.parseInt(tokens[0]);
        roomName = Utility.parseInt(tokens[1]);

        //số lối ra của phòng
        int exitNum = Utility.parseInt(tokens[2]);
        //các hướng ra và tên phòng tiếp theo ở hướng ra đó
        for (int i = 3; i < 2 + 2 * exitNum; i += 2){
            exits[Utility.parseInt(tokens[i])] = Utility.parseInt(tokens[i+1]);
        }

        //ma trận 15x20 ghi mã tên các Tile
        roomMap = new int[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                roomMap[x][y] = Utility.parseInt(tokens[x + y * WIDTH + 3 + 2 * exitNum]);
            }
        }
    }

    //GET
    //lấy tên phòng ra theo hướng dir
    public int getExit(int dir){
        return exits[dir];
    }

    public ArrayList<Enemy> getEnemyList(){
        return enemyList;
    }

    public ArrayList<Item> getItemList(){
        return itemList;
    }

    //từ tọa độ (x, y), xét mã tên Tile để trả về Tile
    public Tile getTile(int x, int y){
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT){
            return Tile.blankTile;
        }

        Tile tile = Tile.tiles[roomMap[x][y]];

        if (tile == null){
            return Tile.blankTile;
        } else {
            return tile;
        }
    }

    //Add quái mới vào phòng
    //add theo mã tên quái -> Random vị trí
    public void addNewEnemy(int enemyID){
        Random rand = new Random();
        int x, y;
        do {
            x = (rand.nextInt(18) + 2);
            y = (rand.nextInt(13) + 2);
        } while (getTile(x, y).isSolid());

        addNewEnemy(enemyID, x, y);
    }

    //add theo mã tên quái với số lượng -> Random vị trí
    public void addNewEnemy(int enemyID, int numOfEnemies){
        for (int i = 0; i < numOfEnemies; i++){
            addNewEnemy(enemyID);
        }
    }

    //add theo mã tên quái và vị trí xác định (tính theo vị trí tile)
    public void addNewEnemy(int enemyID, int x, int y){
        switch (enemyID){
            case GUMMY:
                this.enemyList.add(new Gummy(handler, x * 40, y * 40, level));
                return;
            case MUSHROOM:
                this.enemyList.add(new Mushroom(handler, x * 40, y * 40, level));
                return;
            case SNAKE:
                this.enemyList.add(new Snake(handler, x * 40, y * 40, level));
                return;
            case BAT:
                this.enemyList.add(new Bat(handler, x * 40, y * 40, level));
                return;
            case SKULL:
                this.enemyList.add(new Skull(handler, x * 40, y * 40, level));
                return;
            case GOBLIN:
                this.enemyList.add(new Goblin(handler, x * 40, y * 40, level));
        }
    }

    public void addNewItem(int x, int y){
        int itemID = Item.randItemID();
        switch (itemID){
            case HEALTH_ITEM:
                itemList.add(new HealthItem(handler, x, y));
                break;
            case ENERGY_ITEM:
                itemList.add(new EnergyItem(handler, x, y));
                break;
        }
    }
}
