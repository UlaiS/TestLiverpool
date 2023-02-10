package mx.ulai.test.ui.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mx.ulai.test.application.scope.ActivityScope
import mx.ulai.test.ui.activity.navigation.NavigationActivity
import mx.ulai.test.ui.fragment.ModuleFragment
import mx.ulai.test.viewmodel.ModuleViewModel

@Module(includes = [
    ModuleFragment::class,
    ModuleViewModel::class
])
abstract class ModuleActivity {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeNavigationActivity(): NavigationActivity

}