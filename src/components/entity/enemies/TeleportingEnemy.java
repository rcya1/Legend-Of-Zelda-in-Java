package components.entity.enemies;

abstract class TeleportingEnemy extends Enemy
{
	abstract void generateTargetLocation();

	boolean checkTargetIsInMap(double destX, double destY)
	{
		return (destX > 0 && destY > 0 && destX < room.getMapWidth()
					&& destY < room.getMapHeight());
	}

	boolean checkTargetIsInMap(int targetColumn, int targetRow)
	{
		return (targetColumn > 0 && targetRow > 0 &&
				targetColumn < room.getNumOfColumns() - 1 &&
				targetRow < room.getNumOfRows() - 1);
	}

	abstract boolean checkTargetIsAvailable(double destX, double destY);
}
