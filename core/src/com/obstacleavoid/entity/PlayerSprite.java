package com.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.obstacleavoid.config.GameConfig;

public class PlayerSprite extends GameSpriteBase {
    private float width = 1f;
    private float height = 1f;

    public PlayerSprite(TextureRegion region) {
        super(region, GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }


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
