/*
 * Copyright (C) 2017 toyblocks All rights reserved.
 */

package jp.llv.nest.mod.bukkit

import jp.llv.nest.command.Context
import jp.llv.nest.command.Func
import jp.llv.nest.command.obj.NestString
import jp.llv.nest.command.obj.bukkit.BukkitCommandSender
import jp.llv.nest.command.obj.bukkit.BukkitConsole
import org.bukkit.Server
import java.util.*
import java.util.stream.Collectors

/**
 * Created by toyblocks on 2016/05/14.
 */
class CommandFunc(val bukkit: Server) {

    @Func(name = ":b", value = "execute <command> on bukkit", perm="bukkit.execute")
    fun execute(context: Context<BukkitCommandSender<*>>,
                vararg command: NestString): Unit {
        bukkit.dispatchCommand(context.sender.unwrap(), Arrays.stream(command).map { it.unwrap() }.collect(Collectors.joining(" ")));
    }

    @Func(value = "returns console", perm="bukkit.console")
    fun console(context: Context<BukkitCommandSender<*>>): BukkitConsole = BukkitConsole.getInstance();

}