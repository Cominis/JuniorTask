package com.dmt.juniortask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction


@Dao
abstract class ServerDao : BaseDao<ServerEntity> {

    @Query("SELECT * from servers_table")
    abstract fun getServers() : List<ServerEntity>

    @Query("SELECT * from servers_table")
    abstract fun getServersLiveData() : LiveData<List<ServerEntity>>

    @Query("SELECT * from servers_table WHERE id = :id")
    abstract fun getServerById(id : Long) : ServerEntity

    @Query("DELETE FROM servers_table")
    abstract fun eraseServers()

    @Transaction
    open fun refreshDatabase(vararg servers : ServerEntity) {
        eraseServers()
        insert(*servers)
    }
}