package entity.bullet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.geom.Line2D;

import entity.Entity;
import entity.creature.enemy.Enemy;
import graphic.tile.Tile;
import main.Handler;

public class Bullet extends Entity {

    private boolean isFromPlayer, exploded;
    private int damage;
    private float[] vector;

    private float[] preCoord, newCoord;
    private Line2D.Float bulletPath;


    private BufferedImage[] frame;

    public Bullet(Handler handler, float startX, float startY, boolean isFromPlayer, int damage, float speed, double angle, BufferedImage[] bulletFrame) {
        super(handler, startX, startY, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        this.isFromPlayer = isFromPlayer;
        vector = new float[2];
        vector[0] = speed * (float) Math.cos(Math.toRadians(angle));
        vector[1] = speed * (float) Math.sin(Math.toRadians(angle));

        this.damage = damage;

        preCoord = new float[2];
        newCoord = new float[2];
        preCoord[0] = this.getCenterX();
        preCoord[1] = this.getCenterY();
        bulletPath = new Line2D.Float();

        this.exploded = false;

        this.frame = bulletFrame;
    }

    @Override
    public void tick() {
        this.x += vector[0];
        this.y += vector[1];

        newCoord[0] = this.getCenterX();
        newCoord[1] = this.getCenterY();

        bulletPath.setLine(preCoord[0], preCoord[1], newCoord[0], newCoord[1]);

        preCoord[0] = newCoord[0];
        preCoord[1] = newCoord[1];
        
        this.exploded = isDamaging() || isCollision();
    }

    private boolean isDamaging() {
        if (isFromPlayer) {
            for (Enemy e : handler.getWorld().getRoom().getEnemyList()){
                if (bulletPath.intersects(e.getMovingBounds())){
                    e.decreaseHealth(this.damage);
                    System.out.println("bullet hit");
                    return true;
                }
            }
        } else {
            if (bulletPath.intersects(handler.getPlayer().getMovingBounds())){
                handler.getPlayer().decreaseHealth(this.damage);
                System.out.println("bullet hit player");
                return true;
            }
        }

        return false;
    }

    private boolean isCollision(){
        int x = (int) getCenterX() / Tile.TILE_WIDTH;
        int y = (int) getCenterY() / Tile.TILE_HEIGHT;

        if (x < 0 || x > handler.getWorld().getRoom().WIDTH
                || y < 0 || y > handler.getWorld().getRoom().HEIGHT){
            return true;
        }

        return handler.getWorld().getRoom().getTile(x, y).isSolid();
    }

    @Override
    public void render(Graphics graphics) {
        if (exploded) graphics.drawImage(frame[1], (int) x, (int) y, width, height, null);
        else graphics.drawImage(frame[0], (int) x, (int) y, width, height, null);
    }

    public boolean getExploded(){
        return exploded;
    }
}
