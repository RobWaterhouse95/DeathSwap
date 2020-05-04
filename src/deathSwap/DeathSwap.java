package deathSwap;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin 
{
	public DSSession DeathSwapSession;
	
	
    @Override
    public void onEnable() 
    {
    	//Register Listeners
    	getServer().getPluginManager().registerEvents(new DSListener(this), this);
    	
    	//Register Commands
    	getCommand("deathswap").setExecutor(new CommandDeathSwap(this));
    	
    	//Initialise Session
    	DeathSwapSession = new DSSession(this);
    }
    
    @Override
    public void onDisable() 
    {    	

    }
}
