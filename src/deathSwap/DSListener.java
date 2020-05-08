package deathSwap;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DSListener implements Listener
{
	private DeathSwap _plugin;
	
	public DSListener(DeathSwap plugin) {
		_plugin = plugin;
	}

	@EventHandler
	public void onInteract(EntityDeathEvent evt) 
	{
		this.EntityDeathLogic(evt.getEntity());
	}

	private void EntityDeathLogic(LivingEntity entity) 
	{
		if((entity == null) || (entity.getType() != EntityType.PLAYER) || !_plugin.DeathSwapSession.IsMatchRunning())
		{
			return;
		}
		
		Player player = (Player) entity;		
		List<DSPlayerStatus> alivePlayers = _plugin.DeathSwapSession.GetAlivePlayers();
		
		for (DSPlayerStatus ps : alivePlayers)
		{
			if(ps.Player == player)
			{
				ps.IsAlive = false;
				ps.Player.setGameMode(GameMode.SPECTATOR);
				if(!_plugin.DeathSwapSession.CheckGameComplete())
				{
					_plugin.DeathSwapSession.BroadcastMatchAliveStatus();
				}				
			}
		}
	}
}
