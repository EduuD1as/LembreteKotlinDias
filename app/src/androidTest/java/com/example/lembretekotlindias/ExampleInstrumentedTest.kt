package com.example.lembretekotlindias

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.lembretekotlindias", appContext.packageName)
    val activityScenario: ActivityScenario<MainActivity> =
        ActivityScenario.launch(MainActivity::class.java)

        activityScenario.moveToState(Lifecycle.State.RESUMED)

        //Tela de Login
        onView(withId(R.id.textView)).check(matches(isDisplayed()))
        onView(withId(R.id.textView)).check(matches(withText("Seja bem Vindo")))

        onView(withId(R.id.textView2)).check(matches(isDisplayed()))
        onView(withId(R.id.textView2)).check(matches(withText("Login :")))

        onView(withId(R.id.textView3)).check(matches(isDisplayed()))
        onView(withId(R.id.textView3)).check(matches(withText("Senha :")))

        onView(withId(R.id.nome_edt)).check(matches(isDisplayed()))
        onView(withId(R.id.sobrenome_edt)).check(matches(isDisplayed()))

        onView(withId(R.id.btnEntrar2)).check(matches(isDisplayed()))
        onView(withId(R.id.btnEntrar2)).check(matches(withText("Proxima")))

        onView(withId(R.id.btnSignup)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSignup)).check(matches(withText("Cadastro")))
        onView(withId(R.id.btnSignup)).perform(click())
        //---------------------------------------------------------------

        //Tela de Cadastro
        onView(withId(R.id.textView4)).check(matches(isDisplayed()))
        onView(withId(R.id.textView4)).check(matches(withText("Cadastro")))

        onView(withId(R.id.textView5)).check(matches(isDisplayed()))
        onView(withId(R.id.textView5)).check(matches(withText("Nome :")))

        onView(withId(R.id.textView6)).check(matches(isDisplayed()))
        onView(withId(R.id.textView6)).check(matches(withText("Email :")))

        onView(withId(R.id.textView7)).check(matches(isDisplayed()))
        onView(withId(R.id.textView7)).check(matches(withText("Senha :")))

        onView(withId(R.id.textViewSignupName)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewEmailSignup)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewPasswordSignup)).check(matches(isDisplayed()))

        onView(withId(R.id.textViewSignupName)).perform(typeText("Thiago"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.textViewSignupName)).check(matches(withText("Thiago")))

        onView(withId(R.id.textViewEmailSignup)).perform(typeText("Thiago@gmail.com"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.textViewEmailSignup)).check(matches(withText("Thiago@gmail.com")))

        onView(withId(R.id.textViewPasswordSignup)).perform(typeText("123456789"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.textViewPasswordSignup)).check(matches(withText("123456789")))

        onView(withId(R.id.btnSignup2)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSignup2)).check(matches(withText("Cadastrar")))
        onView(withId(R.id.btnSignup2)).perform(click())

        //----------------------------------------------------------

        Thread.sleep(2000)

        onView(withId(R.id.nome_edt)).check(matches(isDisplayed()))
        onView(withId(R.id.nome_edt)).perform(typeText("Thiago@gmail.com"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.nome_edt)).check(matches(withText("Thiago@gmail.com")))

        onView(withId(R.id.sobrenome_edt)).check(matches(isDisplayed()))
        onView(withId(R.id.sobrenome_edt)).perform(typeText("123456789"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.sobrenome_edt)).check(matches(withText("123456789")))

        onView(withId(R.id.btnEntrar2)).check(matches(isDisplayed()))
        onView(withId(R.id.btnEntrar2)).check(matches(withText("Proxima")))
        onView(withId(R.id.btnEntrar2)).perform(click())


        Thread.sleep(2000)

        //Tela de Lembrete
        onView(withId(R.id.Nome_txv)).check(matches(isDisplayed()))
        onView(withId(R.id.Nome_txv)).check(matches(withText("Olá, null")))

        onView(withId(R.id.TextLembrete)).check(matches(isDisplayed()))

        onView(withId(R.id.TextLembrete)).perform(typeText("The Isle e um jogo"))

        onView(withId(R.id.btnSalvar)).check(matches(isDisplayed()))
        onView(withText("Salvar")).check(matches(isDisplayed()))
        onView(withId(R.id.btnSalvar)).perform(click())

        onView(withId(R.id.TextLembrete)).check(matches(withText("The Isle e um jogo")))

        onView(withId(R.id.btnSair)).check(matches(isDisplayed()))
        onView(withText("Voltar")).check(matches(isDisplayed()))
        onView(withId(R.id.btnSair)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.nome_edt)).check(matches(isDisplayed()))
        onView(withId(R.id.nome_edt)).perform(typeText("Thiago@gmail.com"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.nome_edt)).check(matches(withText("Thiago@gmail.com")))

        onView(withId(R.id.sobrenome_edt)).check(matches(isDisplayed()))
        onView(withId(R.id.sobrenome_edt)).perform(typeText("123456789"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.sobrenome_edt)).check(matches(withText("123456789")))

        onView(withId(R.id.btnEntrar2)).check(matches(isDisplayed()))
        onView(withId(R.id.btnEntrar2)).check(matches(withText("Proxima")))
        onView(withId(R.id.btnEntrar2)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.Nome_txv)).check(matches(isDisplayed()))
        onView(withId(R.id.Nome_txv)).check(matches(withText("Olá, null")))

        onView(withId(R.id.TextLembrete)).check(matches(withText("The Isle e um jogo")))

        onView(withId(R.id.btnExcluir)).check(matches(isDisplayed()))
        onView(withText("Deletar")).check(matches(isDisplayed()))
        onView(withId(R.id.btnExcluir)).perform(click())

        onView(withId(R.id.btnSair)).check(matches(isDisplayed()))
        onView(withText("Voltar")).check(matches(isDisplayed()))
        onView(withId(R.id.btnSair)).perform(click())
        //Testes

        Thread.sleep(2000)

        onView(withId(R.id.nome_edt)).check(matches(isDisplayed()))
        onView(withId(R.id.nome_edt)).perform(typeText("Thiago@gmail.com"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.nome_edt)).check(matches(withText("Thiago@gmail.com")))

        onView(withId(R.id.sobrenome_edt)).check(matches(isDisplayed()))
        onView(withId(R.id.sobrenome_edt)).perform(typeText("123456789"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.sobrenome_edt)).check(matches(withText("123456789")))

        onView(withId(R.id.btnEntrar2)).check(matches(isDisplayed()))
        onView(withId(R.id.btnEntrar2)).check(matches(withText("Proxima")))
        onView(withId(R.id.btnEntrar2)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.Nome_txv)).check(matches(isDisplayed()))
        onView(withId(R.id.Nome_txv)).check(matches(withText("Olá, null")))

        onView(withId(R.id.TextLembrete)).check(matches(withText("")))

        onView(withId(R.id.btnSair)).check(matches(isDisplayed()))
        onView(withText("Voltar")).check(matches(isDisplayed()))
        onView(withId(R.id.btnSair)).perform(click())


    }
}