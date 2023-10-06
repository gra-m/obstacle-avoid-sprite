package com.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obstacleavoid.config.GameConfig;

public class PlayerSprite extends GameSpriteBase {
    private float width = 1f;
    private float height = 1f;

    public PlayerSprite(TextureRegion region) {
        super(region, GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

}
