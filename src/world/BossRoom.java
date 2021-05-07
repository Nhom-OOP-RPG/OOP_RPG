package world;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import entity.creature.enemy.world0.Boss0;
import entity.creature.enemy.world1.Boss1;
import entity.creature.enemy.Enemy;
import graphic.Asset;
import graphic.tile.Tile;
import main.Handler;
import state.State;

public class BossRoom extends Room {

    public BossRoom(Handler handler, String path) {
        super(handler, path);
        
        if (worldName == 0){
            enemyList.add(new Boss0(handler, 9 * 40, 6 * 40));
        } else if (worldName == 1) {
            enemyList.add(new Boss1(handler, 13 * 40, 6 * 40));
        }
    }

    @Override
    public void tick(){
        if (enemyList.get(0).getIsDead()){
            if ((int) handler.getPlayer().getCenterX()/40 == 9
                    && (int) handler.getPlayer().getCenterY()/40 == 6){
                if (worldName == 0){
                    handler.getWorld().setCurrentRoom(1, 0);
                    handler.getPlayer().setX(9 * 40f);
                    handler.getPlayer().setY(7 * 40f);
                } else if (worldName == 1){
                    State.setState(handler.getGame().getWinGameState());
                }
            }
        }

        for (Enemy e : this.enemyList){
                e.tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                getTile(x, y).render(graphics, x, y);
            }
        }

        graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.PLAIN, 15));
		graphics.drawString("Boss of World " + worldName, Tile.TILE_HEIGHT * 2/3 + 600, 20);

        if (enemyList.get(0).getIsDead()){
            graphics.drawImage(Asset.gate, 9 * 40, 6 * 40, 40, 40, null);
        }

        for (Enemy e : enemyList){
            e.render(graphics);
        }

    }
}
