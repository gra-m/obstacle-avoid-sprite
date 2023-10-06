package com.obstacleavoid.screen.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.ObstacleAvoidGame;
import com.obstacleavoid.common.EntityFactory;
import com.obstacleavoid.screen.menu.MenuScreen;


public class GameScreen implements Screen
{
    private static final Logger LOG = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    private final ObstacleAvoidGame obstacleAvoidGame;
    private final AssetManager assetManager;
    private  GameController controller;
    private GameRenderer renderer;
    private EntityFactory entityFactory;

    public GameScreen( ObstacleAvoidGame game ) {
        this.obstacleAvoidGame = game;
        this.assetManager = obstacleAvoidGame.getAssetManager();
        this.entityFactory = new EntityFactory(this.assetManager);
    }

    @Override
    public void show()
    {
        this.controller = new GameController(obstacleAvoidGame, entityFactory);
        this.renderer = new GameRenderer(obstacleAvoidGame.getSpriteBatch(), assetManager, controller);
    }

    @Override
    public void render( float delta )
    {
        renderer.render( delta );
        controller.update( delta );

        if ( controller.isGameOver( ) ) {
            obstacleAvoidGame.setScreen( new MenuScreen( obstacleAvoidGame ) );
        }
    }

    @Override
    public void resize( int width, int height )
    {
        renderer.resize( width, height );
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {
        dispose();
    }

    @Override
    public void dispose()
    {
        renderer.dispose();
    }
}
