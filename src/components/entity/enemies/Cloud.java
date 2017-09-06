package components.entity.enemies;

import components.map.AnimationObject;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

public class Cloud extends AnimationObject
{
    private Enemy enemy;

    public Cloud(int x, int y, Room room, Enemy enemy)
    {
        super(x, y, new Animation(7, false, Images.Enemies.CLOUD_1, Images.Enemies.CLOUD_2, Images.Enemies.CLOUD_3), room);
        this.enemy = enemy;
    }

    public Enemy getEnemy()
    {
        return enemy;
    }
}
