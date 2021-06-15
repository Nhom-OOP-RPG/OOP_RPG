/*
Lá»›p Room: chá»©a dá»¯ liá»‡u liÃªn quan tá»›i phÃ²ng cá»§a mÃ n chÆ¡i gá»“m cÃ³ 
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
import entity.item.*;
import graphic.tile.Tile;
import main.Handler;
import utility.Utility;

public class Room {
    public static final int WIDTH = 20, HEIGHT = 15;
    public static final int GUMMY = 1, MUSHROOM = 2, SNAKE = 3, BAT = 4, SKULL = 5, GOBLIN = 6;
    public static final int HEALTH_ITEM = 1, ENERGY_ITEM = 2, MELEE_DAMAGE_ITEM = 3, GUN_DAMAGE_ITEM = 4, SPEED_ITEM = 5;

    Handler handler;
    
    public int roomName, worldName;
    
    //Má»©c Ä‘á»™ (DEMO, EASY, HARD)
    int level;

    //lÆ°u tÃªn phÃ²ng náº±m cáº¡nh phÃ²ng hiá»‡n táº¡i
    //thá»© tá»± láº§n lÆ°á»£t lÃ  tÃªn phÃ²ng phÃ­a Ä�Ã´ng, TÃ¢y, Nam, Báº¯c
    protected int[] exits = {0, 0, 0, 0};

    //lÆ°u báº£n Ä‘á»“ cá»§a phÃ²ng dÆ°á»›i dáº¡ng mÃ£ tÃªn cá»§a cÃ¡c Tile
    protected int[][] roomMap;

    //Danh sÃ¡ch quÃ¡i trong phÃ²ng
    protected ArrayList<Enemy> enemyList;
    //Danh sÃ¡ch item trong phÃ²ng
    protected ArrayList<Item> itemList;

    public Room(Handler handler, String path, int level){
        this.handler = handler;

        loadRoom(path);

        this.level = level;
        enemyList = new ArrayList<Enemy>();
        itemList = new ArrayList<Item>();
    }
    
    //Cáº­p nháº­t thuá»™c tÃ­nh
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

    //In phÃ²ng chÆ¡i ra (báº£n Ä‘á»“, quÃ¡i, item)
    public void render(Graphics graphics){
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                getTile(x, y).render(graphics, x, y);
            }
        }

        graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Times New Roman", Font.BOLD, 15));
		graphics.drawString("World: " + worldName + ", Room: " + roomName, Tile.TILE_HEIGHT * 2/3 + 590, 12);

        for (Enemy e : enemyList){
                e.render(graphics);
        }

        for (Item i : itemList){
            i.render(graphics);
        }
    }

    void loadRoom(String path){
        String file = Utility.loadFileAString(path);
        String[] tokens = file.split("\\s+");

        worldName = Utility.parseInt(tokens[0]);
        roomName = Utility.parseInt(tokens[1]);


        int exitNum = Utility.parseInt(tokens[2]);

        for (int i = 3; i < 2 + 2 * exitNum; i += 2){
            exits[Utility.parseInt(tokens[i])] = Utility.parseInt(tokens[i+1]);
        }

        //ma tráº­n 15x20 ghi mÃ£ tÃªn cÃ¡c Tile
        roomMap = new int[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                roomMap[x][y] = Utility.parseInt(tokens[x + y * WIDTH + 3 + 2 * exitNum]);
            }
        }
    }

    //GET
    //láº¥y tÃªn phÃ²ng ra theo hÆ°á»›ng dir
    public int getExit(int dir){
        return exits[dir];
    }

    public ArrayList<Enemy> getEnemyList(){
        return enemyList;
    }

    public ArrayList<Item> getItemList(){
        return itemList;
    }

    //tá»« tá»�a Ä‘á»™ (x, y), xÃ©t mÃ£ tÃªn Tile Ä‘á»ƒ tráº£ vá»� Tile
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

    //Add quÃ¡i má»›i vÃ o phÃ²ng
    //add theo mÃ£ tÃªn quÃ¡i -> Random vá»‹ trÃ­
    public void addNewEnemy(int enemyID){
        Random rand = new Random();
        int x, y;
        do {
            x = (rand.nextInt(18) + 2);
            y = (rand.nextInt(13) + 2);
        } while (getTile(x, y).isSolid());

        addNewEnemy(enemyID, x, y);
    }

    //add theo mÃ£ tÃªn quÃ¡i vá»›i sá»‘ lÆ°á»£ng -> Random vá»‹ trÃ­
    public void addNewEnemy(int enemyID, int numOfEnemies){
        for (int i = 0; i < numOfEnemies; i++){
            addNewEnemy(enemyID);
        }
    }

    //add theo mÃ£ tÃªn quÃ¡i vÃ  vá»‹ trÃ­ xÃ¡c Ä‘á»‹nh (tÃ­nh theo vá»‹ trÃ­ tile)
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

    //add item má»›i vÃ o phÃ²ng
    public void addNewItem(int x, int y){
        int itemID = Item.randItemID();
        switch (itemID){
            case HEALTH_ITEM:
                itemList.add(new HealthItem(handler, x, y));
                break;
            case ENERGY_ITEM:
                itemList.add(new EnergyItem(handler, x, y));
                break;
            case MELEE_DAMAGE_ITEM:
                itemList.add(new MeleeIncreaseDamage(handler, x, y));
                break;
            case GUN_DAMAGE_ITEM:
                itemList.add(new GunIncreaseDamage(handler, x, y));
                break;
            case SPEED_ITEM:
                itemList.add(new SpeedItem(handler, x, y));
                break;
        }
    }
}
