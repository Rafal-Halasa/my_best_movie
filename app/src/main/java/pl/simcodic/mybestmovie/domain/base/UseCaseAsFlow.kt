package pl.simcodic.mybestmovie.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


interface UseCaseAsFlow<in IN : Input, out OUT : Flow<Output>> {

    fun run(input: IN): OUT

    suspend operator fun invoke(input: IN) = withContext(Dispatchers.IO) {
        run(input)
    }
}