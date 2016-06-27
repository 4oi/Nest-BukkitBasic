package jp.llv.nest.mod.bukkit

import jp.llv.nest.NestAPIBukkit
import jp.llv.nest.NestPlugin
import jp.llv.nest.command.Func
import jp.llv.nest.mod.kotlin.KotlinModule
import jp.llv.nest.module.Module
import org.bukkit.Server
import javax.inject.Inject

/**
 * Created by toyblocks on 2016/05/14.
 */
@Module(name = "bukkit-basics", author = "toyblocks", version = 9)
class BukkitBasicsModule @Inject constructor(val api: NestAPIBukkit, val bukkit: Server, val plugin: NestPlugin, kotlin: KotlinModule){

    @Func("Provides a way to access to scoreboard")
    val score = ScoreFunc(bukkit)

    init {
        api.registerFunc(this)
        api.registerFunc(LocationFunc())
        api.registerFunc(PlayerFunc(bukkit))
        api.registerFunc(CommandFunc(bukkit))
        api.registerFunc(SchedulerFunc(bukkit, plugin))
    }

}