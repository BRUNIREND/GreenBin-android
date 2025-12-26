package com.example.data.auth.repository


import com.example.data.R
import com.example.domain.learning.model.LearningTopic
import com.example.domain.learning.model.Lesson
import com.example.domain.learning.model.Question
import com.example.domain.learning.model.Test
import com.example.domain.learning.model.TheoryCategory
import com.example.domain.learning.repository.ILearningRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LearningRepositoryImpl @Inject constructor() : ILearningRepository {

    override fun getTopics(): Flow<List<LearningTopic>> = flow {
        emit(hardcodedTopics)
    }

    override fun getTheoryCategories(topicId: String): Flow<List<TheoryCategory>> = flow {
        emit(hardcodedTheory[topicId] ?: emptyList())
    }

    override fun getTests(topicId: String): Flow<List<Test>> = flow {
        emit(hardcodedTests[topicId] ?: emptyList())
    }

    override fun getLessons(topicId: String): Flow<List<Lesson>> = flow {
        emit(hardcodedLessons[topicId] ?: emptyList())
    }
    override fun getQuestions(testId: String): Flow<List<Question>> = flow {
        emit(hardcodedQuestions[testId] ?: emptyList())
    }
    private val hardcodedTopics = listOf(
        LearningTopic(
            id = "intro",
            title = "Введение в экологию и переработку",
            subtitle = "С чего начинается забота о планете: простыми словами о сложном",
            iconRes = R.drawable.ic_ecology_intro, // добавь drawable
            totalLessons = 11,
            completedLessons = 0,
            totalTests = 10,
            completedTests = 1
        ),
        LearningTopic(
            id = "sorting",
            title = "Сортировка отходов — зачем и как?",
            subtitle = "Научись разделять мусор правильно и с пользой для природы",
            iconRes = R.drawable.ic_sorting,
            totalLessons = 15,
            completedLessons = 0,
            totalTests = 10,
            completedTests = 1
        ),
        LearningTopic(
            id = "recycling",
            title = "Утилизация, переработка отходов",
            subtitle = "Узнай, как утилизировать отходы без вреда для природы",
            iconRes = R.drawable.ic_recycling,
            totalLessons = 5,
            completedLessons = 0,
            totalTests = 10,
            completedTests = 1
        ),
        LearningTopic(
            id = "habits",
            title = "Экопривычки и как начать жить экологичнее",
            subtitle = "Простые шаги к экологичному образу жизни — без стресса и фанатизма",
            iconRes = R.drawable.ic_habits,
            totalLessons = 0,
            completedLessons = 0,
            totalTests = 10,
            completedTests = 1
        )
    )

    private val hardcodedTheory = mapOf(
        "intro" to listOf(
            TheoryCategory("1", "Основные экологические проблемы: мусор, загрязнение, климат", 6, 21),
            TheoryCategory("2", "Как человек влияет на природу: статистика и примеры", 6, 100),
            TheoryCategory("3", "Круговорот отходов в природе и почему он нарушен?", 6, 21),
            TheoryCategory("4", "Что такое экология и как его уменьшить?", 6, 21)
        )
    )
    private val hardcodedLessons = mapOf(
        "intro" to listOf(
            Lesson("1", "Основные экологические проблемы: мусор, загрязнение, климат.", 10, true),
            Lesson("2", "Экологические проблемы мусор, загрязнение, климат.", 10, true),
            Lesson("3", "Круговорот отходов в природе и почему он нарушен?", 10, false),
            // ...
        )
    )
    private val hardcodedTests = mapOf(
        "intro" to listOf(
            Test(
                "1",
                "Основные экологические проблемы: мусор, загрязнение и климатическая угроза",
                15,
                21,
                isLocked = true
            ),
            Test("2", "Экологические проблемы: мусор, загрязнение и климатическая угроза", 15, 21),
            Test("3", "Влияние человека на природу", 15, 100, false, true),
            Test("4", "Круговорот отходов в природе", 15, 21),
            Test("5", "Экослед и практики его уменьшения", 15, 21)
        )
    )
//    private val hardcodedTests = mapOf(
//        "intro" to listOf(
//            LearningTest(
//                "1",
//                "Основные экологические проблемы: мусор, загрязнение и климатическая угроза",
//                15,
//                0,
//                isLocked = true
//            ),
//            LearningTest("2", "Экологические проблемы: мусор, загрязнение и климатическая угроза", 15, 21),
//            LearningTest("3", "Влияние человека на природу", 15, 100, isCompleted = true),
//            LearningTest("4", "Круговорот отходов в природе", 15, 21),
//            LearningTest("5", "Экослед и практики его уменьшения", 15, 21)
//        )
//    )

    private val hardcodedQuestions = mapOf(
        "3" to listOf( // тест "Влияние человека на природу" (3й в карточках)
            Question(
                "1",
                "Какой газ является основным причиной глобального потепления?",
                listOf("Кислород", "Углекислый газ", "Азот", "Водород"),
                1
            ),
            Question("2", "Что такое биоразлагаемые отходы?", listOf("Отходы, которые разлагаются естественно", "Отходы, которые не разлагаются", "Пластик", "Металл"), 0),
            Question("3", "Какое влияние оказывает пластик на океаны?", listOf("Улучшает экосистему", "Загрязняет и убивает морскую жизнь", "Увеличивает биоразнообразие", "Никакого"), 1),
            Question("4", "Что такое возобновляемые источники энергии?", listOf("Уголь, нефть", "Солнце, ветер", "Газ", "Атомная энергия"), 1),
            Question("5", "Как уменьшить углеродный след?", listOf("Ездить на машине", "Использовать общественный транспорт", "Больше потреблять", "Не перерабатывать"), 1)
        )
    )
}