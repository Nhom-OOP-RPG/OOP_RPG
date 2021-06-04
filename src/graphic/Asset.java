//load các hình ảnh tài nguyên

package graphic;

import java.awt.image.BufferedImage;

import utility.Utility;

public class Asset { 
    //Tile
    public static BufferedImage blank;
    public static BufferedImage gate;
    public static BufferedImage grass, earth, wall;
    public static BufferedImage rock, desertfloor, desertwall;

    //Player
    public static BufferedImage[][] player;

    //Enemy
    public static BufferedImage[] gummy, bat;
    public static BufferedImage[][] mushroom, snake, boss0, skull, goblin, boss1;

    //Other
    public static BufferedImage[] bulletPlayer, bulletPlayer_ultimate;
    public static BufferedImage[] bulletRed, bulletGreen, bulletRock, bulletFlame, bulletBomb;
    public static BufferedImage scratchGrey, scratchGummy, scratchRock, scratchRed, scratchVenom;
    public static BufferedImage[] cutGrey;
    public static BufferedImage heart, cutUltimateVertical, cutUltimateHorizontal;
    public static BufferedImage dead, deadBoss0, deadBoss1;

    //Item
    public static BufferedImage health, energy, speed, meleeDamage, gunDamage;
    public static BufferedImage health_effect, energy_effect;
    public static BufferedImage main0, winGame, mainMenu1, loseGame, instruction, chooseLevel, pauseGame, youDied;
    public static void init(){
        initTile();
        initPlayer();
        initEnemy();
        initItem();
        initOther();
    }
    
    private static void initPlayer(){
        //Player
        player = new BufferedImage[8][2];
        
        player[0][0] = Utility.loadImage("res/texture/player/player_right.png");
        player[0][1] = Utility.loadImage("res/texture/player/player_right_2.png");

        player[1][0] = Utility.loadImage("res/texture/player/player_left.png");
        player[1][1] = Utility.loadImage("res/texture/player/player_left_2.png");
        
        player[2][0] = Utility.loadImage("res/texture/player/player.png");
        player[2][1] = Utility.loadImage("res/texture/player/player_2.png");

        player[3][0] = Utility.loadImage("res/texture/player/player_back.png");
        player[3][1] = Utility.loadImage("res/texture/player/player_back_2.png");

        player[4][0] = Utility.loadImage("res/texture/player/player_right_damaged.png");
        player[4][1] = Utility.loadImage("res/texture/player/player_right_2_damaged.png");

        player[5][0] = Utility.loadImage("res/texture/player/player_left_damaged.png");
        player[5][1] = Utility.loadImage("res/texture/player/player_left_2_damaged.png");
        
        player[6][0] = Utility.loadImage("res/texture/player/player_damaged.png");
        player[6][1] = Utility.loadImage("res/texture/player/player_2_damaged.png");

        player[7][0] = Utility.loadImage("res/texture/player/player_back_damaged.png");
        player[7][1] = Utility.loadImage("res/texture/player/player_back_2_damaged.png");
    }

    private static void initEnemy(){
        //Enemy
        //World0
        gummy = new BufferedImage[4];
        gummy[0] = Utility.loadImage("res/texture/enemy/world0/gummy/gummy.png");
        gummy[1] = Utility.loadImage("res/texture/enemy/world0/gummy/gummy_2.png");
        gummy[2] = Utility.loadImage("res/texture/enemy/world0/gummy/gummy_damaged.png");
        gummy[3] = Utility.loadImage("res/texture/enemy/world0/gummy/gummy_2_damaged.png");

        mushroom = new BufferedImage[8][2];
        mushroom[0][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_right.png");
        mushroom[0][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_right_2.png");
        mushroom[1][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_left.png");
        mushroom[1][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_left_2.png");
        mushroom[2][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom.png");
        mushroom[2][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_2.png");
        mushroom[3][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_back.png");
        mushroom[3][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_back_2.png");
        mushroom[4][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_right_damaged.png");
        mushroom[4][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_right_2_damaged.png");
        mushroom[5][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_left_damaged.png");
        mushroom[5][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_left_2_damaged.png");
        mushroom[6][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_damaged.png");
        mushroom[6][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_2_damaged.png");
        mushroom[7][0] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_back_damaged.png");
        mushroom[7][1] = Utility.loadImage("res/texture/enemy/world0/mushroom/mushroom_back_2_damaged.png");

        snake = new BufferedImage[8][2];
        snake[0][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_right.png");
        snake[0][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_right_2.png");
        snake[1][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_left.png");
        snake[1][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_left_2.png");
        snake[2][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake.png");
        snake[2][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_2.png");
        snake[3][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_back.png");
        snake[3][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_back_2.png");
        snake[4][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_right_damaged.png");
        snake[4][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_right_2_damaged.png");
        snake[5][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_left_damaged.png");
        snake[5][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_left_2_damaged.png");
        snake[6][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_damaged.png");
        snake[6][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_2_damaged.png");
        snake[7][0] = Utility.loadImage("res/texture/enemy/world0/snake/snake_back_damaged.png");
        snake[7][1] = Utility.loadImage("res/texture/enemy/world0/snake/snake_back_2_damaged.png");

        boss0 = new BufferedImage[8][2];
        boss0[0][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_right.png");
        boss0[0][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_right_2.png");
        boss0[1][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_left.png");
        boss0[1][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_left_2.png");
        boss0[2][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0.png");
        boss0[2][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_2.png");
        boss0[3][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_back.png");
        boss0[3][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_back_2.png");
        boss0[4][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_right_damaged.png");
        boss0[4][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_right_2_damaged.png");
        boss0[5][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_left_damaged.png");
        boss0[5][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_left_2_damaged.png");
        boss0[6][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_damaged.png");
        boss0[6][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_2_damaged.png");
        boss0[7][0] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_back_damaged.png");
        boss0[7][1] = Utility.loadImage("res/texture/enemy/world0/boss0/boss0_back_2_damaged.png");

        //World1
        bat = new BufferedImage[4];
        bat[0] = Utility.loadImage("res/texture/enemy/world1/bat/bat.png");
        bat[1] = Utility.loadImage("res/texture/enemy/world1/bat/bat_2.png");
        bat[2] = Utility.loadImage("res/texture/enemy/world1/bat/bat_damaged.png");
        bat[3] = Utility.loadImage("res/texture/enemy/world1/bat/bat_2_damaged.png");

        skull = new BufferedImage[8][2];
        skull[0][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_right.png");
        skull[0][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_right_2.png");
        skull[1][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_left.png");
        skull[1][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_left_2.png");
        skull[2][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull.png");
        skull[2][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_2.png");
        skull[3][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_back.png");
        skull[3][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_back_2.png");
        skull[4][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_right_damaged.png");
        skull[4][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_right_2_damaged.png");
        skull[5][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_left_damaged.png");
        skull[5][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_left_2_damaged.png");
        skull[6][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_damaged.png");
        skull[6][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_2_damaged.png");
        skull[7][0] = Utility.loadImage("res/texture/enemy/world1/skull/skull_back_damaged.png");
        skull[7][1] = Utility.loadImage("res/texture/enemy/world1/skull/skull_back_2_damaged.png");

        goblin = new BufferedImage[8][2];
        goblin[0][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_right.png");
        goblin[0][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_right_2.png");
        goblin[1][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_left.png");
        goblin[1][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_left_2.png");
        goblin[2][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin.png");
        goblin[2][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_2.png");
        goblin[3][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_back.png");
        goblin[3][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_back_2.png");
        goblin[4][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_right_damaged.png");
        goblin[4][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_right_2_damaged.png");
        goblin[5][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_left_damaged.png");
        goblin[5][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_left_2_damaged.png");
        goblin[6][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_damaged.png");
        goblin[6][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_2_damaged.png");
        goblin[7][0] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_back_damaged.png");
        goblin[7][1] = Utility.loadImage("res/texture/enemy/world1/goblin/goblin_back_2_damaged.png");

        boss1 = new BufferedImage[8][2];
        boss1[0][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_right.png");
        boss1[0][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_right_2.png");
        boss1[1][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_left.png");
        boss1[1][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_left_2.png");
        boss1[2][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1.png");
        boss1[2][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_2.png");
        boss1[3][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_back.png");
        boss1[3][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_back_2.png");
        boss1[4][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_right_damaged.png");
        boss1[4][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_right_2_damaged.png");
        boss1[5][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_left_damaged.png");
        boss1[5][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_left_2_damaged.png");
        boss1[6][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_damaged.png");
        boss1[6][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_2_damaged.png");
        boss1[7][0] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_back_damaged.png");
        boss1[7][1] = Utility.loadImage("res/texture/enemy/world1/boss1/boss1_back_2_damaged.png");

    }

    private static void initTile(){
        blank = Utility.loadImage("res/texture/tile/blank.png");
        gate = Utility.loadImage("res/texture/tile/gate.png");

        grass = Utility.loadImage("res/texture/tile/world0/grass.png");
        earth = Utility.loadImage("res/texture/tile/world0/earth.png");
        wall = Utility.loadImage("res/texture/tile/world0/wall.png");

        rock = Utility.loadImage("res/texture/tile/world1/rock.png");
        desertfloor = Utility.loadImage("res/texture/tile/world1/desertfloor.png");
        desertwall = Utility.loadImage("res/texture/tile/world1/desertwall.png");
        main0 = Utility.loadImage("res/texture/tile/world2/wall.png");
        winGame = Utility.loadImage("res/texture/tile/world2/youwin.png");
        mainMenu1 = Utility.loadImage("res/texture/tile/world2/mainMenu1.png");
        loseGame = Utility.loadImage("res/texture/tile/world2/loseGame.jpg");
        instruction = Utility.loadImage("res/texture/tile/world2/instruction.png");
        chooseLevel = Utility.loadImage("res/texture/tile/world2/chooseLevel.jpg");
        pauseGame = Utility.loadImage("res/texture/tile/world2/pauseGame.jpg");
        youDied = Utility.loadImage("res/texture/tile/world2/youDied.jpg");
    }

    private static void initOther(){
        //Bullet
        bulletPlayer = new BufferedImage[2];
        bulletPlayer[0] = Utility.loadImage("res/texture/other/bullet/bulletPlayer.png");
        bulletPlayer[1] = Utility.loadImage("res/texture/other/bullet/bulletPlayer_explode.png");

        bulletPlayer_ultimate = new BufferedImage[2];
        bulletPlayer_ultimate[0] = Utility.loadImage("res/texture/other/bullet/bulletPlayer_ultimate.png");
        bulletPlayer_ultimate[1] = Utility.loadImage("res/texture/other/bullet/bulletPlayer_ultimate_explode.png");

        bulletRed = new BufferedImage[2];
        bulletRed[0] = Utility.loadImage("res/texture/other/bullet/bulletRed.png");
        bulletRed[1] = Utility.loadImage("res/texture/other/bullet/bulletRed_explode.png");

        bulletGreen = new BufferedImage[2];
        bulletGreen[0] = Utility.loadImage("res/texture/other/bullet/bulletGreen.png");
        bulletGreen[1] = Utility.loadImage("res/texture/other/bullet/bulletGreen_explode.png");

        bulletRock = new BufferedImage[2];
        bulletRock[0] = Utility.loadImage("res/texture/other/bullet/bulletRock.png");
        bulletRock[1] = Utility.loadImage("res/texture/other/bullet/bulletRock_explode.png");

        bulletFlame = new BufferedImage[2];
        bulletFlame[0] = Utility.loadImage("res/texture/other/bullet/bulletFlame.png");
        bulletFlame[1] = Utility.loadImage("res/texture/other/bullet/bulletFlame_explode.png");

        bulletBomb = new BufferedImage[2];
        bulletBomb[0] = Utility.loadImage("res/texture/other/bullet/bulletBomb.png");
        bulletBomb[1] = Utility.loadImage("res/texture/other/bullet/bulletBomb_explode.png");

        //Cut
        scratchGrey = Utility.loadImage("res/texture/other/cut/scratchGrey.png");
        scratchGummy = Utility.loadImage("res/texture/other/cut/scratchGummy.png");
        scratchRock = Utility.loadImage("res/texture/other/cut/scratchRock.png");
        scratchRed = Utility.loadImage("res/texture/other/cut/scratchRed.png");
        scratchVenom = Utility.loadImage("res/texture/other/cut/scratchVenom.png");

        cutGrey = new BufferedImage[4];
        cutGrey[0] = Utility.loadImage("res/texture/other/cut/cutGrey_right.png");
        cutGrey[1] = Utility.loadImage("res/texture/other/cut/cutGrey_left.png");
        cutGrey[2] = Utility.loadImage("res/texture/other/cut/cutGrey_down.png");
        cutGrey[3] = Utility.loadImage("res/texture/other/cut/cutGrey_up.png");

        cutUltimateVertical = Utility.loadImage("res/texture/other/cut/cutUltimate_vertical.png");
        cutUltimateHorizontal = Utility.loadImage("res/texture/other/cut/cutUltimate_horizontal.png");

        dead = Utility.loadImage("res/texture/other/dead/dead.png");
        deadBoss0 = Utility.loadImage("res/texture/other/dead/dead_boss0.png");
        deadBoss1 = Utility.loadImage("res/texture/other/dead/dead_boss1.png");

        heart = Utility.loadImage("res/texture/other/heart.png");
    }

    private static void initItem(){
        health = Utility.loadImage("res/texture/item/health.png");
        energy = Utility.loadImage("res/texture/item/energy.png");
        speed = Utility.loadImage("res/texture/item/speed.png");
        meleeDamage = Utility.loadImage("res/texture/item/melee.png");
        gunDamage = Utility.loadImage("res/texture/item/gun.png");

        //effect
        health_effect = Utility.loadImage("res/texture/other/effect/health_effect.png");
        energy_effect = Utility.loadImage("res/texture/other/effect/energy_effect.png");
    }
}