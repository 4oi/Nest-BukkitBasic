/*
 * Copyright (C) 2017 toyblocks All rights reserved.
 */

package jp.llv.nest.mod.bukkit

import jp.llv.nest.command.Context
import jp.llv.nest.command.Func
import jp.llv.nest.command.obj.bukkit.BukkitLocation
import jp.llv.nest.command.obj.bukkit.BukkitPlayer
import org.bukkit.Server
import org.bukkit.command.CommandException
import org.bukkit.entity.Player

/**
 * Created by toyblocks on 2016/05/23.
 */
class PlayerFunc(val bukkit: Server) {

    @Func(value = "return closest player ", perm="bukkit.playernear")
    fun playernear(context: Context<*>,
                   location: BukkitLocation): BukkitPlayer {
        var player: Player? = null;
        var distance  :Double = Double.MAX_VALUE;
        for (p in bukkit.onlinePlayers) {
            if (location.unwrap().world != p.world) continue
            var d = p.location.distanceSquared(location.unwrap())
            if (d < distance) {
                player = p
                distance = d
            }
        }
        if (player == null) throw CommandException("There are no players")
        return BukkitPlayer(player)
    }

}