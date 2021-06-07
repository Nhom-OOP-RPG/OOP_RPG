/*
Lớp World: chứa dữ liệu liên quan tới các thế giới của các màn chơi
*/

package world;

import entity.creature.player.Player;
import main.Game;
import main.Handler;

public class World {
    public static final int EAST = 0, WEST = 1, SOUTH = 2, NORTH = 3;
    public static final int DEMO = 0, EASY = 1, HARD = 2;

    Handler handler;

    //Mức độ khó dễ
    int level;

    //Danh sách các phòng
    //index đầu tiên: tên thế giới chứa phòng
    //index thứ hai: tên phòng chơi
    private Room[][] worldMap;

    //phòng chơi người chơi đang đứng
    private Room currentRoom;

    public World(Handler handler, int level){
        this.handler = handler;
        this.level = level;

        //Khởi tạo thế giới
        init();

        //set phòng 0, thế giới 0 làm xuất phát
        currentRoom = worldMap[0][0];
    }

    //phương thức chuyển các phòng
    //khi người chơi ra phòng sẽ tìm phòng bên cạnh theo hướng ra đó 
    public void changeRoom(Player player){
        if (player.getCenterX() < 0){
            setCurrentRoom(currentRoom.worldName, currentRoom.getExit(WEST));
            player.setChangeRoomX(Game.WINDOW_WIDTH);
        } else if (player.getCenterX() > Game.WINDOW_WIDTH){
            setCurrentRoom(currentRoom.worldName, currentRoom.getExit(EAST));
            player.setChangeRoomX(0);
        } else if (player.getCenterY() < 0){
            setCurrentRoom(currentRoom.worldName, currentRoom.getExit(NORTH));
            player.setChangeRoomY(Game.WINDOW_HEIGHT);
        } else if (player.getCenterY() > Game.WINDOW_HEIGHT){
            setCurrentRoom(currentRoom.worldName, currentRoom.getExit(SOUTH));
            player.setChangeRoomY(0);
        }
    }
    
    //Init phòng chơi mới
    private void init() {
        worldMap = new Room[2][];
        worldMap[0] = new Room[5];
        worldMap[1] = new Room[5];

        //load map
        setRoom(this.handler, 0, 0, "res/world/world0/room_0_0.txt");
        setRoom(this.handler, 0, 1, "res/world/world0/room_0_1.txt");
        setRoom(this.handler, 0, 2, "res/world/world0/room_0_2.txt");
        setRoom(this.handler, 0, 3, "res/world/world0/room_0_3.txt");
        setBossRoom(this.handler, 0, 4, "res/world/world0/room_0_4.txt");
        setRoom(this.handler, 1, 0, "res/world/world1/room_1_0.txt");
        setRoom(this.handler, 1, 1, "res/world/world1/room_1_1.txt");
        setRoom(this.handler, 1, 2, "res/world/world1/room_1_2.txt");
        setRoom(this.handler, 1, 3, "res/world/world1/room_1_3.txt");
        setBossRoom(this.handler, 1, 4, "res/world/world1/room_1_4.txt");

        //init enemy theo mức độ
        switch (level) {
            case DEMO:
                initEnemyDemo();
                break;
            case EASY:
                initEnemyEasy();
                break;
            case HARD:
                initEnemyHard();
        }
    }

    //Khởi tại level demo
    private void initEnemyDemo(){
        //World0
        //Room1
        worldMap[0][1].addNewEnemy(Room.GUMMY, 1);
        //Room2
        worldMap[0][2].addNewEnemy(Room.MUSHROOM, 1);
        //Room3
        worldMap[0][3].addNewEnemy(Room.SNAKE, 1);

        //World1
        //Room1
        worldMap[1][1].addNewEnemy(Room.BAT, 1);
        //Room2
        worldMap[1][2].addNewEnemy(Room.SKULL, 1);
        //Boss1
        worldMap[1][3].addNewEnemy(Room.GOBLIN, 1);
    }

    //level dễ
    private void initEnemyEasy(){
        //World0
        //Room1
        worldMap[0][1].addNewEnemy(Room.GUMMY, 2);
        //Room2
        worldMap[0][2].addNewEnemy(Room.GUMMY,3);
        worldMap[0][2].addNewEnemy(Room.MUSHROOM, 1);
        //Room3
        worldMap[0][3].addNewEnemy(Room.MUSHROOM, 2);
        worldMap[0][3].addNewEnemy(Room.SNAKE, 2);

        //World1
        //Room1
        worldMap[1][1].addNewEnemy(Room.BAT, 2);
        worldMap[1][1].addNewEnemy(Room.SKULL, 2);
        //Room2
        worldMap[1][2].addNewEnemy(Room.SKULL, 3);
        worldMap[1][2].addNewEnemy(Room.GOBLIN, 2);
        //Room3
        worldMap[1][3].addNewEnemy(Room.GOBLIN, 2);
    }

    //level khó
    private void initEnemyHard(){
        //World0
        //Room1
        worldMap[0][1].addNewEnemy(Room.GUMMY, 5);
        //Room2
        worldMap[0][2].addNewEnemy(Room.GUMMY,4);
        worldMap[0][2].addNewEnemy(Room.MUSHROOM, 3);
        //Room3
        worldMap[0][3].addNewEnemy(Room.MUSHROOM, 3);
        worldMap[0][3].addNewEnemy(Room.SNAKE, 3);
        //Boss0
        worldMap[0][4].addNewEnemy(Room.SNAKE, 2);

        //World1
        //Room1
        worldMap[1][1].addNewEnemy(Room.BAT, 4);
        worldMap[1][1].addNewEnemy(Room.SKULL, 3);
        //Room2
        worldMap[1][2].addNewEnemy(Room.SKULL, 6);
        worldMap[1][2].addNewEnemy(Room.GOBLIN, 4);
        //Room3
        worldMap[1][3].addNewEnemy(Room.GOBLIN, 4);
        //Boss1
        worldMap[1][4].addNewEnemy(Room.GOBLIN, 2);
    }

    //Get Set

    //trả về phòng chơi hiện tại
    public Room getRoom(){
        return currentRoom;
    }

    public Room getRoom(int worldName, int roomName){
        return worldMap[worldName][roomName];
    }

    public void setCurrentRoom(int roomName){
        currentRoom = worldMap[currentRoom.worldName][roomName];
    }

    public void setCurrentRoom(int worldName, int roomName){
        currentRoom = worldMap[worldName][roomName];
    }

    public void setRoom(Handler handler, int worldName, int roomName, String path){
        worldMap[worldName][roomName] = new Room(handler, path, level);
    }

    public void setBossRoom(Handler handler, int worldName, int roomName, String path){
        worldMap[worldName][roomName] = new BossRoom(handler, path, level);
    }
}
