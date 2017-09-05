package components.entity.enemies.overworld;

import components.entity.Direction;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

public class LynelBlue extends Lynel
{
    public LynelBlue(int x, int y, Direction direction, Room room)
    {
        super(x, y, direction, room);

        up = new Animation(20, true, Images.Enemies.Lynel.LYNEL_BLUE_UP,
                Images.Enemies.Lynel.LYNEL_BLUE_UP_2);
        right = new Animation(20, true, Images.Enemies.Lynel.LYNEL_BLUE_RIGHT,
                Images.Enemies.Lynel.LYNEL_BLUE_RIGHT_2);
        down = new Animation(20, true, Images.Enemies.Lynel.LYNEL_BLUE_DOWN,
                Images.Enemies.Lynel.LYNEL_BLUE_DOWN_2);
        left = new Animation(20, true, Images.Enemies.Lynel.LYNEL_BLUE_LEFT,
                Images.Enemies.Lynel.LYNEL_BLUE_LEFT_2);

        this.health = 12;
    }
}
