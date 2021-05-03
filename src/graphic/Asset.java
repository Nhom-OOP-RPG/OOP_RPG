//load các hình ảnh tài nguyên

package graphic;

import java.awt.image.BufferedImage;

import utility.*;

public class Asset {
    //private static final int width = 40, height = 40;
    
    public static BufferedImage floor, grass, stone, wall;
    public static BufferedImage[][] player, playerDamaged;
    public static BufferedImage[] enemy1, enemy1Damaged;

    public static void init(){
        //Tile
        floor = Utility.loadImage("res/texture/tile/floor.png");
        grass = Utility.loadImage("res/texture/tile/grass.png");
        stone = Utility.loadImage("res/texture/tile/stone.png");
        wall = Utility.loadImage("res/texture/tile/wall.png");

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

        playerDamaged = new BufferedImage[4][2];
        
        playerDamaged[0][0] = Utility.loadImage("res/texture/player/player_damaged.png");
        playerDamaged[0][1] = Utility.loadImage("res/texture/player/player_2_damaged.png");

        playerDamaged[1][0] = Utility.loadImage("res/texture/player/player_back_damaged.png");
        playerDamaged[1][1] = Utility.loadImage("res/texture/player/player_back_2_damaged.png");
        
        playerDamaged[2][0] = Utility.loadImage("res/texture/player/player_left_damaged.png");
        playerDamaged[2][1] = Utility.loadImage("res/texture/player/player_left_2_damaged.png");

        playerDamaged[3][0] = Utility.loadImage("res/texture/player/player_right_damaged.png");
        playerDamaged[3][1] = Utility.loadImage("res/texture/player/player_right_2_damaged.png");

        //Enemy
        //tạm thời mới có 1 loại quái
        enemy1 = new BufferedImage[2];
        enemy1[0] = Utility.loadImage("res/texture/enemy/enemy.png");
        enemy1[1] = Utility.loadImage("res/texture/enemy/enemy_2.png");
        enemy1Damaged = new BufferedImage[2];
        enemy1Damaged[0] = Utility.loadImage("res/texture/enemy/enemy_damaged.png");
        enemy1Damaged[1] = Utility.loadImage("res/texture/enemy/enemy_2_damaged.png");
    }
    
}