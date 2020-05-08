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
				case "generate":
					CommandGenerate(player, command, label, args);
					break;
				case "arena":
					CommandArena(player, command, label, args);
					break;
				case "world":
					CommandWorld(player, command, label, args);
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
		_plugin.DeathSwapSession.MarkPlayerAsReady(player);
	}
	
	private void CommandDestroy(Player player, Command command, String label, String[] args)
	{
		_plugin.DeathSwapSession.DestroySession();
	}
	
	private void CommandGenerate(Player player, Command command, String label, String[] args)
	{
		DSWorldGenerator.GenerateWorld();
	}
	
	private void CommandArena(Player player, Command command, String label, String[] args)
	{
		player.teleport(Bukkit.getWorld("DeathSwapArena").getSpawnLocation());
	}
	
	private void CommandWorld(Player player, Command command, String label, String[] args)
	{
		player.teleport(Bukkit.getWorld("world").getSpawnLocation());
	}
	
	private void CommandIsNotSupported(Player player, Command command, String label, String[] args)
	{
		player.sendMessage("This is not a supported command, please use \"/DeathSwap help\" for available commands");
	}
}
