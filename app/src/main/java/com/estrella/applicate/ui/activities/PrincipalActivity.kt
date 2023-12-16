package com.estrella.applicate.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.estrella.applicate.R
import com.estrella.applicate.core.Applicate
import com.estrella.applicate.databinding.ActivityPrincipalBinding
import com.estrella.applicate.logic.usercases.LoginUserCase
import com.estrella.applicate.ui.core.Constants
import com.estrella.applicate.ui.fragments.FavoritesFragment
import com.estrella.applicate.ui.fragments.ListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
        checkDataBase()
    }

    // Llamar a un fragment.
    val listFragment = ListFragment()
    val favoriteFragment = FavoritesFragment()

    fun initListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val manager = supportFragmentManager
            when (item.itemId) {
                R.id.item_1 -> {
                    val transaction = manager.beginTransaction()
                    transaction.replace(binding.frmContainer.id, listFragment)
                    transaction.commit()

                    true
                }

                R.id.item_2 -> {
                    val transaction = manager.beginTransaction()
                    transaction.replace(binding.frmContainer.id, favoriteFragment)
                    transaction.commit()
                    true
                }


                else -> {
                    lifecycleScope.launch(Dispatchers.Main) {
                        val name1 = withContext(Dispatchers.IO) {
                            val a = "Dillan"
                            val b = a + " Coloma"
                            b
                        }

                        val w = withContext(Dispatchers.Default) {
                            val ListC = listOf(
                                async { getName() },
                                async { getName() }
                            )
                            ListC.awaitAll()
                        }

                        val name2 = withContext(Dispatchers.IO) {
                            getName()
                        }
                        binding.textUser.text = name2
                        binding.bottomNavigation

                    }
                    false
                }

            }
        }
    }

    private fun checkDataBase() {
        lifecycleScope.launch(Dispatchers.Main) {
            val usurs = withContext(Dispatchers.IO) {
                LoginUserCase(Applicate.getConnectionDB()!!)
                    .getAllUsers()
            }
            Log.d(Constants.TAG, usurs.toString())
        }

    }


}




suspend fun getName(): String {
    val a = "Dillan"
    val b = a + " Coloma"
    return b
}



