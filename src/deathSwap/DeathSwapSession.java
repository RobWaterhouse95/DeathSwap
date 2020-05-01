package deathSwap;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DeathSwapSession 
{
	private Plugin _plugin;
	private List<PlayerStatus> _players;
	
	public DeathSwapSession(Plugin plugin) 
	{        
		_plugin = plugin;
		
        _plugin.getLogger().log(Level.INFO, "Generating DeathSwapSession");
		_players = new ArrayList<PlayerStatus>();
		
		BroadcastToPlayers("[DeathSwap] A new DeathSwap session has started! Use \"/DeathSwap join\" to join!");
	}
	
	public void JoinDeathSwapSession(Player player)
    {
		for (PlayerStatus ps : _players)
		{
			if(ps.Player == player)
			{
				player.sendMessage("[DeathSwap] You're already in this session!");
				return;				
			}
		}
		
		player.sendMessage("[DeathSwap] Joining DeathSwap session");
		_players.add(new PlayerStatus(player, false));
    }
    
    public void BroadcastToPlayers(String s)
    {
    	for (PlayerStatus playerStatus : _players){
    		playerStatus.Player.sendMessage("[DeathSwap] " + s);
    	}
    }
    
    public void MarkPlayerAsReady(Player player)
    {
    	boolean playerFound = false;
    	
    	for (PlayerStatus ps : _players)
		{
			if(ps.Player == player)
			{
				playerFound = true;
				ps.IsReady = true;
				player.sendMessage("[DeathSwap] You are now ready!");			
			}
		}
    	
    	if(playerFound == false) 
    	{
    		player.sendMessage("[DeathSwap] You are not in this session, use \"/DeathSwap join\".");
    	}
    	
    	if(CheckAllPlayersReady())
    	{
    		StartGame();
    	}
    }
    
    private boolean CheckAllPlayersReady()
    {
    	for (PlayerStatus ps : _players)
		{
			if(ps.IsReady == false)
			{
				return false;				
			}
		}
    	
    	return true;
    }
    
    private void StartGame()
    {
    	BroadcastToPlayers("All players are ready! Starting game!");
    }
}
