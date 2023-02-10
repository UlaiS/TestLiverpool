package mx.ulai.test.ui.activity.navigation

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import mx.ulai.test.MainActivity
import mx.ulai.test.R
import mx.ulai.test.databinding.ActivityNavigationBinding

class NavigationActivity: MainActivity<ActivityNavigationBinding, NavigationViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.lifecycleOwner = this
        setSupportActionBar(dataBinding.toolbar)
        supportActionBar?.let { it.title = "Liverpool" }
        if (savedInstanceState == null) {
            val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_records,
            ).build()
            val navController = Navigation.findNavController(this, R.id.container)
            NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
            NavigationUI.setupWithNavController(dataBinding.navView, navController)
        }
    }

    override val layoutRes = R.layout.activity_navigation

    override fun getViewModel() = NavigationViewModel::class.java

}