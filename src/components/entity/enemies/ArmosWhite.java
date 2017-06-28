package components.entity.enemies;

import components.map.rooms.Room;
import utility.Images;

public class ArmosWhite extends Armos
{
    public ArmosWhite(int x, int y, Room room)
    {
        super(x, y, room);
        immobile = Images.Enemies.Armos.ARMOS_WHITE;
    }
}
