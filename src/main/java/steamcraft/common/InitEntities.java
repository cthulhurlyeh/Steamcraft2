/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common;

import steamcraft.common.entities.EntityFleshGolem;
import steamcraft.common.entities.EntityGrapplingHook;
import steamcraft.common.entities.EntityGrub;
import steamcraft.common.entities.EntityLostMiner;
import steamcraft.common.entities.EntityTimeBomb;
import steamcraft.common.entities.projectile.EntityBullet;
import steamcraft.common.entities.projectile.EntityRocket;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author Surseance
 * 
 */
public class InitEntities
{
	public static void init()
	{
		registerEntities();
	}

	private static void registerEntities()
	{
		int entID = 0;

		EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", entID++, Steamcraft.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityGrapplingHook.class, "GrapplingHook", entID++, Steamcraft.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityTimeBomb.class, "TimeBomb", entID++, Steamcraft.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityRocket.class, "Rocket", entID++, Steamcraft.instance, 64, 10, true);

		// Mobs
		EntityRegistry.registerModEntity(EntityFleshGolem.class, "FleshGolem", entID++, Steamcraft.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityGrub.class, "Grub", entID++, Steamcraft.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityLostMiner.class, "LostMiner", entID++, Steamcraft.instance, 64, 20, true);
	}
}
