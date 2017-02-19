/*
 * Copyright (C) 2017 toyblocks All rights reserved.
 */

package jp.llv.nest.mod.bukkit

import jp.llv.nest.command.Context
import jp.llv.nest.command.Func
import jp.llv.nest.command.exceptions.CommandException
import jp.llv.nest.command.obj.bukkit.BukkitCommandSender
import jp.llv.nest.command.obj.bukkit.BukkitLocation

/**
 * Created by toyblocks on 2016/05/23.
 */
class LocationFunc() {

    @Func(value = "return where the executor is", perm="bukkit.here")
    fun here(context: Context<BukkitCommandSender<*>> ): BukkitLocation {
        return BukkitLocation(context.sender.location.orElseThrow { throw  CommandException("The executor has no location!")})
    }

}