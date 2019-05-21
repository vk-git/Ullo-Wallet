package com.ullo.ui.choose_patient

import android.content.Context
import com.ullo.adapter.ContactAdapter
import dagger.Module
import dagger.Provides

@Module
class ChoosePatientActivityModule {

    @Provides
    fun provideContactAdapter(context: Context): ContactAdapter {
        return ContactAdapter(context)
    }
}