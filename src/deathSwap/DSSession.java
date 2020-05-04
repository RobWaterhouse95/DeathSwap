package deathSwap;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DSSession 
{
	private Plugin _plugin;
	private DSMatch _match;
	private List<DSPlayerStatus> _players;
	
	public DSSession(Plugin plugin) 
	{        
		_plugin = plugin;
		
        _plugin.getLogger().log(Level.INFO, "Generating DeathSwapSession");
		_players = new ArrayList<DSPlayerStatus>();
	}
	
	public void BroadcastToPlayers(String s)
    {
    	for (DSPlayerStatus playerStatus : _players){
    		playerStatus.Player.sendMessage("[DeathSwap] " + s);
    	}
    }
	
	public boolean IsMatchRunning()
	{
		if(_match == null || _match.isCancelled())
		{
			return false;
		}
		
		return true;
	}
	
	public void JoinDeathSwapSession(Player player)
    {
		for (DSPlayerStatus ps : _players)
		{
			if(ps.Player == player)
			{
				player.sendMessage("[DeathSwap] You're already in this session!");
				return;				
			}
		}
		
		_players.add(new DSPlayerStatus(player, false, true));
		BroadcastMatchReadyStatus();
    }
	
    public void MarkPlayerAsReady(Player player)
    {
    	boolean playerFound = false;
    	
    	for (DSPlayerStatus ps : _players)
		{
			if(ps.Player == player)
			{
				playerFound = true;
				ps.IsReady = true;
				BroadcastMatchReadyStatus();
			}
		}
    	
    	if(playerFound == false) 
    	{
    		player.sendMessage("[DeathSwap] You are not in this session, use \"/DeathSwap join\".");
    	}
    	
    	if(CheckAllPlayersReady())
    	{
    		_match = new DSMatch(_plugin, this);
    		BroadcastToPlayers("Match has begun!");
    	}
    }
    
    public List<DSPlayerStatus> GetPlayers()
    {
    	return _players;
    }
    
    public List<DSPlayerStatus> GetAlivePlayers()
    {
    	List<DSPlayerStatus> alivePlayers = new ArrayList<DSPlayerStatus>();
    	
    	for (DSPlayerStatus ps : _players)
    	{
    		if (ps.IsAlive)
    		{
    			alivePlayers.add(ps);
    		}
    	}
    	
    	return alivePlayers;
    }
    
    public void DestroyMatch()
    {
    	_match.StopRunning();
    	_match = null;
    }
    
    private boolean CheckAllPlayersReady()
    {
    	if(_players.size() < 2)
    	{
    		return false;
    	}
    	
    	for (DSPlayerStatus ps : _players)
		{
			if(ps.IsReady == false)
			{
				return false;				
			}
		}
    	
    	return true;
    }
    
    public boolean CheckGameComplete()
    {
    	List<DSPlayerStatus> alivePlayers = new ArrayList<DSPlayerStatus>();
    	
    	for (DSPlayerStatus ps : _players)
    	{
    		if(ps.IsAlive)
    		{
    			alivePlayers.add(ps);
    		}
    	}
    	
    	if(alivePlayers.size() == 1)
    	{
    		BroadcastToPlayers(alivePlayers.get(0).Player.getDisplayName() + " is the winner!");
    		DestroyMatch();
    		DestroySession();
    		return true;
    	}
    	
    	return false;
    }
    
    private void DestroySession()
    {
    	_players = new ArrayList<DSPlayerStatus>();
    }
    
    public void BroadcastMatchReadyStatus()
    {
    	int totalPlayers = 0;
    	int readyCount = 0;
    	for (DSPlayerStatus ps : _players)
		{
			totalPlayers++;
			if(ps.IsReady)
			{
				readyCount++;
			}
		}
    	
    	BroadcastToPlayers(readyCount + "/" + totalPlayers + " players ready");
    }
    
    public void BroadcastMatchAliveStatus()
    {
    	int totalPlayers = 0;
    	int aliveCount = 0;
    	for (DSPlayerStatus ps : _players)
		{
			totalPlayers++;
			if(ps.IsAlive)
			{
				aliveCount++;
			}
		}
    	
    	BroadcastToPlayers(aliveCount + "/" + totalPlayers + " players alive.");
    }
}
