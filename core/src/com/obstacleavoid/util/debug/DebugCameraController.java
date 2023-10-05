package com.obstacleavoid.util.debug;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

import static com.obstacleavoid.util.Common.DEFAULT_ZOOM_LEVEL;
import static com.obstacleavoid.util.Common.LOCALE;

public class DebugCameraController
{
    // == constants ==
    private static final Logger LOG = new Logger(DebugCameraController.class.getName(), Logger.DEBUG);

    // == attributes ==
    private Vector2 position = new Vector2();
    private Vector2 startPosition = new Vector2();
    private float zoom;
    private DebugCameraConfig config;

    // == constructor ==

    public DebugCameraController() {
        this.config = new DebugCameraConfig();
        this.zoom = config.getZoomLevel();
        LOG.info(String.format(LOCALE,"cameraConfig =%s",  config));
    }

    // == public API ==
    public void setStartPosition(float x, float y)
    {
        startPosition.set(x, y);
        position.set(x, y);
    }

    public void applyTo(OrthographicCamera camera)
    {
        camera.position.set(position, 0);
        camera.zoom = zoom;
        camera.update();
    }

    public void handleDebugInput(float delta)
    {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            return;
        }
        float moveSpeed = config.getMoveSpeed() * delta;
        float zoomSpeed = config.getZoomSpeed() * delta;

        // move controls
        if (config.isLeftPressed()) {
            moveLeft(moveSpeed);
        } else if (config.isRightPressed()) {
            moveRight(moveSpeed);
        } else if (config.isUpPressed()) {
            moveUp(moveSpeed);
        } else if (config.isDownPressed()) {
            moveDown(moveSpeed);
        }

        // zoom controls
        if (config.isZoomInPressed()) {
            zoomIn(zoomSpeed);
        } else if (config.isZoomOutPressed()) {
            zoomOut(zoomSpeed);
        }
        // reset controls
        if (config.isResetPressed()) {
            reset();
        }

       // log controls
        if (config.isLogPressed()) {
            logDebug();
        }

    }

    // private methods
    private void setZoom(float value) {
        zoom = MathUtils.clamp(value, config.getMaxZoomIn(), config.getMaxZoomOut());
        
    }
    private void setPosition(float x, float y) {
        position.set(x, y);
    }

    private void moveCamera(float xSpeed, float ySpeed){
        setPosition(position.x + xSpeed, position.y + ySpeed);
    }
    private void moveLeft(float moveSpeed)
    {
        moveCamera(-moveSpeed, 0);
    }

    private void moveRight(float moveSpeed)
    {
        moveCamera(moveSpeed, 0);
    }
    private void moveUp(float moveSpeed)
    {
        moveCamera(0, moveSpeed);
    }
    private void moveDown(float moveSpeed)
    {
        moveCamera(0, -moveSpeed);
    }

    private void zoomIn(float zoomSpeed){
        setZoom(zoom + zoomSpeed);
    }
    private void zoomOut(float zoomSpeed){
        setZoom(zoom - zoomSpeed);
    }
    private void reset(){
        position.set(startPosition);
        setZoom(DEFAULT_ZOOM_LEVEL);
    }


    // this is printing for every thread!
    private void logDebug(){
            LOG.debug( String.format( LOCALE, "position = %s zoom = %f", position, zoom ) );
    }

    }



