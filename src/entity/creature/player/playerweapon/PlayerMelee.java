package entity.creature.player.playerweapon;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.creature.enemy.Enemy;
import graphic.Asset;
import main.Handler;

public class PlayerMelee extends PlayerWeapon {
    private int range, ultimateRange;
    private Rectangle attackBox;

    private int frameID;
    private BufferedImage[] attackFrame;

    public PlayerMelee(Handler handler, int damage) {
        super(handler, damage);

        energy = 2;

        this.range = 60;
        this.ultimateRange = 300;
        attackBox = new Rectangle();

        frameID = 0;
        attackFrame = Asset.cutGrey;
    }

    @Override
    public void damaging() {
        if (!isUltimate && !handler.getPlayer().decreaseEnergy(energy)) {
            return;
        }
        setDirect();
        getAttackBox();
        for (Enemy e : handler.getWorld().getRoom().getEnemyList()){
            if (e.getMovingBounds().intersects(attackBox)){
                e.decreaseHealth(this.damage + isUltimateToInt * ultimateDamage);
                System.out.println("hit");
            }
        }
    }

    private void getAttackBox(){
        switch (this.direct) {
            case EAST:
                frameID = 0;
                attackBox.width = this.range + isUltimateToInt * ultimateRange;
                attackBox.height = 2 * this.range;
                attackBox.x = (int) handler.getPlayer().getCenterX();
                attackBox.y = (int) handler.getPlayer().getCenterY() - range;
                break;
            case WEST:
                frameID = 1;
                attackBox.width = this.range + isUltimateToInt * ultimateRange;
                attackBox.height = 2 * this.range;
                attackBox.x = (int) handler.getPlayer().getCenterX() - range - isUltimateToInt * ultimateRange;
                attackBox.y = (int) handler.getPlayer().getCenterY() - range;
                break;
            case SOUTH:
                frameID = 2;
                attackBox.width = 2 * this.range;
                attackBox.height = this.range + isUltimateToInt * ultimateRange;
                attackBox.x = (int) handler.getPlayer().getCenterX() - range;
                attackBox.y = (int) handler.getPlayer().getCenterY();
                break;
            case NORTH:
                frameID = 3;
                attackBox.width = 2 * this.range;
                attackBox.height = this.range + isUltimateToInt * ultimateRange;
                attackBox.x = (int) handler.getPlayer().getCenterX() - range;
                attackBox.y = (int) handler.getPlayer().getCenterY() - range - isUltimateToInt * ultimateRange;
                break;
            default:
                attackBox = new Rectangle(0, 0, 0, 0);
        }
    }

    
    @Override
    public void tick() {
        if (isUltimate){
            ultimateDelayCount++;
            if (ultimateDelayCount > ultimateDelay){
                isUltimate = false;
                isUltimateToInt = 0;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (isUltimate){
            if (direct == NORTH || direct == SOUTH){
                graphics.drawImage(Asset.cutUltimateVertical, attackBox.x, attackBox.y, attackBox.width, attackBox.height, null);
            } else {
                graphics.drawImage(Asset.cutUltimateHorizontal, attackBox.x, attackBox.y, attackBox.width, attackBox.height, null);
            }
        } else {
            graphics.drawImage(attackFrame[frameID], attackBox.x, attackBox.y, attackBox.width, attackBox.height, null);
        }

        if (isUltimate){
            int x = (int) handler.getPlayer().getX();
            int y = (int) handler.getPlayer().getY();
            float ratio = (float) (this. ultimateDelay - this.ultimateDelayCount) / this.ultimateDelay;
            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y - 10, (int) 40, 5);
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(x, y - 10, (int) (40 * ratio), 5);
        }

        attackBox = new Rectangle(0, 0, 0, 0);
    }

    @Override
    public void resetWeapon() {
        this.isUltimate = false;
        this.isUltimateToInt = 0;
        this.ultimateDelayCount = 0;
        this.attackFrame = Asset.cutGrey;
        this.frameID = 0;
    }
    
}
