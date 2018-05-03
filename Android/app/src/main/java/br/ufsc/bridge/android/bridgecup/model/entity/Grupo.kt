package br.ufsc.bridge.android.bridgecup.model.entity

data class Grupo(
        val idGrupo: String,
        val letra: String,
        val selecoes: List<Selecao>
)
