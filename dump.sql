INSERT INTO `usuarios` (`id`, `nome`, `email`, `senha`, `role`) VALUES
  (UNHEX(REPLACE(UUID(), '-', '')), 'admin', 'admin2@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'ADMIN');

 INSERT INTO `usuarios` (`id`, `nome`, `email`, `senha`, `role`) VALUES
  (UNHEX(REPLACE(UUID(), '-', '')), 'João Silva', 'joao@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Maria Costa', 'maria@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Pedro Almeida', 'pedro@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Ana Paula', 'ana@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Carlos Mendes', 'carlos@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Juliana Rocha', 'juliana@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Bruno Martins', 'bruno@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Fernanda Lima', 'fernanda@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Lucas Teixeira', 'lucas@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER'),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Aline Ferreira', 'aline@email.com', '$2a$10$AqOls1fKr2/BvgQx1.E3fed4tLwg9Mo/AhFWwctay3vX4rTVgMlQ', 'USER');

 
 INSERT INTO `produtos` (
  `id`, `nome`, `descricao`, `preco`, `categoria`,
  `quantidade_em_estoque`, `data_criacao`, `data_atualizacao`
) VALUES
  (UNHEX(REPLACE(UUID(), '-', '')), 'Camiseta Básica', 'Camiseta de algodão 100% para uso diário', 49.90, 'Vestuário', 100, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Fone Bluetooth', 'Fone de ouvido sem fio com cancelamento de ruído', 199.90, 'Eletrônicos', 35, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Garrafa Térmica', 'Garrafa de inox 1L para bebidas quentes e frias', 89.90, 'Casa', 20, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Notebook Dell', 'Notebook i5 11ª geração, SSD 512GB', 4299.00, 'Informática', 10, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Cadeira Gamer', 'Cadeira com ajuste ergonômico e apoio para lombar', 999.00, 'Móveis', 7, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Smartphone XYZ', 'Smartphone com 128GB, 8GB RAM', 2299.99, 'Eletrônicos', 25, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Livro Java Completo', 'Guia de referência para desenvolvedores Java', 89.90, 'Livros', 50, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Teclado Mecânico', 'Teclado RGB com switches vermelhos', 299.90, 'Periféricos', 40, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Tênis Esportivo', 'Tênis leve e resistente para corrida', 179.90, 'Calçados', 60, NOW(), NOW()),
  (UNHEX(REPLACE(UUID(), '-', '')), 'Relógio Digital', 'Relógio resistente à água com cronômetro', 149.90, 'Acessórios', 15, NOW(), NOW());