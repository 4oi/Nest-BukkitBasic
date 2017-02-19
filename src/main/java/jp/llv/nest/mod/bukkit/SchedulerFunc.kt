/*
 * Copyright (C) 2017 toyblocks All rights reserved.
 */

package jp.llv.nest.mod.bukkit

import jp.llv.nest.NestPlugin
import jp.llv.nest.command.Context
import jp.llv.nest.command.Func
import jp.llv.nest.command.obj.NestCommandSender
import jp.llv.nest.command.obj.NestExecutable
import jp.llv.nest.command.obj.NestInt
import jp.llv.nest.command.obj.NestValueAdapter
import org.bukkit.Server
import org.bukkit.scheduler.BukkitTask

/**
 * Created by toyblocks on 2016/05/14.
 */
class SchedulerFunc(val bukkit: Server, val plugin: NestPlugin) {

    @Func(value = "do <executable> <delay> ticks later", perm = "schedule.later")
    fun <S: NestCommandSender<*>>later(context: Context<S>,
              delay: NestInt, executable: NestExecutable<S, *>): ScheduledTask {
        return ScheduledTask(bukkit.scheduler.runTaskLater(plugin, Runnable{
            executable.execute(context)
        }, delay.unwrap()))
    }

    @Func(value = "do <executable> every <period> ticks since <delay> ticks later", perm = "schedule.timer")
    fun <S: NestCommandSender<*>> timer(context: Context<S>,
              delay: NestInt, period: NestInt, executable: NestExecutable<S, *>): ScheduledTask {
        return ScheduledTask(bukkit.scheduler.runTaskTimer(plugin, Runnable{
            executable.execute(context)
        }, delay.unwrap(), period.unwrap()))
    }

    @Func(value = "cancel <task>", perm = "schedule.cancel")
    fun cancel(context: Context<*>,
               task: ScheduledTask): Unit {
        task.cancel()
    }

    @Func(value = "execute <task> synchronizedly with main-thread", perm = "schedule.sync")
    fun <S: NestCommandSender<*>> syncmain(context: Context<S>,
             executable: NestExecutable<S, *>): ScheduledTask {
        return ScheduledTask(bukkit.scheduler.runTask(plugin, Runnable{
            executable.execute(context)
        }))
    }

    class ScheduledTask(val task: BukkitTask): NestValueAdapter<BukkitTask>(task) {

        fun cancel() {
            task.cancel()
        }

    }

}