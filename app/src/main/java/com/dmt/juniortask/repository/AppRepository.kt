package com.dmt.juniortask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dmt.juniortask.db.ServerDatabase
import com.dmt.juniortask.db.ServerEntity
import com.dmt.juniortask.db.asDomainModel
import com.dmt.juniortask.domain.Server
import com.dmt.juniortask.network.ApiService
import com.dmt.juniortask.network.TokenProperty
import com.dmt.juniortask.network.UserProperty
import com.dmt.juniortask.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(private val database: ServerDatabase, private val apiService: ApiService) {

    val servers: LiveData<List<Server>> =
        Transformations.map(database.serverDao.getServersLiveData()) {
            it.asDomainModel()
        }

    suspend fun login(user : UserProperty) : TokenProperty = apiService.postToken(user)

    suspend fun getServers() : List<Server> {
        return withContext(Dispatchers.IO) {
            database.serverDao.getServers().asDomainModel()
        }
    }

    suspend fun getServerById(id : Long) : Server {
        return withContext(Dispatchers.IO) {
            database.serverDao.getServerById(id).asDomainModel()
        }
    }

    suspend fun insert(vararg servers: ServerEntity) : List<Long> {
        return withContext(Dispatchers.IO) {
            database.serverDao.insert(*servers)
        }
    }

    suspend fun update(vararg servers: ServerEntity) {
        withContext(Dispatchers.IO) {
            database.serverDao.update(*servers)
        }
    }

    suspend fun delete(vararg servers: ServerEntity) {
        withContext(Dispatchers.IO) {
            database.serverDao.delete(*servers)
        }
    }

    suspend fun eraseServers() {
        withContext(Dispatchers.IO) {
            database.serverDao.eraseServers()
        }
    }

    suspend fun refreshServers(token : String) {
        withContext(Dispatchers.IO) {
            val servers = apiService.getServers("Bearer $token")
            database.serverDao.refreshDatabase(*servers.asDatabaseModel())
        }
    }

}