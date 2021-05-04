//load các hình ảnh tài nguyên

package graphic;

import java.awt.image.BufferedImage;

import utility.Utility;

public class Asset { 
    //Tile
    public static BufferedImage blank;
    public static BufferedImage gate;
    public static BufferedImage grass, earth, wall;
    public static BufferedImage concretefloor, desertfloor, desertwall;

    //Player
    public static BufferedImage[][] player;

    //Enemy
    public static BufferedImage[] gummy;

    //Other
    public static BufferedImage[] bulletRed, bulletRock, bulletFlame;
    public static BufferedImage[] cutGrey;
    public static BufferedImage dead, heart;

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
        blank = Utility.loadImage("res/texture/tile/blank.png");
        gate = Utility.loadImage("res/texture/tile/gate.png");

        grass = Utility.loadImage("res/texture/tile/world0/grass.png");
        earth = Utility.loadImage("res/texture/tile/world0/earth.png");
        wall = Utility.loadImage("res/texture/tile/world0/wall.png");

        concretefloor = Utility.loadImage("res/texture/tile/world1/concretefloor.png");
        desertfloor = Utility.loadImage("res/texture/tile/world1/desertfloor.png");
        desertwall = Utility.loadImage("res/texture/tile/world1/desertwall.png");
    }

    private static void initOther(){
        //Bullet
        bulletRed = new BufferedImage[2];
        bulletRed[0] = Utility.loadImage("res/texture/other/bullet/bulletRed.png");
        bulletRed[1] = Utility.loadImage("res/texture/other/bullet/bulletRed_explode.png");

        bulletRock = new BufferedImage[2];
        bulletRock[0] = Utility.loadImage("res/texture/other/bullet/bulletRock.png");
        bulletRock[1] = Utility.loadImage("res/texture/other/bullet/bulletRock_explode.png");

        bulletFlame = new BufferedImage[2];
        bulletFlame[0] = Utility.loadImage("res/texture/other/bullet/bulletFlame.png");
        bulletFlame[1] = Utility.loadImage("res/texture/other/bullet/bulletFlame_explode.png");

        //Cut
        cutGrey = new BufferedImage[4];
        cutGrey[0] = Utility.loadImage("res/texture/other/cut/cutGrey_right.png");
        cutGrey[1] = Utility.loadImage("res/texture/other/cut/cutGrey_left.png");
        cutGrey[2] = Utility.loadImage("res/texture/other/cut/cutGrey_down.png");
        cutGrey[3] = Utility.loadImage("res/texture/other/cut/cutGrey_up.png");

        dead = Utility.loadImage("res/texture/other/dead.png");
        heart = Utility.loadImage("res/texture/other/heart.png");
    }
}