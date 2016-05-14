package jp.llv.nest.mod.bukkit

import jp.llv.nest.NestAPIBukkit
import jp.llv.nest.NestPlugin
import jp.llv.nest.mod.kotlin.KotlinModule
import jp.llv.nest.module.Module
import org.bukkit.Server

/**
 * Created by toyblocks on 2016/05/14.
 */
@Module(name = "bukkit-basics", author = "toyblocks", version = 1)
class BukkitBasicsModule(val api: NestAPIBukkit, val bukkit: Server,  val plugin: NestPlugin, kotlin: KotlinModule){

    init {
        api.registerFunc(CommandFunc(bukkit))
        api.registerFunc(SchedulerFunc(bukkit, plugin))
    }

}