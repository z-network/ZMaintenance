package gq.devmc.zmaint.listeners;

import gq.devmc.zmaint.utilities.Data;
import gq.devmc.zmaint.utilities.Motd;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Listener_ProxyPingEvent
  implements Listener
{
  @EventHandler(priority=64)
  public void onProxyPing(ProxyPingEvent event)
  {
    if (Motd.motdsystem)
    {
      ServerPing serverPing = event.getResponse();
      if (Data.maintenance)
      {
        serverPing.setVersion(new ServerPing.Protocol(Motd.maintenancemessage, -1));
        serverPing.setDescriptionComponent(new TextComponent(Motd.maintenancemotd[0] + "\n" + Motd.maintenancemotd[1]));
      }
      else
      {
        serverPing.setDescriptionComponent(new TextComponent(Motd.normalmotd[0] + "\n" + Motd.normalmotd[1]));
      }
      ServerPing.PlayerInfo[] playerinfo = { new ServerPing.PlayerInfo(Motd.getPlayerInfo(), "") };
      
      serverPing.getPlayers().setOnline(BungeeCord.getInstance().getOnlineCount());
      serverPing.getPlayers().setMax(Motd.slots);
      serverPing.getPlayers().setSample(playerinfo);
    }
  }
}
