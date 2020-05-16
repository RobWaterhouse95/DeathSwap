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
		_players = new ArrayList<DSPlayerStatus>();
	}

	public void BroadcastToPlayers(String s)
	{
		for (DSPlayerStatus ps : GetPlayers())
			ps.Player.sendMessage("[DeathSwap] " + s);
	}

	public void BroadcastMatchReadyStatus()
	{    	
		BroadcastToPlayers(GetReadyPlayers().size() + "/" + GetPlayers().size() + " players ready.");
	}

	public void BroadcastMatchAliveStatus()
	{    	
		BroadcastToPlayers(GetAlivePlayers().size() + "/" + GetPlayers().size() + " players alive.");
	}

	public void JoinDeathSwapSession(Player player)
	{
		for (DSPlayerStatus ps : GetPlayers())
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

		for (DSPlayerStatus ps : GetPlayers())
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

		for (DSPlayerStatus ps : GetPlayers())
		{
			if (ps.IsAlive)
			{
				alivePlayers.add(ps);
			}
		}

		return alivePlayers;
	}

	public List<DSPlayerStatus> GetReadyPlayers()
	{
		List<DSPlayerStatus> readyPlayers = new ArrayList<DSPlayerStatus>();

		for (DSPlayerStatus ps : GetPlayers())
		{
			if (ps.IsReady)
			{
				readyPlayers.add(ps);
			}
		}

		return readyPlayers;
	}

	public boolean IsMatchRunning()
	{
		if(_match == null || _match.isCancelled())
		{
			return false;
		}

		return true;
	}

	public void DestroySession()
	{
		BroadcastToPlayers("Destroying session");
		_match.StopRunning();
		_players = new ArrayList<DSPlayerStatus>();
	}

	public boolean CheckGameComplete()
	{
		List<DSPlayerStatus> alivePlayers = GetAlivePlayers();    	
		if(alivePlayers.size() == 1)
		{
			BroadcastToPlayers(alivePlayers.get(0).Player.getDisplayName() + " is the winner!");
			DestroySession();
			return true;
		}

		return false;
	}

	public boolean CheckAllPlayersReady()
	{
		if(GetPlayers().size() < 2)
		{
			return false;
		}

		for (DSPlayerStatus ps : GetPlayers())
		{
			if(ps.IsReady == false)
			{
				return false;				
			}
		}

		return true;
	}
}
