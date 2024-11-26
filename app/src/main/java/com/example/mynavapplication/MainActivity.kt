package com.example.mynavapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mynavapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        // 액션바 만들기
        val navController = binding.frgNav.getFragment<NavHostFragment>().navController
        appBarConfiguration = AppBarConfiguration(
            // top level fragment의 아이디의 집합을 넘긴다.
            setOf(R.id.aboutFragment, R.id.examineFragment, R.id.settingsFragment),
            binding.drawerLayout    // drawerLayout을 쓸 경우 해당 layout의 이름을 써주면 된다.
        )
        // top level에서는 백 화살표가 표시되지 않는다.
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.drawerNav.setupWithNavController(navController)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // 액션바 뒤로가기 버튼 활성화
    override fun onSupportNavigateUp(): Boolean {
        val navController = binding.frgNav.getFragment<NavHostFragment>().navController

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}