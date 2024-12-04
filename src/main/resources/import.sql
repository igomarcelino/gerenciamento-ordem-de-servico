-- Seeds Endereco
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (12345678, 'Centro', 'São Paulo', 'Apto 101', 'SP', '123', 'Rua das Flores');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (87654321, 'Jardim', 'Rio de Janeiro', 'Casa', 'RJ', '456', 'Rua das Palmeiras');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (11223344, 'Bairro Alto', 'Curitiba', '', 'PR', '789', 'Rua do Sol');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (44332211, 'Liberdade', 'Salvador', '', 'BA', '101', 'Rua do Horizonte');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (33445566, 'Cidade Nova', 'Belo Horizonte', 'Apto 202', 'MG', '202', 'Rua das Estrelas');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (66554433, 'Centro', 'Porto Alegre', 'Apto 303', 'RS', '303', 'Rua da Lua');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (77889900, 'Vila Nova', 'Florianópolis', '', 'SC', '404', 'Rua do Mar');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (99008877, 'Santana', 'Fortaleza', 'Casa', 'CE', '505', 'Rua das Ondas');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (00998877, 'Cidade Velha', 'Belém', '', 'PA', '606', 'Rua do Rio');
INSERT INTO endereco (cep, bairro, cidade, complemento, estado, numero, rua) VALUES (22334455, 'Centro', 'Manaus', 'Apto 404', 'AM', '707', 'Rua do Amazonas');

-- Seed Clientes
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES ('12345678901', 'joao.silva@example.com', 'João Silva', '55512345678', 1);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES ('23456789012', 'maria.souza@example.com', 'Maria Souza', '55587654321', 2);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES  ('34567890123', 'pedro.almeida@example.com', 'Pedro Almeida', '55511223344', 3);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES  ('45678901234', 'ana.oliveira@example.com', 'Ana Oliveira', '55544332211', 4);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES  ('56789012345', 'jose.costa@example.com', 'José Costa', '55533445566', 5);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES  ('67890123456', 'lucas.lima@example.com', 'Lucas Lima', '55566554433', 6);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES   ('78901234567', 'fernanda.martins@example.com', 'Fernanda Martins', '55577889900', 7);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES  ('89012345678', 'juliana.silveira@example.com', 'Juliana Silveira', '55599008877', 8);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES  ('90123456789', 'carlos.pereira@example.com', 'Carlos Pereira', '55500998877', 9);
INSERT INTO cliente (cpf, email, nome, telefone, endereco_id) VALUES  ('01234567890', 'lara.dias@example.com', 'Lara Dias', '55522334455', 10);

-- Seed funcionario
INSERT INTO funcionario(nome, cpf, email, telefone, usuario_login, senha_login) VALUES ('Igo Marcelino', '99933322299', 'igo@email.com', '11988772233', 'igomarcelino', '123456')
INSERT INTO funcionario(nome, cpf, email, telefone, usuario_login, senha_login) VALUES ('Ana Paula', '88811144455', 'ana.paula@email.com', '11987654321', 'anapaula', '654321');
INSERT INTO funcionario(nome, cpf, email, telefone, usuario_login, senha_login) VALUES ('Carlos Silva', '77722233344', 'carlos.silva@email.com', '11999887766', 'carlossilva', 'abcdef');
INSERT INTO funcionario(nome, cpf, email, telefone, usuario_login, senha_login) VALUES ('Mariana Santos', '66633344455', 'mariana.santos@email.com', '11988776655', 'marianasantos', '123abc');
INSERT INTO funcionario(nome, cpf, email, telefone, usuario_login, senha_login) VALUES ('Ricardo Pereira', '55544466677', 'ricardo.pereira@email.com', '11977665544', 'ricardopereira', 'pass123');

-- Seed tipos de servico
INSERT INTO servico(descricao, valor) VALUES('Formatacao de Computador', 120.00);
INSERT INTO servico(descricao, valor) VALUES('Troca de Processador', 180.00);
INSERT INTO servico(descricao, valor) VALUES('Limpeza completa', 200.00);
INSERT INTO servico(descricao, valor) VALUES('Reparo de Processador', 600.00);
INSERT INTO servico(descricao, valor) VALUES('Troca de Tela', 450.00);
INSERT INTO servico(descricao, valor) VALUES('Instalação de Software', 150.00);
INSERT INTO servico(descricao, valor) VALUES('Limpeza Interna', 200.00);
INSERT INTO servico(descricao, valor) VALUES('Upgrade de Memória RAM', 300.00);
INSERT INTO servico(descricao, valor) VALUES('Substituição de Teclado', 250.00);
INSERT INTO servico(descricao, valor) VALUES('Configuração de Rede', 350.00);

-- Seeds para a tabela ORDEM_SERVICO
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM) VALUES(1, 1, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(2, 3, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(3, 2, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(4, 4, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(9, 1, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(4, 2, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(7, 3, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(8, 4, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(2, 1, 0, 'ABERTA');
INSERT INTO ORDEM_SERVICO (CLIENTE_ID, FUNCIONARIO_ID, PAGAMENTO_ID, STATUS_ORDEM)VALUES(5, 2, 0, 'ABERTA');
