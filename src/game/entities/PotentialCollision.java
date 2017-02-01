package game.entities;

/**
 * Created by wissamemekhilef on 02/02/2017.
 */
public class PotentialCollision {
    private boolean above;
    private boolean below;
    private boolean left;
    private boolean right;

    private Player player;
    private Obstacle obstacle;

    public PotentialCollision(Player p, Obstacle o){
        player = p;
        obstacle = o;

        above = false;
        below = false;
        left = false;
        right = false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public boolean isBelow() {
        return below;
    }

    public void setBelow(boolean below) {
        this.below = below;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isAbove() {
        return above;
    }

    public void setAbove(boolean above) {
        this.above = above;
    }
}
