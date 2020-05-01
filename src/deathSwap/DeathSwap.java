package deathSwap;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin 
{
	public DeathSwapSession DeathSwapSession;
	
	
    @Override
    public void onEnable() 
    {
    	//Register Listeners
    	getServer().getPluginManager().registerEvents(new DeathSwapListener(this), this);
    	
    	//Register Commands
    	getCommand("deathswap").setExecutor(new CommandDeathSwap(this));
    }
    
    @Override
    public void onDisable() 
    {    	

    }
    
    public void GenerateDeathSwapSession() 
    {
    	DeathSwapSession = new DeathSwapSession(this);
    }
}
