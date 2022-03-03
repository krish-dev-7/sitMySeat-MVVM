import com.example.sitmyseat.api.RetroFitInstance

class MoviesRepository(
) {
    suspend fun getMoviesR() =
        RetroFitInstance.api.getMovies()
}