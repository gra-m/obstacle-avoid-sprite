package com.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.screen.game.GameController;

public class PlayerSprite extends Sprite {
    private float width = 1f;
    private float height = 1f;
    private Circle bounds;

    public PlayerSprite(TextureRegion region) {
        super(region);
        bounds = new Circle(getX(), getY(), GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

    public Circle getBounds() {
        return bounds;
    }

    public void drawDebug(ShapeRenderer renderer) {
        renderer.x(bounds.x, bounds.y, 0.1f);
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
        updateBounds();
    }

    private void updateBounds( )
    {
        float adjustmentAlignsCircleToTextureWidth = getWidth()/2f;
        float adjustmentAlignsCircleToTextureHeight = getHeight()/2f;
        bounds.setPosition(getX() + adjustmentAlignsCircleToTextureWidth, getY() + adjustmentAlignsCircleToTextureHeight );
    }

    // from what I can see, these are not needed if update bounds is called directly from drawdebug.:


    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        updateBounds();
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        updateBounds();
    }

}
