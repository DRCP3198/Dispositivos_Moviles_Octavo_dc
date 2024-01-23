package com.examenp.dillan.dispositivosm_recyclewie_dc.ui.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.examenp.dillan.dispositivosm_recyclewie_dc.R
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.entities.Users
import com.examenp.dillan.dispositivosm_recyclewie_dc.databinding.ActivityMainBinding
import com.examenp.dillan.dispositivosm_recyclewie_dc.ui.adapters.UsersAdapter

class MainActivity : AppCompatActivity() {


    private var userList: MutableList<Users> = ArrayList<Users>()
    
    private var count:Int=1
    private lateinit var binding: ActivityMainBinding
    private var usersAdapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycleView()
        initListeners()
    }

    fun initRecycleView() {
        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )

    }

    fun initListeners() {
        binding.btnListener.setOnClickListener {
            val usuarios = Users(
                1,
                "Dillan",
                "Estudiante",
                "https://www.google.com/search?sca_esv=b1246719c6e8d448&rlz=1C1VDKB_esEC1083EC1083&sxsrf=ACQVn088Y-nUmnSZ8k8RdJ05Fme0ZkZOPw:1705757571748&q=imagen+estudiante&tbm=isch&source=lnms&sa=X&ved=2ahUKEwjR-IuRiuyDAxWsRTABHXE2D5sQ0pQJegQIDBAB&biw=1707&bih=811&dpr=1.13#imgrc=cIwiQ2DmZXRWXM"
            )

            userList.add(usuarios)
            Log.d("List", userList.toString())
            usersAdapter.listUsers= userList
            usersAdapter.notifyDataSetChanged()
        }
    }
}