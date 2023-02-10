package mx.ulai.test.ui.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mx.ulai.test.application.scope.FragmentScope
import mx.ulai.test.ui.fragment.records.RecordsFragment

@Module
abstract class ModuleFragment {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeRecordsFragmentFragment(): RecordsFragment

}