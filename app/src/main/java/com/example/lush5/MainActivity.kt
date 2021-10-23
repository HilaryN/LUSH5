package com.example.lush5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RetrofitAPI::class.java)

        api.getLaunches()?.enqueue(object : Callback<List<Launch?>?> {
            override fun onResponse(
                call: Call<List<Launch?>?>?,
                response: Response<List<Launch?>?>
            ) {
                val launchList: List<Launch?>? = response.body()

                //Create an array for the RecyclerView
                val launches = launchList?.let { arrayOfNulls<Launch>(it.size) }

                //Loop through all the launches and insert the names inside the string array
                if (launchList != null) {
                    for (i in launchList.indices) {
                        launches?.set(i, launchList[i])
                    }
                }

                val launchListView = findViewById<View>(R.id.launch_list) as RecyclerView
                //val adapter = LaunchAdapter(Launch.createLaunchList(20))
                val adapter = LaunchAdapter(launches)
                launchListView.adapter = adapter
                launchListView.layoutManager = LinearLayoutManager(applicationContext)
                //launchList.layoutManager = LinearLayoutManager(this)

            }

            override fun onFailure(call: Call<List<Launch?>?>?, t: Throwable) {
                Toast.makeText(
                    applicationContext, t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}