package com.example.lembretekotlindias

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lembretekotlindias.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// Fragmento responsável pela tela de cadastro
class SignupFragment : Fragment() {

    // Binding da view (acesso aos elementos do layout XML)
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    // Instância de autenticação do Firebase
    private lateinit var auth: FirebaseAuth

    // Cria a view do fragmento e inicializa o Firebase Auth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        auth = Firebase.auth // Inicializa o FirebaseAuth
        return binding.root // Retorna a raiz da view
    }

    // Quando a view for criada, configura os botões
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    // Configura os listeners dos botões
    private fun setupListeners() = binding.apply {

        // Botão de voltar: navega para a tela anterior (HomeFragment)
        btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.action_SignupFragment_to_HomeFragment)
        }

        // Botão de cadastro
        btnSignup2.setOnClickListener {
            // Pega o email e senha digitados pelo usuário
            val email = textViewEmailSignup.text.toString().trim()
            val password = textViewPasswordSignup.text.toString().trim()

            // Verifica se os campos estão preenchidos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Email e senha não podem estar vazios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Chama função para criar usuário no Firebase
            cadastrarUsuario(email, password)
        }
    }

    // Realiza o cadastro no Firebase
    private fun cadastrarUsuario(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Cadastro feito com sucesso
                Log.d("Cadastro", "Usuário criado com sucesso")
                findNavController().navigate(R.id.action_SignupFragment_to_HomeFragment)
            }
            .addOnFailureListener { exception ->
                // Trata erro no cadastro e mostra mensagem
                val mensagem = when (exception) {
                    is FirebaseAuthException -> exception.message ?: "Erro no cadastro"
                    else -> "Falha ao cadastrar usuário"
                }
                Toast.makeText(requireContext(), mensagem, Toast.LENGTH_LONG).show()
                Log.e("Cadastro", "Erro: ${exception.message}", exception)
            }
    }

    // Libera o binding ao destruir a view (boa prática)
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
