package pathfinder.weatherBot.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pathfinder.weatherBot.interaction.Client

@Repository
interface ClientRepository: JpaRepository<Client, Long> {
    fun existsByGuildId(guildId: Long): Boolean
    fun findByGuildId(guildId: Long): Client?
    fun deleteByGuildId(guildId: Long)
}