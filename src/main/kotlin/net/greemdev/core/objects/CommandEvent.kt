package net.greemdev.core.objects

import net.greemdev.core.util.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.jetbrains.annotations.NotNull
import java.util.*

public data class CommandEvent(
        @NotNull public val sender: CommandSender,
        @NotNull public val command: Command,
        @NotNull public val label: String,
        @NotNull public val args: Array<out String>) {

    companion object {
        public fun from(sender: CommandSender, command: Command, label: String, args: Array<out String>): CommandEvent {
            return CommandEvent(sender, command, label, args)
        }
    }

    public val player: Player = Objects.requireNonNull(sender.asPlayer())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommandEvent

        return when {
            sender != other.sender -> false
            command != other.command -> false
            label != other.label -> false
            else -> args.contentEquals(other.args)
        }
    }

    override fun hashCode(): Int {
        var result = sender.hashCode()
        result = 31 * result + command.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + args.contentHashCode()
        return result
    }
}