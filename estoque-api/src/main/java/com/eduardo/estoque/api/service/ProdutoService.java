package com.eduardo.estoque.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.estoque.api.model.Produto;
import com.eduardo.estoque.api.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Produto não encontrado"));
    }

    @Transactional
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, Produto dadosAtualizados) {
        Produto produto = buscarPorId(id);

        produto.setNome(dadosAtualizados.getNome());
        produto.setQuantidade(dadosAtualizados.getQuantidade());
        produto.setPreco(dadosAtualizados.getPreco());

        return produtoRepository.save(produto);
    }

    @Transactional
    public void remover(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}