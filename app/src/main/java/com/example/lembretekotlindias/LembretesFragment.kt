package com.example.lembretekotlindias

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lembretekotlindias.databinding.FragmentLembretesBinding

class LembretesFragment : Fragment() {

    private var _binding: FragmentLembretesBinding? = null
    private val ui get() = _binding!!

    // Cria acesso ao SharedPreferences chamado "app_prefs"
    private val preferencias by lazy {
        requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    // Infla o layout do fragmento
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLembretesBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    // Quando a tela for criada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarLembrete()    // Preenche o campo com lembrete salvo, se houver
        configurarBotoes()    // Ativa os botões de salvar, excluir e sair
    }

    // Mostra lembrete salvo, se existir
    private fun carregarLembrete() {
        ui.textViewName.text = "Lembrete"
        ui.TextLembrete.setText(preferencias.getString("saved_note", ""))
    }

    // Configura as ações dos botões
    private fun configurarBotoes() = with(ui) {

        // Salva lembrete (se não estiver vazio)
        btnSalvar.setOnClickListener {
            val conteudo = TextLembrete.text.toString().trim()
            if (conteudo.isEmpty()) {
                exibirMensagem("O lembrete não pode estar vazio.")
            } else {
                preferencias.edit().putString("saved_note", conteudo).apply()
                exibirMensagem("Texto salvo com sucesso.")
            }
        }

        // Apaga o lembrete salvo
        btnExcluir.setOnClickListener {
            TextLembrete.text?.clear()
            preferencias.edit().remove("saved_note").apply()
            exibirMensagem("Texto deletado")
        }

        // Remove token e volta para tela inicial
        btnSair.setOnClickListener {
            preferencias.edit().remove("token").apply()
            redirecionarParaInicio()
        }
    }

    // Função simples pra mostrar uma mensagem
    private fun exibirMensagem(texto: String) {
        Toast.makeText(requireContext(), texto, Toast.LENGTH_SHORT).show()
    }

    // Volta para o HomeFragment
    private fun redirecionarParaInicio() {
        val nav = findNavController()
        val destino = R.id.HomeFragment
        // Tenta voltar no backstack, se não conseguir, navega direto
        if (!nav.popBackStack(destino, false)) {
            nav.navigate(destino)
        }
    }

    // Remove o binding ao destruir a view (pra evitar vazamento de memória)
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
