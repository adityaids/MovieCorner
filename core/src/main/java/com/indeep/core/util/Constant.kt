package com.indeep.core.util

import com.indeep.core.BuildConfig

class Constant {
    companion object{
        const val TOKEN_KEY = BuildConfig.AUTH_TOKEN
        const val LANGUAGE = "en-US"
        const val SORT_BY = "popularity.desc"
        const val SITE_URL = "https://www.youtube.com/watch?v="
    }
}