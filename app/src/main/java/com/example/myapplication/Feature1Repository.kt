class Feature1Repository(private val apiService: ApiService) {

    suspend fun fetchSpecialData(): SpecialData? {
        val response = apiService.getSpecialData()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}