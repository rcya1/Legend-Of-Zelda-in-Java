package components.entity.enemies;

import components.entity.Direction;

public interface ProjectileDeflectibleEnemy
{
	int getShieldRequiredLevel();

	Direction getProjectileDirection();
}
