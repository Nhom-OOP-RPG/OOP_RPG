//Lớp người chơi

package entity.creature.player;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import entity.creature.Creature;
import entity.creature.player.playerweapon.PlayerMelee;
import entity.creature.player.playerweapon.PlayerWeapon;
import graphic.Asset;
import graphic.tile.Tile;
import main.Handler;
import state.State;
import utility.Utility;


public class Player extends Creature {
    //vị trí spawn đầu màn chơi (tính theo pixel)
    public static final float DEFAULT_SPAWN_X = 40, DEFAULT_SPAWN_Y = 40; 

    //thế giới và phòng hiện tại
    //các này t tạo ra cho có mà chưa dùng làm gì
    int atWorld, atRoom = 0;

    private PlayerWeapon melee;
    public int attackDirect;
    private boolean isAttacking;
    private int attackDelayCount;
    private int attackDelay;

    public Player(Handler handler){
        super(handler, DEFAULT_SPAWN_X, DEFAULT_SPAWN_Y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 10;
        bounds.y = 10;
        bounds.width = 20;
        bounds.height = 30;

        melee = new PlayerMelee(handler, 20);
        isAttacking = false;
        attackDelayCount = 25;
        attackDelay = 25;

        currentFrame = Asset.player[0][0];
        currentDirect = 0;
        currentFrameID = 0;
    }

    @Override
    public void tick() {

        if (health <= 0) {
            isDead = true;
            State.setState(handler.getGame().getLoseGameState());
        }
        getInput();
        move();

        
        if (isAttacking){
            melee.damaging();
            isAttacking = false;
            System.out.println("attack");
            attackDelayCount = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {

        currentFrameUpdate();
        graphics.drawImage(currentFrame, (int) x, (int) y, width, height, null);
        graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.PLAIN, 15));
		graphics.drawImage(Utility.loadImage("res/texture/player/heart.png"), 0, 0, Tile.TILE_WIDTH*2/3, Tile.TILE_HEIGHT*2/3, null);
		graphics.drawString(getHealth()+" / 100", Tile.TILE_HEIGHT*2/3+5, 20);
    }
    
    //Kiểm tra input để cập nhật xMove, yMove
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up){
            yMove = -speed;
            attackDirect = 3;
        }
        if (handler.getKeyManager().down){
            yMove = speed;
            attackDirect = 2;
        }
        if (handler.getKeyManager().left){
            xMove = -speed;
            attackDirect = 1;
        }
        if (handler.getKeyManager().right){
            xMove = speed;
            attackDirect = 0;
        }

        attackDelayCount++;
        if(attackDelayCount >= attackDelay){
            if (handler.getKeyManager().attack){
                isAttacking = true;
            }
        }
    }

    //Chuyển đổi animation của người chơi
    @Override
    protected void currentFrameUpdate() {
        animationDelayCount++;
        if (xMove != 0 || yMove != 0){
            if (animationDelayCount >= animationDelay){
                animationDelayCount = 0;
                currentFrameID = 1 - currentFrameID;
            }
        }

        if (yMove > 0){
            currentDirect = 0;
        } else if (yMove < 0){
            currentDirect = 1;
        } else if (xMove < 0){
            currentDirect = 2;
        } else if (xMove > 0){
            currentDirect = 3;
        }

        if (isDamaged){
            currentFrame = Asset.playerDamaged[currentDirect][currentFrameID];
            if (animationDelayCount > animationDelay){
                isDamaged = false;
            }
        } else {
            currentFrame = Asset.player[currentDirect][currentFrameID];
        }
    }
    
    //Get Set

    //get set Center để lấy tọa độ trung tâm của người chơi
    //dùng trong chuyển các phòng
    public float getCenterX(){
        return x + width / 2;
    }

    public void setCenterX(float x){
        this.x = x - this.width / 2;
    }

    public float getCenterY(){
        return y + height / 2;
    }

    public void setCenterY(float y){
        this.y = y - this.height / 2;
    }
}