package pl.simcodic.mybestmovie.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface UseCase<in IN : Input, out OUT : Output> {

    fun run(input: IN): OUT

    suspend operator fun invoke(input: IN) = withContext(Dispatchers.IO) {
        run(input)
    }
}

sealed interface UseCaseData
interface Input : UseCaseData
interface Output : UseCaseData
object NonInput : Input
object NonOutput : Output