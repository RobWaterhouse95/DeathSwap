package deathSwap;

import org.bukkit.entity.Player;

public class PlayerStatus 
{
	public Player Player;
	public boolean IsReady;
	
	public PlayerStatus (Player player, boolean isReady)
	{
		Player = player;
		IsReady = isReady;
	}
}
