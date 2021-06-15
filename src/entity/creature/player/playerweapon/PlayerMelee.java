package entity.creature.player.playerweapon;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

import entity.creature.enemy.Enemy;
import graphic.Asset;
import graphic.tile.Tile;
import main.Game;
import main.Handler;

public class PlayerMelee extends PlayerWeapon {
    private int range, isUltimateToInt, ultimateRange;
    private Rectangle attackBox;

    private int frameID;

    public PlayerMelee(Handler handler, int damage) {
        super(handler, damage);

        energy = 2;

        this.range = 60;
        this.ultimateRange = 300;
        attackBox = new Rectangle();

        frameID = 0;
    }

    @Override
    public void damaging() {
        if (!isUltimate && !handler.getPlayer().decreaseEnergy(energy)) {
            return;
        }
        isUltimateToInt = isUltimate ? 1 : 0;
        setDirect();
        getAttackBox();
        for (Enemy e : handler.getWorld().getRoom().getEnemyList()){
            if (e.getMovingBounds().intersects(attackBox)){
                e.decreaseHealth(this.damage * (1 + isUltimateToInt * 2));
                System.out.println("hit");
            }
        }
    }

    //tạo khu vực chịu sát thương
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

            if (ultimateDelayCount % 4 == 0){
                ultimateEffectFrameID = 1 - ultimateEffectFrameID;
            }

            if (ultimateDelayCount > ultimateDelay){
                isUltimate = false;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Asset.meleeImage, Game.WINDOW_WIDTH- Tile.TILE_WIDTH, 5 , 35, 27, null);

        if (isUltimate){
            graphics.drawImage(Asset.cutUltimate[frameID], attackBox.x, attackBox.y, attackBox.width, attackBox.height, null);

            int x = (int) handler.getPlayer().getX();
            int y = (int) handler.getPlayer().getY();

            graphics.drawImage(Asset.ultimate_effect[ultimateEffectFrameID], x - 10, y, 60, 40, null);

            float ratio = (float) (ultimateDelay - ultimateDelayCount) / ultimateDelay;
            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y - 10, (int) 40, 5);
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(x, y - 10, (int) (40 * ratio), 5);

        } else {
            graphics.drawImage(Asset.cutGrey[frameID], attackBox.x, attackBox.y, attackBox.width, attackBox.height, null);
        }

        attackBox = new Rectangle(0, 0, 0, 0);
    }

    @Override
    public void resetWeapon() {
        isUltimate = false;
        ultimateDelayCount = 0;
        this.frameID = 0;
    }

    @Override
    public void ultimate() {
        if (!isUltimate && handler.getPlayer().decreaseEnergy(ultimateEnergy)){
            isUltimate = true;
            ultimateDelayCount = 0;
        }
    }
    
}
