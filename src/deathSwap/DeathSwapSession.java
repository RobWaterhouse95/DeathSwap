package deathSwap;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DeathSwapSession 
{
	private Plugin _plugin;
	private List<Player> _players;
	
	public DeathSwapSession(Plugin plugin) 
	{
		_plugin = plugin;		
		_players = new ArrayList<Player>();
		
        _plugin.getLogger().log(Level.INFO, "Generating DeathSwapSession");
	}
	
	public void JoinDeathSwapSession(Player p)
    {
    	_players.add(p);
    }
    
    public void BroadcastToPlayers(String s)
    {
    	for (Player p : _players){
    		p.sendMessage(s);
    	}
    }
}
