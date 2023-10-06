package com.obstacleavoid.entity;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Pool;
import com.obstacleavoid.config.GameConfig;

public class ObstacleSprite extends BaseSprite implements Pool.Poolable {

    private boolean hitAlready;
    private float objectSpeed = GameConfig.MEDIUM_OBSTACLE_SPEED;

    public ObstacleSprite() {
        //
    }
    public ObstacleSprite(TextureRegion region) {
        super(region, GameConfig.OBSTACLE_BOUNDS_RADIUS, GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);
    }

    public boolean isPlayerColliding(PlayerSprite player) {
        Circle playerBounds = player.getBounds();

        if (player.getBounds() != null && this.getBounds() != null)

        hitAlready = Intersector.overlaps( playerBounds, this.getBounds() );

        return hitAlready;
    }

    public boolean notHitAlready() {
        return !hitAlready;
    }

    public void setDifficulty(float objectSpeed) {
        this.objectSpeed = objectSpeed;
    }

    @Override
    public void reset() {
        hitAlready = false;
    }
}
