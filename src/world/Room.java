/*
Lớp Room: chứa dữ liệu liên quan tới phòng của màn chơi gồm có 
*/

package world;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import entity.creature.enemy.Boss0;
import entity.creature.enemy.Enemy;
import entity.creature.enemy.Enemy1;
import entity.creature.enemy.Enemy2;
import graphic.tile.Tile;
import main.Handler;
import utility.Utility;

public class Room {
    //phòng có kích thước 15x20
    public final int WIDTH = 20, HEIGHT = 15;

    Handler handler;
    
    //tên phòng chơi, tên thế giới chứa phòng này
    public int roomName, worldName;

    //lưu tên phòng nằm cạnh phòng hiện tại
    //thứ tự lần lượt là tên phòng phía Đông, Tây, Nam, Bắc
    protected int[] exits = {0, 0, 0, 0};

    //lưu bản đồ của phòng dưới dạng mã tên của các Tile
    protected int[][] roomMap;

    //demo2, kệ đoạn này
    protected ArrayList<Enemy> enemyList;

    public Room(Handler handler, String path){
        this.handler = handler;

        loadRoom(path);

        enemyList = new ArrayList<Enemy>();
    }
    
    //cơ bản mình chưa làm phòng có các hành động như có gai nhấp nhô hay biến mặt đất thành lửa (như trong Soul Knight)
    //nên chưa phải dùng tick()
    public void tick(){
        for (Enemy e : enemyList){
            e.tick();
        }
    }

    //In phòng chơi ra (in từng Tile ra)
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

    //lấy tên phòng ra theo hướng dir
    public int getExit(int dir){
        return exits[dir];
    }

    public ArrayList<Enemy> getEnemyList(){
        return enemyList;
    }

    //từ tọa độ (x, y), xét mã tên Tile để trả về Tile
    public Tile getTile(int x, int y){
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT){
            return Tile.grassTile;
        }

        Tile tile = Tile.tiles[roomMap[x][y]];

        if (tile == null){
            return Tile.blankTile;
        } else {
            return tile;
        }
    }

    public void addNewEnemy(int enemyID){
        Random rand = new Random();
        int x, y;
        do {
            x = (rand.nextInt(18) + 2);
            y = (rand.nextInt(13) + 2);
        } while (getTile(x, y).isSolid());

        addNewEnemy(enemyID, x, y);
    }

    public void addNewEnemy(int enemyID, int numOfEnemies){
        for (int i = 0; i < numOfEnemies; i++){
            Random rand = new Random();
            int x, y;
            do {
                x = (rand.nextInt(18) + 2);
                y = (rand.nextInt(13) + 2);
            } while (getTile(x, y).isSolid());


            addNewEnemy(enemyID, x, y);
        }
    }

    public void addNewEnemy(int enemyID, int x, int y){
        switch (enemyID){
            case 1:
                this.enemyList.add(new Enemy1(handler, x * 40, y * 40));
                return;
            case 2:
                this.enemyList.add(new Enemy2(handler, x * 40, y * 40));
                return;
            case 3:
                this.enemyList.add(new Boss0(handler, x * 40, y * 40));
        }
    }

}
