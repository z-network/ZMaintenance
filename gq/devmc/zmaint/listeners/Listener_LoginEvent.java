package gq.devmc.zmaint.listeners;

import java.util.List;

import gq.devmc.zmaint.utilities.Data;
import gq.devmc.zmaint.utilities.Messages;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Listener_LoginEvent
  implements Listener
{
  @EventHandler(priority=64)
  public void onLogin(LoginEvent event)
  {
    String uuid = event.getConnection().getUniqueId().toString().replaceAll("-", "");
    String name = event.getConnection().getName().toString();
    
    List<String> whitelist = Data.whitelist;
    if (Data.maintenance)
    {
      System.out.println(uuid);
      if (whitelist.contains(uuid))
      {
        event.setCancelled(false);
        return;
      }
      event.setCancelReason(new BaseComponent[] { new TextComponent(Messages.cancelreason()) });
      event.setCancelled(true);
      for (ProxiedPlayer proxiedPlayer : BungeeCord.getInstance().getPlayers()) {
        if (proxiedPlayer.hasPermission("zmaintenance.notify")) {
          proxiedPlayer.sendMessage(new TextComponent(Data.prefix + Messages.teamnotify.replaceAll("%player%", name)));
        }
      }
    }
  }
}
