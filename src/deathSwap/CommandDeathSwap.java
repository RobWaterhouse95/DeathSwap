package deathSwap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDeathSwap implements CommandExecutor
{
	private DeathSwap _plugin;

	public CommandDeathSwap(DeathSwap plugin) {
		_plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if (sender instanceof Player) 
		{
			Player player = (Player) sender;
			
			switch (args[0]) 
			{
				case "join":
					CommandJoin(player, command, label, args);
					break;
				case "ready":
					CommandReady(player, command, label, args);
					break;
				case "destroy":
					CommandDestroy(player, command, label, args);
					break;
				default:
					CommandIsNotSupported(player, command, label, args);
					break;
			}
		}

		return true;
	}
	
	private void CommandJoin(Player player, Command command, String label, String[] args)
	{
		if(_plugin.DeathSwapSession.GetPlayers().size() == 0)
		{
			Bukkit.broadcastMessage("[DeathSwap] A new DeathSwap session has started! Use \"/DeathSwap join\" to connect!");
		}
        _plugin.DeathSwapSession.JoinDeathSwapSession(player);
	}
	
	private void CommandReady(Player player, Command command, String label, String[] args)
	{
		if(_plugin.DeathSwapSession == null)
		{
			player.sendMessage("There is no active DeathSwap session, Use \"/DeathSwap join\" to start a new one.");
		}
		
		_plugin.DeathSwapSession.MarkPlayerAsReady(player);
	}
	
	private void CommandDestroy(Player player, Command command, String label, String[] args)
	{
		_plugin.DeathSwapSession.BroadcastToPlayers("Destroying session");
		_plugin.DeathSwapSession.DestroyMatch();
		_plugin.DeathSwapSession = null;
	}
	
	private void CommandIsNotSupported(Player player, Command command, String label, String[] args)
	{
		player.sendMessage("This is not a supported command, please use \"/DeathSwap help\" for available commands");
	}
}
