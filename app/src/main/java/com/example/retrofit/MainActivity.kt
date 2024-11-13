package com.example.retrofit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitservices=RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

        val responseLiveData:LiveData<Response<Albums>> =
    liveData {
        val Response = retrofitservices.getAlbums()
        emit(Response)
    }
        responseLiveData.observe(this, Observer {
            val albumList=it.body()?.listIterator()
            if(albumList!=null){
                while (albumList.hasNext()){
                    val albumsItem=albumList.next()
                    val albumItem="Album Title:${albumsItem.title} \n"
                    binding.text.append(albumItem)
                }
            }
        })

    }
}