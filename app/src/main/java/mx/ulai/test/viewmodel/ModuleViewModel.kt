package mx.ulai.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import mx.ulai.test.application.scope.ViewModelKey
import mx.ulai.test.ui.activity.navigation.NavigationViewModel
import mx.ulai.test.ui.fragment.records.RecordsViewModel

@Module
abstract class ModuleViewModel {


    @Binds
    @IntoMap
    @ViewModelKey(NavigationViewModel::class)
    abstract fun bindRepoViewModel(navigationViewModel: NavigationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecordsViewModel::class)
    abstract fun bindRecordsViewModel(recordsViewModelViewModel: RecordsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory

}
