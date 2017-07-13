/*
 * Copyright (C) 2017 toyblocks All rights reserved.
 */

package jp.llv.nest.mod.bukkit

import jp.llv.nest.NestAPIBukkit
import jp.llv.nest.NestBukkitPlugin
import jp.llv.nest.command.Func
import jp.llv.nest.mod.kotlin.KotlinModule
import jp.llv.nest.module.Module
import org.bukkit.Server
import javax.inject.Inject

/**
 * Created by toyblocks on 2016/05/14.
 */
@Module(name = "bukkit-basics", author = "toyblocks", version = 11)
class BukkitBasicsModule @Inject constructor(val api: NestAPIBukkit, val bukkit: Server, val plugin: NestBukkitPlugin, kotlin: KotlinModule){

    @Func("Provides a way to access to scoreboard")
    val score = ScoreFunc(bukkit)

    init {
        api.registerFunc(this)
        api.registerFunc(LocationFunc())
        api.registerFunc(PlayerFunc(bukkit))
        api.registerFunc(CommandFunc(bukkit))
        api.registerFunc(SchedulerFunc(bukkit, plugin, api))
    }

}