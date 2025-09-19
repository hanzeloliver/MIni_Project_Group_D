package com.example.miniprojectgroupd

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewUsers) // id di activity_main.xml
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Call API pakai Retrofit
        RetrofitClient.instance.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()?.mapIndexed { index, user ->
                        user.copy(
                            nim = "0000${index + 1}",             // dummy nim
                            phone = "0812-3456-789${index}"      // dummy phone
                        )
                    } ?: emptyList()

                    recyclerView.adapter = UserAdapter(users)
                } else {
                    Toast.makeText(this@MainActivity, "Gagal ambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}