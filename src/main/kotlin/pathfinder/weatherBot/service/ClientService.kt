package pathfinder.weatherBot.service

import jakarta.transaction.Transactional
import net.dv8tion.jda.api.entities.Guild
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.repository.ClientRepository

@Service
class ClientService(private val clientRepository: ClientRepository) {

    @Transactional
    fun createIfAbsent(guild: Guild) = if (!clientRepository.existsByGuildId(guild.idLong)) {
        clientRepository.saveAndFlush(Client.forGuild(guild))
        true
    } else false

    @Transactional
    fun delete(guild: Guild) = if (clientRepository.existsByGuildId(guild.idLong)) {
        clientRepository.deleteByGuildId(guild.idLong)
        clientRepository.flush()
        true
    } else false

    @Transactional
    fun getOrCreate(guild: Guild): Client = clientRepository.findByGuildId(guild.idLong)
        ?: clientRepository.saveAndFlush(Client.forGuild(guild))

    @Transactional
    fun perform(guild: Guild, transform: (Client) -> Unit) {
        val client = getOrCreate(guild)
        transform(client)
        save(client)
    }

    @Transactional
    fun perform(guilds: List<Guild>, transform: (Guild, Client) -> Unit) {
        guilds.associateWith(::getOrCreate)
            .map { (g, c) ->
                transform(g, c)
                c
            }
            .let(::save)
    }

    @Transactional
    fun save(client: Client): Client = clientRepository.saveAndFlush(client)

    @Transactional
    fun save(clients: List<Client>): List<Client?> = clientRepository.saveAllAndFlush(clients)
}