package com.ullo.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken
import com.ullo.api.response.AppUser
import com.ullo.api.response.UserHome
import com.ullo.api.response.patient.Patient
import com.ullo.utils.Session.Key.APP_USER
import com.ullo.utils.Session.Key.APP_USER_ARCHIVE_LIST
import com.ullo.utils.Session.Key.APP_USER_HOME
import com.ullo.utils.Session.Key.APP_USER_PROGRESS_LIST
import com.ullo.utils.Session.Key.APP_USER_TOKEN
import java.util.ArrayList

class Session(context: Context) {

    private val PREF_NAME = "private_lindera"
    private val PRIVATE_MODE = 0
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val context: Context = context

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun setAppUser(appUser: AppUser) {
        editor.putString(APP_USER, SharedPreferenceHelper.getStringFromObject(appUser))
        editor.apply()
    }

    fun getAppUser(): AppUser {
        return SharedPreferenceHelper.getObjectFromString(pref.getString(APP_USER, ""), object :
                TypeToken<AppUser>() {
        })
    }

    fun setArchiveList(patientList: ArrayList<Patient>) {
        editor.putString(APP_USER_ARCHIVE_LIST, SharedPreferenceHelper.getStringFromObject(patientList))
        editor.apply()
    }

    fun getArchiveList(): ArrayList<Patient> {
        return SharedPreferenceHelper.getObjectFromString(pref.getString(APP_USER_ARCHIVE_LIST, ""), object :
                TypeToken<ArrayList<Patient>>() {
        })
    }

    fun setProgressList(patientList: ArrayList<Patient>) {
        editor.putString(APP_USER_PROGRESS_LIST, SharedPreferenceHelper.getStringFromObject(patientList))
        editor.apply()
    }

    fun getProgressList(): ArrayList<Patient> {
        return SharedPreferenceHelper.getObjectFromString(pref.getString(APP_USER_PROGRESS_LIST, ""), object :
                TypeToken<ArrayList<Patient>>() {
        })
    }

    fun setAppUserHome(userHome: UserHome) {
        editor.putString(APP_USER_HOME, SharedPreferenceHelper.getStringFromObject(userHome))
        editor.apply()
    }

    fun getAppUserHome(): UserHome {
        return SharedPreferenceHelper.getObjectFromString(pref.getString(APP_USER_HOME, ""), object :
                TypeToken<UserHome>() {
        })
    }

    fun setAppUserToken(token: String) {
        editor.putString(APP_USER_TOKEN, token)
        editor.apply()
    }

    fun getAppUserToken(): String {
        return pref.getString(APP_USER_TOKEN, "")
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }

    object Key {
        internal const val APP_USER = "app_user"
        internal const val APP_USER_HOME = "app_user_home"
        internal const val APP_USER_TOKEN = "app_user_token"
        internal const val APP_USER_PATIENT_LIST = "app_user_patient_list"
        internal const val APP_USER_ARCHIVE_LIST = "app_user_archive_list"
        internal const val APP_USER_PROGRESS_LIST = "app_user_progress_list"
    }
}