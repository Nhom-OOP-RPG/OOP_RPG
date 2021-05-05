/*
Lớp World: chứa dữ liệu liên quan tới các thế giới của các màn chơi
*/

package world;

import entity.creature.player.Player;
import main.Game;
import main.Handler;

public class World {
    public static final int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;
    //public static final int 


    Handler handler;
    //Danh sách các phòng
    //index đầu tiên: tên thế giới chứa phòng
    //index thứ hai: tên phòng chơi
    private Room[][] worldMap;

    //phòng chơi người chơi đang đứng
    private Room currentRoom;

    //Load các phòng chơi
    public World(Handler handler){
        this.handler = handler;

        init();
        initEasy();

        currentRoom = worldMap[0][0];
    }

    //phương thức chuyển các phòng
    //khi người chơi ra phòng sẽ tìm phòng bên cạnh theo hướng ra đó 
    public void changeRoom(Player player){
        if (player.getCenterX() < 0){
            currentRoom = worldMap[currentRoom.worldName][currentRoom.getExit(WEST)];
            player.setChangeRoomX(Game.WINDOW_WIDTH);
        } else if (player.getCenterX() > Game.WINDOW_WIDTH){
            currentRoom = worldMap[currentRoom.worldName][currentRoom.getExit(EAST)];
            player.setChangeRoomX(0);
        } else if (player.getCenterY() < 0){
            currentRoom = worldMap[currentRoom.worldName][currentRoom.getExit(NORTH)];
            player.setChangeRoomY(Game.WINDOW_HEIGHT);
        } else if (player.getCenterY() > Game.WINDOW_HEIGHT){
            currentRoom = worldMap[currentRoom.worldName][currentRoom.getExit(SOUTH)];
            player.setChangeRoomY(0);
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

    public void setRoom(Handler handler, int thisWorld, int roomName, String path){
        worldMap[thisWorld][roomName] = new Room();
        worldMap[thisWorld][roomName].loadRoom(path);
    }

    public void setBossRoom(Handler handler, int thisWorld, int roomName, String path){
        worldMap[thisWorld][roomName] = new BossRoom();
        worldMap[thisWorld][roomName].loadRoom(path);
    }

    public void init() {
        worldMap = new Room[2][];
        worldMap[0] = new Room[3];
        worldMap[1] = new Room[2];

        setRoom(this.handler, 0, 0, "res/world/world0/room_0_0.txt");
        setRoom(this.handler, 0, 1, "res/world/world0/room_0_1.txt");
        setBossRoom(this.handler, 0, 2, "res/world/world0/room_0_2.txt");
        setRoom(this.handler, 1, 0, "res/world/world1/room_1_0.txt");
        setRoom(this.handler, 1, 1, "res/world/world1/room_1_1.txt");
    }

    public void initEasy(){
        worldMap[0][0].addNewEnemy(handler, 1, 3);
        worldMap[0][0].addNewEnemy(handler, 2, 3);
        worldMap[0][1].addNewEnemy(handler, 2);
        worldMap[0][2].addNewEnemy(handler, 3, 16, 10);

        worldMap[1][0].addNewEnemy(handler, 1, 2);
        worldMap[1][0].addNewEnemy(handler, 2, 4);
        worldMap[1][1].addNewEnemy(handler, 1);
    }
}
