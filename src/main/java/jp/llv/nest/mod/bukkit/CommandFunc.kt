package jp.llv.nest.mod.bukkit

import jp.llv.nest.command.CommandExecutor
import jp.llv.nest.command.Func
import jp.llv.nest.command.obj.NestString
import jp.llv.nest.command.obj.bind.Binding
import jp.llv.nest.command.obj.bukkit.BukkitCommandSender
import org.bukkit.Server
import org.bukkit.command.CommandSender
import java.util.*
import java.util.stream.Collectors

/**
 * Created by toyblocks on 2016/05/14.
 */
class CommandFunc(val bukkit: Server) {

    @Func(name = ":b", value = "execute <command> on bukkit")
    fun execute(executor: CommandExecutor, sender: BukkitCommandSender<CommandSender>, binding: Binding<Any>,
              vararg command: NestString): Unit {
        bukkit.dispatchCommand(sender.unwrap(), Arrays.stream(command).map { it.unwrap() }.collect(Collectors.joining(" ")));
    }

}