package com.example.lembretekotlindias

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lembretekotlindias.databinding.FragmentLembretesBinding

class SignupFragment : Fragment() {

    private var _binding: FragmentLembretesBinding? = null
    private val bind get() = _binding!!

    // Preferências para salvar o lembrete e token localmente
    private val prefs by lazy {
        requireActivity().getSharedPreferences("signup_prefs", Context.MODE_PRIVATE)
    }

    // Editor para modificar as preferências
    private val editor by lazy { prefs.edit() }

    // Infla o layout do fragmento
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLembretesBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    // Após a view ser criada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarTela()    // Mostra o conteúdo salvo no campo
        configurarEventos() // Define o que acontece nos botões
    }

    // Carrega o lembrete salvo (se tiver)
    private fun configurarTela() = with(bind) {
        textViewName.text = "Lembrete" // Título da tela
        TextLembrete.setText(prefs.getString("saved_note", "")) // Preenche o campo com o texto salvo
    }

    // Define os cliques dos botões
    private fun configurarEventos() = with(bind) {

        // Botão salvar: salva o texto se não estiver vazio
        btnSalvar.setOnClickListener {
            val texto = TextLembrete.text?.toString().orEmpty()
            if (texto.isNotBlank()) {
                editor.putString("saved_note", texto).apply()
                mostrarToast("Texto salvo com sucesso!")
            } else {
                mostrarToast("Não é possível salvar um lembrete vazio.")
            }
        }

        // Botão excluir: limpa o campo e remove o lembrete salvo
        btnExcluir.setOnClickListener {
            TextLembrete.text?.clear()
            editor.remove("saved_note").apply()
            mostrarToast("Lembrete excluído")
        }

        // Botão sair: remove o token e volta pra tela inicial
        btnSair.setOnClickListener {
            editor.remove("token").apply()
            findNavController().navigate(R.id.action_LembretesFragment_to_HomeFragment)
        }
    }

    // Função rápida para mostrar mensagens pro usuário
    private fun mostrarToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
