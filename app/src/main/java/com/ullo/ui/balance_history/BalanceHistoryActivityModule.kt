package com.ullo.ui.balance_history

import android.content.Context
import com.ullo.adapter.ContactAdapter
import dagger.Module
import dagger.Provides

@Module
class BalanceHistoryActivityModule {

    @Provides
    fun provideContactAdapter(context: Context): ContactAdapter {
        return ContactAdapter(context)
    }
}