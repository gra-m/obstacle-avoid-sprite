package com.obstacleavoid.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.assets.RegionNames;
import com.obstacleavoid.entity.PlayerSprite;


/** used to keep GameController (Logic/Model) and GameRenderer (GraphicsView) separated, against
 * the pressure from using Sprite (that wants both).
 */
public class EntityFactory {
    private final AssetManager assetManager;
    private TextureAtlas gamePlayAtlas;

    public EntityFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        gamePlayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ATlAS);
    }

    public PlayerSprite createPlayer() {
        TextureRegion playerRegion = gamePlayAtlas.findRegion(RegionNames.PLAYER);
        return  new PlayerSprite(playerRegion);
    }



}
