package TowerDefense;

import com.codingame.game.Player;

public class BuildAction {
    private Player player;
    private int x;
    private int y;
    private String type;

    public BuildAction(Player player, int x, int y, String type) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
