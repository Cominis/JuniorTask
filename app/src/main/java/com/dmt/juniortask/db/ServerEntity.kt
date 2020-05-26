package com.dmt.juniortask.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmt.juniortask.domain.Server

@Entity(tableName = "servers_table")
data class ServerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val country: String,
    val distance: Long,
    val tag: Int
)

fun List<ServerEntity>.asDomainModel(): List<Server> {
    return map {
        Server(
            id = it.id,
            country = it.country,
            distance = it.distance
        )
    }
}

fun ServerEntity.asDomainModel(): Server {
    return Server(
            id = this.id,
            country = this.country,
            distance = this.distance
        )
}