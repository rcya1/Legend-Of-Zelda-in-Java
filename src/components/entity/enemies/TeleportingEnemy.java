package components.entity.enemies;

import components.map.rooms.Room;

public abstract class TeleportingEnemy extends Enemy
{
	public TeleportingEnemy(int x, int y, Room room, int health, int damage, String state, int width, int height)
	{
		super(x, y, room, health, damage, state, width, height);
	}

	public abstract void generateTargetLocation();

	public boolean checkTargetIsInMap(double destX, double destY)
	{
		return (destX > 0 && destY > 0 && destX < room.getMapWidth()
					&& destY < room.getMapHeight());
	}

	public boolean checkTargetIsInMap(int targetColumn, int targetRow)
	{
		return (targetColumn > 0 && targetRow > 0 &&
				targetColumn < room.getNumOfColumns() - 1 &&
				targetRow < room.getNumOfRows() - 1);
	}

	public abstract boolean checkTargetIsAvailable(double destX, double destY);
}
