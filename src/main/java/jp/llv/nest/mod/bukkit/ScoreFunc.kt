package jp.llv.nest.mod.bukkit

import jp.llv.nest.command.Context
import jp.llv.nest.command.Func
import jp.llv.nest.command.obj.NestCommandSender
import jp.llv.nest.command.obj.NestInt
import jp.llv.nest.command.obj.NestString
import jp.llv.nest.command.obj.bukkit.BukkitPlayer
import org.bukkit.Server

/**
 * Created by toyblocks on 2016/06/17.
 */
class ScoreFunc(val bukkit: Server) {

    @Func(value = "return score value of <owner> in <objective> managed by main score board", perm = "score.get")
    fun get(context: Context<*>, objective: NestString, owner: NestString) : NestInt
            = NestInt(bukkit.scoreboardManager.getMainScoreboard().getObjective(objective.unwrap()).getScore(owner.unwrap()).score.toLong());

    @Func(value = "set <score> into <owner> in <objective> managed by main score board", perm = "score.set")
    fun set(context: Context<*>, objective: NestString, owner: NestString, score: NestInt) : Unit {
        bukkit.scoreboardManager.getMainScoreboard().getObjective(objective.unwrap()).getScore(owner.unwrap()).score = score.unwrap().toInt();
    }

    @Func(value = "return score value of <owner> in <objective> managed by sender's current scoreboard", perm = "score.getp")
    fun getp(context: Context<BukkitPlayer>, objective: NestString, owner: NestString) : NestInt
            = NestInt(context.sender.unwrap().scoreboard.getObjective(objective.unwrap()).getScore(owner.unwrap()).score.toLong());

    @Func(value = "set <score> into <owner> in <objective> managed by sender's current scoreboard", perm = "score.setp")
    fun setp(context: Context<BukkitPlayer>, objective: NestString, owner: NestString, score: NestInt) : Unit {
        context.sender.unwrap().scoreboard.getObjective(objective.unwrap()).getScore(owner.unwrap()).score = score.unwrap().toInt();
    }

}