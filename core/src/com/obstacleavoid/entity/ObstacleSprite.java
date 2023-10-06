package com.obstacleavoid.entity;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.obstacleavoid.config.GameConfig;

public class ObstacleSprite extends Sprite {
    private Circle bounds;

    public ObstacleSprite(TextureRegion region) {
        super(region);
        bounds = new Circle(getX(), getY(), GameConfig.OBSTACLE_BOUNDS_RADIUS);
        setSize(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);
    }
}
