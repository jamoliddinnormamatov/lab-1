interface ApiService {
    @GET("special-endpoint")
    suspend fun getSpecialData(): Response<SpecialData>
}