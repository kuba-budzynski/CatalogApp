package com.example.katalog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.apollographql.apollo.coroutines.await
import com.example.GetAllCategoriesQuery
import com.example.GetAllToListQuery
import com.example.katalog.databinding.ActivitySplashScreenBinding
import com.google.gson.Gson
import kotlinx.coroutines.*

class SplashScreen : FullScreenActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var characters = GlobalScope.async{
            withTimeoutOrNull(10000){
                val response = apolloClient.query(GetAllToListQuery()).await()
                return@withTimeoutOrNull response.data?.characters
            }
        }

        var categories = GlobalScope.async{
            withTimeoutOrNull(10000){
                val response = apolloClient.query(GetAllCategoriesQuery()).await()
                return@withTimeoutOrNull response.data?.kategorias
            }
        }

        runBlocking {
            val dataCharacters = characters.await()
            val dataCategories = categories.await()
            Handler().postDelayed({
                val i = Intent(this@SplashScreen, BaseActivity::class.java)
                i.putExtra("characters", Gson().toJson(dataCharacters))
                i.putExtra("categories", Gson().toJson(dataCategories))
                startActivity(i)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }, 3000)
        }
    }
}