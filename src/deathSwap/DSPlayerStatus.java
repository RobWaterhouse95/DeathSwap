package deathSwap;

import org.bukkit.entity.Player;

public class DSPlayerStatus 
{
	public Player Player;
	public boolean IsReady;
	public boolean IsAlive;

	public DSPlayerStatus (Player player, boolean isReady, boolean isAlive)
	{
		Player = player;
		IsReady = isReady;
		IsAlive = isAlive;
	}
}
