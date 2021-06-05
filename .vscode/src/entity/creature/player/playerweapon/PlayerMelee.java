package entity.creature.player.playerweapon;

import java.awt.Graphics;
import java.awt.Rectangle;

import entity.creature.enemy.Enemy;
import main.Handler;

public class PlayerMelee extends PlayerWeapon {
    private int range;

    public PlayerMelee(Handler handler, int damage) {
        super(handler, damage);

        this.range = 100;
    }

    @Override
    public void damaging() {
        setDirect();
        Rectangle attackBox = getAttackBox();
        for (Enemy e : handler.getWorld().getRoom().getEnemyList()){
            if (e.getMovingBounds().intersects(attackBox)){
                e.decreaseHealth(this.damage);
                System.out.println("hit");
            }
        }    
    }

    private Rectangle getAttackBox(){
        Rectangle attackBox = new Rectangle();
        attackBox.width = this.range;
        attackBox.height = this.range;

        switch (this.direct) {
            case EAST:
                attackBox.x = (int) handler.getPlayer().getCenterX();
                attackBox.y = (int) handler.getPlayer().getCenterY() - range/2;
                break;
            case WEST:
                attackBox.x = (int) handler.getPlayer().getCenterX() - range;
                attackBox.y = (int) handler.getPlayer().getCenterY() - range/2;
                break;
            case SOUTH:
                attackBox.x = (int) handler.getPlayer().getCenterX() - range/2;
                attackBox.y = (int) handler.getPlayer().getCenterY();
                break;
            case NORTH:
                attackBox.x = (int) handler.getPlayer().getCenterX() - range/2;
                attackBox.y = (int) handler.getPlayer().getCenterY() - range;
                break;
            default:
                return new Rectangle(0, 0, 0, 0);
        }

        return attackBox;
    }

    
    @Override
    public void tick() {}

    @Override
    public void render(Graphics graphics) {}
    
}
