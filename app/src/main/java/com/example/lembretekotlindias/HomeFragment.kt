package com.example.lembretekotlindias

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lembretekotlindias.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var auth: FirebaseAuth // Firebase Auth para login
    private lateinit var context: Context   // Contexto da aplicação
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Cria a visualização do fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        auth = Firebase.auth // Inicializa autenticação do Firebase
        context = requireContext() // Salva o contexto

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root // Retorna a root view
    }

    // Depois que a view for criada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val editor = prefs.edit() // Editor para salvar token

        binding.apply {
            // Quando clicar em Entrar
            btnEntrar2.setOnClickListener {
                realizarLogin(
                    email = nomeEdt.text.toString(),            // Pega e-mail do campo
                    senha = sobrenomeEdt.text.toString(),       // Pega senha do campo
                    salvarToken = { token ->                    // Salva token se o login funcionar
                        editor.putString("token", token).apply()
                        findNavController().navigate(R.id.action_HomeFragment_to_LembretesFragment)
                    }
                )
            }

            // Quando clicar em Cadastrar, executa a função ir na tela de Cadastro
            btnSignup.setOnClickListener {
                navegarCadastro()
            }
        }
    }

    // Leva para a tela de cadastro
    private fun navegarCadastro() {
        findNavController().navigate(R.id.action_HomeFragment_to_SignupFragment)
    }

    // Faz login com Firebase usando e-mail e senha
    private fun realizarLogin(email: String, senha: String, salvarToken: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                // Se login deu certo, pega o token
                auth.currentUser?.getIdToken(true)?.addOnSuccessListener { tokenResult ->
                    tokenResult.token?.takeIf { it.isNotEmpty() }?.let { token ->
                        salvarToken(token) // Salva o token
                    }
                }
            } else {
                // Se deu erro no login, mostra no log
                Log.e("LOGIN", "Erro ao fazer login", result.exception)
            }
        }
    }

    // Limpa o binding pra evitar vazamento de memória
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
