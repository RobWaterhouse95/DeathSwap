package deathSwap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class DSWorldGenerator 
{	
	private static final String _WORLD_NAME = "DeathSwapArena";

	public static World GenerateWorld() 
	{
		World world = Bukkit.getServer().getWorld(_WORLD_NAME);
		if(world == null)
		{
			WorldCreator wc = new WorldCreator(_WORLD_NAME);

			wc.type(WorldType.NORMAL);

			return wc.createWorld();
		}

		return world;
	}
}
