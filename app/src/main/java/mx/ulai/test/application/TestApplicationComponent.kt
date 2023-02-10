package mx.ulai.test.application

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import mx.ulai.test.api.ApiLiverpoolModule
import mx.ulai.test.application.scope.ApplicationScope
import mx.ulai.test.db.AppDataBaseModule
import mx.ulai.test.ui.activity.ModuleActivity
import javax.inject.Singleton

@Singleton
@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppDataBaseModule::class,
    ApiLiverpoolModule::class,
    ModuleActivity::class
])
interface TestApplicationComponent {
    fun inject(app: TestApplication)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(aplication: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }
}