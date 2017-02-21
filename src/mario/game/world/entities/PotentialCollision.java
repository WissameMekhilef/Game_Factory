package mario.game.world.entities;

/**
 * Created by wissamemekhilef on 02/02/2017.
 */
public class PotentialCollision {
    private boolean above;
    private boolean below;
    private boolean left;
    private boolean right;

    private Player player;
    private Solid obstacle;

    public PotentialCollision(Player p, Solid o){
        player = p;
        obstacle = o;

        above = true;
        below = true;
        left = true;
        right = true;
    }

    public Player getPlayer() {
        return player;
    }

    public Solid getSolid() {
        return obstacle;
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
