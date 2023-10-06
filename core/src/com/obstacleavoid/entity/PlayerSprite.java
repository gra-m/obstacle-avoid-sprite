package com.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obstacleavoid.config.GameConfig;

public class PlayerSprite extends BaseSprite {

    public PlayerSprite(TextureRegion region) {
        super(region, GameConfig.PLAYER_BOUNDS_RADIUS, GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);

    }
/*
    public Circle getBounds() {
        return bounds;
    }*/

/*
    public void drawDebug(ShapeRenderer renderer) {
        renderer.x(bounds.x, bounds.y, 0.1f);
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
        updateBounds();
    }

    private void updateBounds( )
    {
        if (bounds == null) {
            return;
        }
        float adjustmentAlignsCircleToTextureWidth = getWidth()/2f;
        float adjustmentAlignsCircleToTextureHeight = getHeight()/2f;
        bounds.setPosition(getX() + adjustmentAlignsCircleToTextureWidth, getY() + adjustmentAlignsCircleToTextureHeight );
    }
*/

    // from what I can see, these are not needed if update bounds is called directly from drawdebug.:


/*    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        updateBounds();
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        updateBounds();
    }*/

}
