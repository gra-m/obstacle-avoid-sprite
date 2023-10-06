package com.obstacleavoid.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.assets.RegionNames;
import com.obstacleavoid.entity.ObstacleSprite;
import com.obstacleavoid.entity.PlayerSprite;


/** used to keep GameController (Logic/Model) and GameRenderer (GraphicsView) separated, against
 * the pressure from using Sprite (that wants both).
 */
public class EntityFactory {
    private final AssetManager assetManager;
    private TextureAtlas gamePlayAtlas;
    private Pool<ObstacleSprite> obstaclePool;
    private TextureRegion obstacleRegion;

    public EntityFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        gamePlayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ATlAS);
        obstacleRegion = gamePlayAtlas.findRegion(RegionNames.OBSTACLE);
        obstaclePool = new Pool<ObstacleSprite>(0, 40) {
            @Override
            protected ObstacleSprite newObject() {
                return new ObstacleSprite(obstacleRegion);
            }
        };
    }

    public PlayerSprite createPlayer() {
        TextureRegion playerRegion = gamePlayAtlas.findRegion(RegionNames.PLAYER);
        return  new PlayerSprite(playerRegion);
    }

    public ObstacleSprite obtain(){
        ObstacleSprite obstacle = obstaclePool.obtain();
        obstacle.setRegion(obstacleRegion); // this step is necessary as impossible to reset if eg. have 3 diff textures
        return obstacle;
    }

    public void free(ObstacleSprite obstacle){
        obstaclePool.free(obstacle);
    }

    public void freeAll(Array<ObstacleSprite> obstacleSpriteArray) {
        obstaclePool.freeAll(obstacleSpriteArray);
    }



}
