package components.entity.enemies;

import java.awt.*;

public interface ProjectileEnemy
{
	void removeProjectile();

	Rectangle getProjectileCollisionBox();

	int getProjectileDamage();
}
