# Sprite
A game object that describes almost everything
* Sprite is a subclass of TextureRegion with additional information.
* Sprite is always rectangular x0,y0 is in bottom left corner
* Position
* Rotation
* Size width/height
* Scale
* Colour
* Texture Info
* Origin from which rotations and scales are performed, so this origin never moves
* Origin is mapped relative to bottom left corner of Sprite

## Sprite mixes Model info (position rotation scale size) with view info (the texture we want to draw)
* So Sprite is inappropriate if implementing design patterns where model and view are strictly separated. 