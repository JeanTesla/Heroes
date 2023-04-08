package com.example.heroes.utils

class AppConstants {
    companion object {
        const val MARVEL_API_BASE_URL = "https://gateway.marvel.com"
        const val MARVEL_API_KEY = "d4c8fd95be9546319aac0abb062c366e"
        const val MARVEL_API_HASH = "7f8ea4f2844dc78e81a8692985e32bb0"
        const val MARVEL_API_TIMESTAMP = "1"
        const val MARVEL_API_QUERY_STRING =
            "?apikey=${MARVEL_API_KEY}" +
                    "&hash=${MARVEL_API_HASH}" +
                    "&ts=${MARVEL_API_TIMESTAMP}" +
                    "&limit=50"
    }
}