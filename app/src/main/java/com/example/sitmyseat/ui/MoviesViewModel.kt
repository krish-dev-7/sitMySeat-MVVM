

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sitmyseat.models.MovieResponse
import com.example.sitmyseat.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel(
    val moviesRep: MoviesRepository

) : ViewModel() {

    val moviesPage: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()

    init {
        getMoviesVM()
    }

    private fun getMoviesVM() = viewModelScope.launch {
        moviesPage.postValue(Resource.Loading())
        val response = moviesRep.getMoviesR()
        moviesPage.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<MovieResponse>) : Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}