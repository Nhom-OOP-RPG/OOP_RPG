package entity.creature.player.playerweapon;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.creature.enemy.Enemy;
import graphic.Asset;
import main.Handler;

public class PlayerMelee extends PlayerWeapon {
    private int range;
    private Rectangle attackBox;

    private int frameID;
    private BufferedImage[] attackFrame;

    public PlayerMelee(Handler handler, int damage) {
        super(handler, damage);

        this.range = 60;
        attackBox = new Rectangle();

        frameID = 0;
        attackFrame = Asset.cutGrey;
    }

    @Override
    public void damaging() {
        setDirect();
        getAttackBox();
        for (Enemy e : handler.getWorld().getRoom().getEnemyList()){
            if (e.getMovingBounds().intersects(attackBox)){
                e.decreaseHealth(this.damage);
                System.out.println("hit");
            }
        }    
    }

    private void getAttackBox(){
        attackBox.width = this.range;
        attackBox.height = this.range;

        switch (this.direct) {
            case EAST:
                frameID = 0;
                attackBox.width = this.range;
                attackBox.height = 2 * this.range;
                attackBox.x = (int) handler.getPlayer().getCenterX();
                attackBox.y = (int) handler.getPlayer().getCenterY() - range;
                break;
            case WEST:
                frameID = 1;
                attackBox.width = this.range;
                attackBox.height = 2 * this.range;
                attackBox.x = (int) handler.getPlayer().getCenterX() - range;
                attackBox.y = (int) handler.getPlayer().getCenterY() - range;
                break;
            case SOUTH:
                frameID = 2;
                attackBox.width = 2 * this.range;
                attackBox.height = this.range;
                attackBox.x = (int) handler.getPlayer().getCenterX() - range;
                attackBox.y = (int) handler.getPlayer().getCenterY();
                break;
            case NORTH:
                frameID = 3;
                attackBox.width = 2 * this.range;
                attackBox.height = this.range;
                attackBox.x = (int) handler.getPlayer().getCenterX() - range;
                attackBox.y = (int) handler.getPlayer().getCenterY() - range;
                break;
            default:
                attackBox = new Rectangle(0, 0, 0, 0);
        }
    }

    
    @Override
    public void tick() {}

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(attackFrame[frameID], attackBox.x, attackBox.y, attackBox.width, attackBox.height, null);
        attackBox = new Rectangle(0, 0, 0, 0);
    }
    
}
