/*
Lớp World: chứa dữ liệu liên quan tới các thế giới của các màn chơi
*/

package world;

import java.awt.Graphics;

import entity.creature.player.Player;
import main.Game;
import main.Handler;
import state.State;

public class World extends State{
    //Hướng
    public static final int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;

    //Danh sách các phòng
    //index đầu tiên: tên thế giới chứa phòng
    //index thứ hai: tên phòng chơi
    private Room[][] worldMap;

    //phòng chơi người chơi đang đứng
    private Room currentRoom;

    private byte position = 1;

    //Load các phòng chơi
    public World(Handler handler){
        super(handler);
        worldMap = new Room[2][];
        worldMap[0] = new Room[2];
        worldMap[1] = new Room[2];

        setRoom(handler, 0, 0, "res/world/world0/room_0_0.txt", 4);
        setRoom(handler, 0, 1, "res/world/world0/room_0_1.txt", 2);
        setRoom(handler, 1, 0, "res/world/world1/room_1_0.txt", 5);
        setRoom(handler, 1, 1, "res/world/world1/room_1_1.txt", 3);

        currentRoom = worldMap[0][0];
    }

    //phương thức chuyển các phòng
    //khi người chơi ra phòng sẽ tìm phòng bên cạnh theo hướng ra đó 
    public void changeRoom(Player player){
        if (currentRoom.worldName == 0 && currentRoom.roomName == 1 
                && (int) player.getCenterX()/Game.TILE_WIDTH == 16 && (int) player.getCenterY()/Game.TILE_HEIGHT == 10
                && getRoom(0, 1).getNumOfEnemies() == 0){
            currentRoom = worldMap[1][0];
            player.setX(40f);
            player.setY(40f);
        }
        
        if (currentRoom.worldName == 1 && currentRoom.roomName == 1 
                && (int) player.getCenterX()/Game.TILE_WIDTH == 16 && (int) player.getCenterY()/Game.TILE_HEIGHT == 10
                && getRoom(0, 1).getNumOfEnemies() == 0){
            State.setState(handler.getGame().getWinGameState());
        }

        if (player.getCenterX() < 0){
            currentRoom = worldMap[currentRoom.worldName][currentRoom.getExit(WEST)];
            player.setCenterX(Game.WINDOW_WIDTH);
            position --;
        } else if (player.getCenterX() > Game.WINDOW_WIDTH){
            currentRoom = worldMap[currentRoom.worldName][currentRoom.getExit(EAST)];
            player.setCenterX(0);
            position ++;
        } else if (player.getCenterY() < 0){
            currentRoom = worldMap[currentRoom.worldName][currentRoom.getExit(NORTH)];
            player.setCenterY(Game.WINDOW_HEIGHT);
        } else if (player.getCenterY() > Game.WINDOW_HEIGHT){
            currentRoom = worldMap[currentRoom.worldName][currentRoom.getExit(SOUTH)];
            player.setCenterY(0);
        }
        
    }


    //Get Set

    //trả về phòng chơi hiện tại
    public Room getRoom(){
        return currentRoom;
    }

    //trả về phòng ở thế giới và tên phòng truyền vào
    //t chưa động tới vì t nghĩ khi chuyển các màn chơi mới gọi
    //sau khi giết Boss xong thì mới chuyển màn
    public Room getRoom(int worldName, int roomName){
        return worldMap[worldName][roomName];
    }

    public void setRoom(Handler handler, int thisWorld, int roomName, String path, int numOfEnemies){
        worldMap[thisWorld][roomName] = new Room();
        worldMap[thisWorld][roomName].loadRoom(path);
        worldMap[thisWorld][roomName].createEnemy(handler, numOfEnemies);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        
    }
}
