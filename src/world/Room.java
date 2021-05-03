/*
Lớp Room: chứa dữ liệu liên quan tới phòng của màn chơi gồm có 
*/

package world;

import java.awt.Graphics;
import java.util.Random;

import entity.creature.enemy.Enemy1;
import graphic.Asset;
import graphic.tile.Tile;
import main.Game;
import main.Handler;
import utility.Utility;

public class Room {
    //phòng có kích thước 15x20
    public final int WIDTH = 20, HEIGHT = 15;
    
    //tên phòng chơi, tên thế giới chứa phòng này
    public int roomName, worldName;

    //lưu tên phòng nằm cạnh phòng hiện tại
    //thứ tự lần lượt là tên phòng phía Đông, Tây, Nam, Bắc
    private int[] exits = {0, 0, 0, 0};

    //lưu bản đồ của phòng dưới dạng mã tên của các Tile
    private int[][] roomMap;

    //demo2, kệ đoạn này
    private Enemy1[] enemyList;
    private int numOfEnemies;
    public void createEnemy(Handler handler, int numOfEnemies){
        this.numOfEnemies = numOfEnemies;
        enemyList = new Enemy1[numOfEnemies];
        for (int i = 0; i < numOfEnemies; i++){
            Random rand = new Random();
            int x, y;
            do {
                x = (rand.nextInt(18) + 2);
                y = (rand.nextInt(13) + 2);
            } while (getTile(x, y).isSolid());
            enemyList[i] = new Enemy1(handler, x * 40 , y * 40, handler.getPlayer());
        }
    }
    
    //cơ bản mình chưa làm phòng có các hành động như có gai nhấp nhô hay biến mặt đất thành lửa (như trong Soul Knight)
    //nên chưa phải dùng tick()
    public void tick(){
        for (Enemy1 e : enemyList){
            if (!e.getIsDead()){
                e.tick();
            }
        }
    }

    //In phòng chơi ra (in từng Tile ra)
    public void render(Graphics g){
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                int locationX = x * Tile.TILE_WIDTH,
                    locationY = y * Tile.TILE_HEIGHT;
                getTile(x, y).render(g, locationX, locationY);
            }
        }

        if (roomName == 1 && worldName == 0 && numOfEnemies == 0){
            g.drawImage(Asset.desertFloor, 16*40, 10*40, Game.TILE_WIDTH, Game.TILE_HEIGHT, null);
        }

        if (roomName == 1 && worldName == 1 && numOfEnemies == 0){
            g.drawImage(Asset.floor, 16*40, 10*40, Game.TILE_WIDTH, Game.TILE_HEIGHT, null);
        }

        for (Enemy1 e : enemyList){
            if (!e.getIsDead()){
                e.render(g);
            }
        }
    }

    //load phòng chơi
    //dòng đầu tiên ghi 2 số là tên thế giới chứa phòng và tên phòng
    //dòng 2 ghi số lối ra của phòng (0 <= exitNum = n <= 4)
    //n dòng tiếp theo, mỗi dòng ghi 2 số là hướng ra và tên phòng tiếp theo ở hướng ra đó
    //  hướng ra 0 = Đông, 1 = Tây, 2 = Nam, 3 = Bắc
    //cuối cùng là ma trận 15x20 ghi mã tên các Tile của phòng (nhớ đoạn nào không ra được thì bao bằng tường để tránh lỗi)
    void loadRoom(String path){
        String file = Utility.loadFileAString(path);
        String[] tokens = file.split("\\s+");

        worldName = Utility.parseInt(tokens[0]);
        roomName = Utility.parseInt(tokens[1]);
        int exitNum = Utility.parseInt(tokens[2]);
        for (int i = 3; i < 2 + 2 * exitNum; i += 2){
            exits[Utility.parseInt(tokens[i])] = Utility.parseInt(tokens[i+1]);
        }

        roomMap = new int[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                roomMap[x][y] = Utility.parseInt(tokens[x + y * WIDTH + 3 + 2 * exitNum]);
            }
        }
    }

    public void decreaseNumOfEnemies(){
        numOfEnemies--;
    }

    public int getNumOfEnemies(){
        return numOfEnemies;
    }

    //lấy tên phòng ra theo hướng dir
    public int getExit(int dir){
        return exits[dir];
    }

    public Enemy1[] getEnemyList(){
        return enemyList;
    }

    //từ tọa độ (x, y), xét mã tên Tile để trả về Tile
    public Tile getTile(int x, int y){
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT){
            return Tile.grassTile;
        }

        Tile tile = Tile.tiles[roomMap[x][y]];

        if (tile == null){
            return Tile.floorTile;
        } else {
            return tile;
        }
    }
}
