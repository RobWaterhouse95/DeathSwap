package deathSwap;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DSMatch extends BukkitRunnable 
{
	private Plugin _plugin;
	private DSSession _session;
	private boolean _isRunning;
	private int swapTime = 20;
	private int secondsUntilNextSwap;

    public DSMatch(Plugin plugin, DSSession session) 
    {
        _plugin = plugin;
		_session = session;
		
		_isRunning = true;
		secondsUntilNextSwap = swapTime;
		
		this.runTaskTimer(_plugin, 0l, 20l);
    }

    @Override
    public void run() 
    {
    	if(_isRunning)
		{
			secondsUntilNextSwap--;
			
			if(secondsUntilNextSwap == 0)
			{
				_session.BroadcastToPlayers("Swapping!");
				secondsUntilNextSwap = swapTime;
				
				swapPlayers();

			}			
			else if(secondsUntilNextSwap <= 10)
			{
				_session.BroadcastToPlayers(secondsUntilNextSwap + " seconds until next swap!");
			}
		}
		else
		{
			this.cancel();
		}
    }
    
    public void StopRunning()
    {
    	_isRunning = false;
    }
    
    private void swapPlayers()
	{		
		List<DSPlayerStatus> alivePlayers =  _session.GetAlivePlayers();
		Location firstLocation = alivePlayers.get(0).Player.getLocation();
		
		for (int i = 0; i < alivePlayers.size(); i++)
		{
			if(i != alivePlayers.size() - 1)
			{
				alivePlayers.get(i).Player.teleport( alivePlayers.get(i + 1).Player.getLocation());
			}
			else
			{
				alivePlayers.get(i).Player.teleport(firstLocation);
			}
		}
	}
}
