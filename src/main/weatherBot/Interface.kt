package weatherBot

import weatherBot.location.Climate
import weatherBot.location.Elevation
import weatherBot.time.Clock
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.system.exitProcess

val CONFIG_FILE = File("location.properties").apply { if (!exists()) createNewFile() }
var config = Properties()

fun main(args: Array<String>) = Interface()

object Interface{
    private val input: String?
        get() = System.`in`.run { while(available() < 1) Unit }.let { readLine()?.toUpperCase()?.trim() }

    operator fun invoke() {
        config.load(CONFIG_FILE.inputStream())
        config.manip(true)
        while(true){
            println("The bot is currently " + Clock.status())
            println("Enter command:")
            when(input){
                "HELP" -> TODO()
                "START" -> Clock()
                "STOP" -> Clock.stop()
                "EXIT" -> { Clock.stop(); exitProcess(0) }
                "CONFIG" -> config.manip(false)
                null -> println("You did not enter a command. Type \"HELP\" to view the list of commands.")
            }
            println()
        }
    }

    private fun Properties.manip(returnIfValid: Boolean) {
        fun hasImportant(): Boolean = keys.containsAll(listOf("climate", "elevation", "webhook_url"))
        fun hasAll(): Boolean = hasImportant() && "desert" in keys
        fun add(){
            fun climate(){
                while (true){
                    println("Enter a climate type. Valid types are COLD, TEMPERATE and TROPICAL.")
                    input?.let { Climate.valueOf(it) }?.let { setProperty("climate", it.name) }?.also { return } ?: println("That is not a valid climate type.\n")
                }
            }
            fun elevation(){
                while (true){
                    println("Enter a elevation type. Valid types are SEA_LEVEL, LOWLAND, HIGHLAND, HIGHLAND_ARID and HIGHLAND_MOUNTAIN.")
                    input?.let { Elevation.valueOf(it) }?.let { setProperty("elevation", it.name) }?.also { return } ?: println("That is not a valid elevation type.\n")
                }
            }
            fun desert(){
                while (true){
                    println("Enter TRUE or FALSE:")
                    input?.toBoolean()?.let { setProperty("desert", it.toString()) }?.also { return } ?: println("Invalid input.\n")
                }
            }
            fun webhook(){
                println("Enter webhook url:")
                while (input?.let { setProperty("webhook_url", it); false } ?: println("No input detected. Try again.").let { true }) Unit
            }
            while (true){
                println("\nEnter property to change, or DONE to return to the previous menu. Valid properties are CLIMATE, ELEVATION, DESERT and WEBHOOK_URL.")
                when(input){
                    "CLIMATE" -> climate()
                    "ELEVATION" -> elevation()
                    "DESERT" -> desert()
                    "WEBHOOK_URL" -> webhook()
                    "DONE" -> return
                    else -> println("Invalid property.\n")
                }
            }
        }
        entries.onEach { (key, _) -> key.toString().toLowerCase() }
        while (true) {
            if (!keys.contains("climate")) println("Climate not configured.")
            if (!keys.contains("elevation")) println("Elevation not configured.")
            if (!keys.contains("desert")) println("<optional> Desert not configured.")
            if (!keys.contains("webhook_url")) println("Webhook URL not configured.")
            if (!hasAll()) println("To add these, type \"ADD\" or manually add them to the location.properties file and reload the program.\n")
            if (returnIfValid && hasImportant()) return

            println("Enter command:")
            when (input) {
                "HELP" -> TODO()
                "ADD" -> add()
                "STOP" -> Clock.stop()
                "DONE" -> if (hasImportant()) return config.store(FileOutputStream(CONFIG_FILE), "save to properties file") else exitProcess(1)
                "CONFIG" -> println(config)
                null -> println("You did not enter a command. Type \"HELP\" to view the list of commands.")
            }
        }
    }
}