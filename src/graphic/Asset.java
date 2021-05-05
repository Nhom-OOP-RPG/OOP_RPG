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
    public static BufferedImage[] gummy, bat;
    public static BufferedImage[][] mushroom, snake, boss0, skull, goblin, boss1;

    //Other
    public static BufferedImage[] bulletRed, bulletRock, bulletFlame;
    public static BufferedImage scratchGrey, scratchGummy;
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
        
        player[0][0] = Utility.loadImage("res/texture/player/player_right.png");
        player[0][1] = Utility.loadImage("res/texture/player/player_right_2.png");

        player[1][0] = Utility.loadImage("res/texture/player/player_left.png");
        player[1][1] = Utility.loadImage("res/texture/player/player_left_2.png");
        
        player[2][0] = Utility.loadImage("res/texture/player/player.png");
        player[2][1] = Utility.loadImage("res/texture/player/player_2.png");

        player[3][0] = Utility.loadImage("res/texture/player/player_back.png");
        player[3][1] = Utility.loadImage("res/texture/player/player_back_2.png");
    }

    private static void initEnemy(){
        //Enemy
        //World0
        gummy = new BufferedImage[2];
        gummy[0] = Utility.loadImage("res/texture/enemy/world0/gummy/gummy.png");
        gummy[1] = Utility.loadImage("res/texture/enemy/world0/gummy/gummy_2.png");

        mushroom = new BufferedImage[4][2];
        mushroom[0][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_right.png");
        mushroom[0][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_right_2.png");
        mushroom[1][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_left.png");
        mushroom[1][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_left_2.png");
        mushroom[2][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom.png");
        mushroom[2][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_2.png");
        mushroom[3][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_back.png");
        mushroom[3][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_back_2.png");

        snake = new BufferedImage[4][2];
        snake[0][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_right.png");
        snake[0][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_right_2.png");
        snake[1][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_left.png");
        snake[1][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_left_2.png");
        snake[2][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake.png");
        snake[2][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_2.png");
        snake[3][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_back.png");
        snake[3][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_back_2.png");

        boss0 = new BufferedImage[4][2];
        boss0[0][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_right.png");
        boss0[0][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_right_2.png");
        boss0[1][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_left.png");
        boss0[1][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_left_2.png");
        boss0[2][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0.png");
        boss0[2][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_2.png");
        boss0[3][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_back.png");
        boss0[3][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_back_2.png");

        //World1
        bat = new BufferedImage[2];
        bat[0] = Utility.loadImage("res/texture/enemy/world1/bat/bat.png");
        bat[1] = Utility.loadImage("res/texture/enemy/world1/bat/bat_2.png");

        skull = new BufferedImage[4][2];
        skull[0][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_right.png");
        skull[0][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_right_2.png");
        skull[1][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_left.png");
        skull[1][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_left_2.png");
        skull[2][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull.png");
        skull[2][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_2.png");
        skull[3][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_back.png");
        skull[3][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_back_2.png");

        goblin = new BufferedImage[4][2];
        goblin[0][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_right.png");
        goblin[0][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_right_2.png");
        goblin[1][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_left.png");
        goblin[1][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_left_2.png");
        goblin[2][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin.png");
        goblin[2][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_2.png");
        goblin[3][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_back.png");
        goblin[3][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_back_2.png");

        boss1 = new BufferedImage[4][2];
        boss1[0][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_right.png");
        boss1[0][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_right_2.png");
        boss1[1][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_left.png");
        boss1[1][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_left_2.png");
        boss1[2][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1.png");
        boss1[2][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_2.png");
        boss1[3][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_back.png");
        boss1[3][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_back_2.png");

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
        scratchGrey = Utility.loadImage("res/texture/other/cut/scratchGrey.png");
        scratchGummy = Utility.loadImage("res/texture/other/cut/scratchGummy.png");

        cutGrey = new BufferedImage[4];
        cutGrey[0] = Utility.loadImage("res/texture/other/cut/cutGrey_right.png");
        cutGrey[1] = Utility.loadImage("res/texture/other/cut/cutGrey_left.png");
        cutGrey[2] = Utility.loadImage("res/texture/other/cut/cutGrey_down.png");
        cutGrey[3] = Utility.loadImage("res/texture/other/cut/cutGrey_up.png");

        dead = Utility.loadImage("res/texture/other/dead.png");
        heart = Utility.loadImage("res/texture/other/heart.png");
    }
}