package com.tifd.suitmediathoriq.network

import com.tifd.suitmediathoriq.screen.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object ApiService {
    suspend fun getUsers(page: Int = 1, perPage: Int = 10): List<User> = withContext(Dispatchers.IO) {
        val url = URL("https://reqres.in/api/users?page=$page&per_page=$perPage")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        if (connection.responseCode == 200) {
            val response = connection.inputStream.bufferedReader().readText()
            val jsonResponse = JSONObject(response)
            val data = jsonResponse.getJSONArray("data")

            List(data.length()) { index ->
                val item = data.getJSONObject(index)
                User(
                    id = item.getInt("id"),
                    email = item.getString("email"),
                    firstName = item.getString("first_name"),
                    lastName = item.getString("last_name"),
                    avatar = item.getString("avatar")
                )
            }
        } else {
            emptyList()
        }
    }
}
