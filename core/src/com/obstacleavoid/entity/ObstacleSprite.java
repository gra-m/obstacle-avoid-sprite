package com.obstacleavoid.entity;

import static com.obstacleavoid.config.GameConfig.OBSTACLE_BOUNDS_RADIUS;
import static com.obstacleavoid.config.GameConfig.OBSTACLE_SIZE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Pool;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.config.GameDifficulty;

public class ObstacleSprite extends GameSpriteBase implements Pool.Poolable
{
    private boolean hitAlready;

    private float ySpeed = GameDifficulty.MEDIUM.getObjectSpeed();

    public ObstacleSprite(TextureRegion region){
        super(region, GameConfig.OBSTACLE_BOUNDS_RADIUS);
        setSize( OBSTACLE_SIZE, OBSTACLE_SIZE );
    }

    public void update() {
        super.setY(  super.getY() - ySpeed);
    }

    public boolean isPlayerColliding(PlayerSprite player) {
        Circle playerBounds = player.getBounds();

        hitAlready = Intersector.overlaps( playerBounds, getBounds() );

        return hitAlready;
    }

    public boolean notHitAlready() {
        return !hitAlready;
    }


    public void setDifficulty( float difficultySpeed ) {
        this.ySpeed = difficultySpeed;
    }

    @Override
    public void reset( )
    {
        hitAlready = false;
    }

}
