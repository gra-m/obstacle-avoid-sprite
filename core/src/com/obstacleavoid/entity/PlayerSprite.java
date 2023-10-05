package com.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

public class PlayerSprite extends Sprite {
    private float width = 1f;
    private float height = 1f;
    private Circle bounds;

    public PlayerSprite(TextureRegion region) {
        super(region);

    }
}
