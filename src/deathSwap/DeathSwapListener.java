package deathSwap;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathSwapListener implements Listener
{
	private DeathSwap _plugin;
	
	public DeathSwapListener(DeathSwap plugin) {
		_plugin = plugin;
	}

	@EventHandler
	public void onInteract(EntityDeathEvent evt) 
	{
		this.EntityDeathLogic(evt.getEntity());
	}

	private void EntityDeathLogic(LivingEntity entity) 
	{
		if((entity == null) || (entity.getType() != EntityType.PLAYER))
		{
			return;
		}
		
		Player player = (Player) entity;		
		_plugin.DeathSwapSession.BroadcastToPlayers(player.getDisplayName() + " has died!");
	}
}
