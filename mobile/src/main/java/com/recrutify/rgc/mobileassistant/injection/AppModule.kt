package com.recrutify.rgc.mobileassistant.injection

import android.app.Application
import android.arch.persistence.room.Room
import com.recrutify.rgc.mobileassistant.common.LiveDataCallAdapterFactory
import com.recrutify.rgc.mobileassistant.db.LocalDB
import com.recrutify.rgc.mobileassistant.db.UserDao
import com.recrutify.rgc.mobileassistant.login.AuthService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
internal class AppModule {

    @Singleton
    @Provides
    fun provideAuthService(): AuthService {
        return Retrofit.Builder()
                .baseUrl("https://api.recrutify.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): LocalDB {
        return Room
                .databaseBuilder(app, LocalDB::class.java, "rgcmain.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: LocalDB): UserDao {
        return db.userDao()
    }

}