package pathfinder.weatherBot

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions

val moderatorPermission = DefaultMemberPermissions.enabledFor(Permission.MANAGE_SERVER)
val dHundredException = RuntimeException("d% yielded a number not between 1 and 100.")