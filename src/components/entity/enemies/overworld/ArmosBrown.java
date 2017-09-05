package components.entity.enemies.overworld;

import components.map.rooms.Room;
import utility.Images;

public class ArmosBrown extends Armos
{
    public ArmosBrown(int x, int y, Room room)
    {
        super(x, y, room);
        immobile = Images.Enemies.Armos.ARMOS_BROWN;
    }
}
