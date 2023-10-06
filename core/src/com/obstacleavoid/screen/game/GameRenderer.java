package com.obstacleavoid.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.entity.Background;
import com.obstacleavoid.entity.Obstacle;
import com.obstacleavoid.entity.PlayerSprite;
import com.obstacleavoid.util.GdxUtils;
import com.obstacleavoid.util.ViewportUtils;
import com.obstacleavoid.util.debug.DebugCameraController;

import static com.obstacleavoid.assets.RegionNames.*;

public class GameRenderer implements Disposable
{
    // constants / final instance variables
    private static final Logger LOG = new Logger( GameRenderer.class.getName( ), Logger.DEBUG );
    private final GameController gameController;
    private final AssetManager assetManager;
    private final SpriteBatch batch;

    // fields
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private DebugCameraController debugCameraController;

    private OrthographicCamera hudCamera;
    private Viewport hudViewport;
    private BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout( );
    private TextureRegion playerRegion;
    private TextureRegion obstacleRegion;
    private TextureRegion backgroundRegion;


    // constructors

    public GameRenderer( SpriteBatch batch, AssetManager assetManager, GameController gameController)
    {
        this.assetManager = assetManager;
        this.batch = batch;
        this.gameController = gameController;
        init( );
    }

    private void init()
    {
        camera = new OrthographicCamera( );
        viewport = new FitViewport( GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera );
        renderer = new ShapeRenderer( );

        hudCamera = new OrthographicCamera( );
        hudViewport = new FitViewport( GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera );

        LOG.debug("Loaded assets prior to initialization = " + assetManager.getLoadedAssets() + " named:\n " +
                        assetManager.getAssetNames());

        TextureAtlas gameplayAtlas = assetManager.get( AssetDescriptors.GAMEPLAY_ATlAS );
        
        font = assetManager.get( AssetDescriptors.UI_FONT_32 );

        backgroundRegion = gameplayAtlas.findRegion( BACKGROUND );
        playerRegion = gameplayAtlas.findRegion( PLAYER );
        obstacleRegion = gameplayAtlas.findRegion( OBSTACLE );
        createDebugCameraController( );
    }
    // public API

    // automatically called every frame
    public void render( float delta )
    {
        batch.totalRenderCalls = 0; // todo delete

        debugCameraController.handleDebugInput( delta );
        debugCameraController.applyTo( camera );


        if(Gdx.input.isTouched() && !gameController.isGameOver()) {
            Vector2 screenTouchedCoOrd = new Vector2( Gdx.input.getX(), Gdx.input.getY() );
            Vector2 worldTouchedCoOrd = viewport.unproject(  new Vector2( screenTouchedCoOrd ) );
            System.out.println( "screen co-ordinates: " + screenTouchedCoOrd );
            System.out.println( "world co-ordinates: " + worldTouchedCoOrd );

            PlayerSprite player = gameController.getPlayer();

            worldTouchedCoOrd.x = ( MathUtils.clamp( worldTouchedCoOrd.x, 0,
                    GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE ) );
            player.setX( worldTouchedCoOrd.x );

        }

        GdxUtils.clearScreen( );

        // YES 1st RENDERED is bottom layer!
        renderGamePlay();

        renderUi( );

        // render debug Grid etc
        renderDebug( );

    }



    public void resize( int width, int height )
    {
        viewport.update( width, height, true );
        hudViewport.update( width, height, true );
        ViewportUtils.debugPixelPerUnit( viewport );

    }

    @Override
    public void dispose()
    {
        renderer.dispose( );
    }


    //private
    private void renderGamePlay( ) {

        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        Background background = gameController.getBackground();
        //batch.draw( backgroundRegion, background.getX(), background.getY(), background.getWidth(),
         //       background.getHeight());

        PlayerSprite player = gameController.getPlayer();
        player.draw(batch);
        //batch.draw( playerRegion, player.getX(), player.getY(), player.getWidth(), player.getHeight());

      /*  for (Obstacle ob: gameController.getObstacles()) {
            batch.draw( obstacleRegion, ob.getX(), ob.getY(), ob.getWidth(), ob.getHeight());
        } */



        batch.end();
    }

    private void createDebugCameraController()
    {
        debugCameraController = new DebugCameraController( );
        debugCameraController.setStartPosition( GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y );
    }

    private void renderUi()
    {
        hudViewport.apply();
        batch.setProjectionMatrix( hudCamera.combined );
        batch.begin( );

        String livesText = "LIVES " + gameController.getLives();

        layout.setText( font, livesText );
        font.draw( batch, livesText, 20, GameConfig.HUD_HEIGHT - layout.height );

        String scoreText = "SCORE " + gameController.getDisplayScore();
        layout.setText( font, scoreText );
        font.draw( batch, scoreText,
                ( GameConfig.HUD_WIDTH - layout.width ) - 20,
                GameConfig.HUD_HEIGHT - layout.height );
        batch.end( );
    }

    private void renderDebug()
    {
        viewport.apply();

        Color oldColour = renderer.getColor().cpy();

        renderer.setProjectionMatrix( camera.combined );
        renderer.begin( ShapeRenderer.ShapeType.Line );
        drawDebug( );
        renderer.end( );

        renderer.setColor(oldColour);

        ViewportUtils.drawGrid( viewport, renderer );

    }

    private void drawDebug()
    {
        renderer.setColor(Color.RED);
        PlayerSprite player = gameController.getPlayer();
        player.drawDebug( renderer );
        /*
        Array<Obstacle> obstacles = gameController.getObstacles();


        for ( Obstacle o : obstacles ) {
            o.drawDebug( renderer );
        }*/
    }


}
