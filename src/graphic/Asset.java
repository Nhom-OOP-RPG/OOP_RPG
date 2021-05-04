//load các hình ảnh tài nguyên

package graphic;

import java.awt.image.BufferedImage;

import utility.*;

public class Asset {
    //private static final int width = 40, height = 40;
    
    public static BufferedImage blank, floor, grass, earth, wall;
    public static BufferedImage[][] player;
    public static BufferedImage[] gummy;
    public static BufferedImage[] bulletRed;
    public static BufferedImage dead;

    public static void init(){
        initTile();
        initPlayer();
        initEnemy();
        initOther();
    }
    
    private static void initPlayer(){
        //Player
        player = new BufferedImage[4][2];
        
        player[0][0] = Utility.loadImage("res/texture/player/player.png");
        player[0][1] = Utility.loadImage("res/texture/player/player_2.png");

        player[1][0] = Utility.loadImage("res/texture/player/player_back.png");
        player[1][1] = Utility.loadImage("res/texture/player/player_back_2.png");
        
        player[2][0] = Utility.loadImage("res/texture/player/player_left.png");
        player[2][1] = Utility.loadImage("res/texture/player/player_left_2.png");

        player[3][0] = Utility.loadImage("res/texture/player/player_right.png");
        player[3][1] = Utility.loadImage("res/texture/player/player_right_2.png");
    }

    private static void initEnemy(){
        //Enemy
        //tạm thời mới có 1 loại quái
        gummy = new BufferedImage[2];
        gummy[0] = Utility.loadImage("res/texture/enemy/world0/gummy/gummy.png");
        gummy[1] = Utility.loadImage("res/texture/enemy/world0/gummy/gummy_2.png");

    }

    private static void initTile(){
        //Tile
        floor = Utility.loadImage("res/texture/tile/world1/concretefloor.png");
        grass = Utility.loadImage("res/texture/tile/world0/grass.png");
        earth = Utility.loadImage("res/texture/tile/world0/earth.png");
        wall = Utility.loadImage("res/texture/tile/world0/wall.png");
        blank = Utility.loadImage("res/texture/tile/blank.png");
    }

    private static void initOther(){
        //Bullet1
        //demo
        bulletRed = new BufferedImage[2];
        bulletRed[0] = Utility.loadImage("res/texture/other/bulletRock.png");
        bulletRed[1] = Utility.loadImage("res/texture/other/bulletRock_explode.png");

        //Dead
        dead = Utility.loadImage("res/texture/other/dead.png");
    }
}