package com.obstacleavoid.entity.old;


import static com.obstacleavoid.config.GameConfig.PLAYER_BOUNDS_RADIUS;
import static com.obstacleavoid.config.GameConfig.PLAYER_SIZE;
@Deprecated
public class Player extends GameObjectBase
{
    public Player(){
        super(PLAYER_BOUNDS_RADIUS);
        setSize( PLAYER_SIZE, PLAYER_SIZE );
    }

}
