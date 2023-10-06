package com.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.obstacleavoid.config.GameConfig;

public class BaseSprite extends Sprite {

    private Circle bounds;
    public BaseSprite(TextureRegion region, float boundsRadius, float width, float height){
        super(region);
        bounds = new Circle(getX(), getY(), boundsRadius);
        setSize(width, height);
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
        if (bounds == null) {
            return;
        }
        float adjustmentAlignsCircleToTextureWidth = getWidth()/2f;
        float adjustmentAlignsCircleToTextureHeight = getHeight()/2f;
        bounds.setPosition(getX() + adjustmentAlignsCircleToTextureWidth, getY() + adjustmentAlignsCircleToTextureHeight );
    }
}
