package deathSwap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStartSession implements CommandExecutor
{
	private DeathSwap _plugin;
	
	public CommandStartSession(DeathSwap plugin) {
		_plugin = plugin;
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
    	if (sender instanceof Player) 
    	{
            Player player = (Player) sender;
            
            player.sendMessage("Generating DeathSwap session");
            _plugin.GenerateDeathSwapSession();
        }
    	
        return true;
    }	
}
