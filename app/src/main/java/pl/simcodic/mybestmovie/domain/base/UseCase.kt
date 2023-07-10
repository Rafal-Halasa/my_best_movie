package pl.simcodic.mybestmovie.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface UseCase {

    fun run()

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        run()
    }
}
