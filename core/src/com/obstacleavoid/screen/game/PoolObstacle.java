package com.obstacleavoid.screen.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.obstacleavoid.common.EntityFactory;

public class PoolObstacle<T> extends Pool<T> {
    EntityFactory entityFactory;

    public PoolObstacle(EntityFactory entityFactory){
        this.entityFactory = entityFactory;
    }
    @Override
    protected T newObject() {
        return (T) entityFactory.createObstacle();
    }
}
