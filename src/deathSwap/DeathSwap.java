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
    	getCommand("startsession").setExecutor(new CommandStartSession(this));
    	getCommand("joinsession").setExecutor(new CommandJoinSession(this));
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
