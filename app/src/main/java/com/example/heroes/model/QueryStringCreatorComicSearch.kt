package com.example.heroes.model

class QueryStringCreatorComicSearch {

    var titleStartsWith: String? = null
    var limit: Int? = null
    var initialDate: String? = null
    var finalDate: String? = null
    var format: String? = null
    var startYear: String? = null

    fun getConcatenatedDateRange(): String? {
        return if (initialDate !== null && finalDate !== null)
            "$initialDate,$finalDate" else null
    }

    fun clearAll() {
        titleStartsWith = null
        limit = null
        initialDate = null
        finalDate = null
        format = null
        startYear = null
    }

    fun clearInitialParameters(){
        format = null
        startYear = null
    }

}