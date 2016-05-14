package jp.llv.nest.mod.bukkit

import jp.llv.nest.NestPlugin
import jp.llv.nest.command.CommandExecutor
import jp.llv.nest.command.Func
import jp.llv.nest.command.obj.NestCommandSender
import jp.llv.nest.command.obj.NestExecutable
import jp.llv.nest.command.obj.NestInt
import jp.llv.nest.command.obj.NestValueAdapter
import jp.llv.nest.command.obj.bind.Binding
import org.bukkit.Server
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

/**
 * Created by toyblocks on 2016/05/14.
 */
class SchedulerFunc(val bukkit: Server, val plugin: NestPlugin) {

    @Func("do <executable> <delay> ticks later")
    fun later(executor: CommandExecutor, sender: NestCommandSender<*>, binding: Binding<Any>,
              delay: NestInt, executable: NestExecutable<*>): ScheduledTask {
        return ScheduledTask(bukkit.scheduler.runTaskLater(plugin, Runnable{
            executable.execute(executor,sender,binding)
        }, delay.unwrap()))
    }

    @Func("do <executable> every <period> ticks since <delay> ticks later")
    fun timer(executor: CommandExecutor, sender: NestCommandSender<*>, binding: Binding<Any>,
              delay: NestInt, period: NestInt, executable: NestExecutable<*>): ScheduledTask {
        return ScheduledTask(bukkit.scheduler.runTaskTimer(plugin, Runnable{
            executable.execute(executor,sender,binding)
        }, delay.unwrap(), period.unwrap()))
    }

    class ScheduledTask(val task: BukkitTask): NestValueAdapter<BukkitTask>(task) {

        fun cancel() {
            task.cancel()
        }

    }

}