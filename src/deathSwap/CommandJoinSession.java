package deathSwap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJoinSession implements CommandExecutor
{
	private DeathSwap _plugin;
	
	public CommandJoinSession(DeathSwap plugin) {
		_plugin = plugin;
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
    	if (sender instanceof Player) 
    	{
            Player player = (Player) sender;
            
            player.sendMessage("Joining DeathSwap session");
            
            if(_plugin == null)
            {
            	player.sendMessage("plugin null");
            }
            
            if(_plugin.DeathSwapSession == null)
            {
            	player.sendMessage("_plugin.DeathSwapSession null");
            }
            
            _plugin.DeathSwapSession.JoinDeathSwapSession(player);
        }
    	
        return true;
    }	
}
