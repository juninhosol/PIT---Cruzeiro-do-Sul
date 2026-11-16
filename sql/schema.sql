-- Script de criação do banco de dados e tabela principal
CREATE DATABASE IF NOT EXISTS catalogo_db
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE catalogo_db;

CREATE TABLE IF NOT EXISTS item_midia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor_diretor VARCHAR(255) NOT NULL,
    ano_lancamento INT NOT NULL,
    genero VARCHAR(100) NOT NULL,
    sinopse TEXT,
    tipo_midia VARCHAR(50) NOT NULL
);

-- Dados iniciais de exemplo
INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES
('O Senhor dos Anéis: A Sociedade do Anel', 'J. R. R. Tolkien', 1954, 'Fantasia', 'Um jovem hobbit recebe a missão de destruir um anel mágico poderoso antes que caia nas mãos do Senhor do Escuro.', 'Livro'),
('Interestelar', 'Christopher Nolan', 2014, 'Ficção Científica', 'Uma equipe de exploradores viaja através de um buraco de minhoca no espaço na tentativa de garantir a sobrevivência da humanidade.', 'Filme'),
('Dark', 'Baran bo Odar', 2017, 'Suspense', 'O desaparecimento de duas crianças em uma pequena cidade alemã expõe segredos e conexões entre quatro famílias ao longo de gerações.', 'Série');
