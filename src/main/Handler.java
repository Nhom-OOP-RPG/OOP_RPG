/*
Lớp Handler: thay thế cho việc gọi lớp World và Game trong các lớp khác
-> chỉ cần gọi Handler để quản lý các thành phần cần thiết. 
*/

package main;

import entity.creature.player.Player;
import input.KeyManager;
import world.World;

public class Handler{
    private Game game;
    private World world;
    Player player;

    public Handler(Game game){
        this.game = game;

        player = new Player(this);
        world = new World(this);
    }

    public void restartGame(){
        player = new Player(this);
        world = new World(this);
    }

    //Get Set
    //Game
    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //World
    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    //KeyManager
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    //Window width & height
    public int getWindowWidth(){
        return game.getWindowWidth();
    }

    public int getWindowHeight(){
        return game.getWindowHeight();
    }

    //Player
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}