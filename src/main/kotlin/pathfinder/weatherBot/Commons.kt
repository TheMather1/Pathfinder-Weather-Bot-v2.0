package pathfinder.weatherBot

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import kotlin.math.absoluteValue
import kotlin.random.Random.Default.nextLong

infix fun Int.d(u: Int) = buildList(absoluteValue) { add(nextLong(1L, u.toLong())) }.sum()
    .let {
        if (this < 0) it.inv()
        else it
    }

val moderatorPermission = DefaultMemberPermissions.enabledFor(Permission.MANAGE_SERVER)
val dHundredException = RuntimeException("d% yielded a number not between 1 and 100.")