package com.dmt.juniortask.network

import com.dmt.juniortask.db.ServerEntity

data class ServerProperty(val name: String, val distance: Int)

fun List<ServerProperty>.asDatabaseModel(): Array<ServerEntity> {
    return map {
        val names = it.name.split(" #")
        ServerEntity(
            id = 0L,
            country = names[0],
            distance = it.distance.toLong(),
            tag = names[1].toInt()
        )
    }.toTypedArray()
}