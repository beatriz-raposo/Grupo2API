DO '
BEGIN
    IF NOT EXISTS (SELECT * FROM usuario) THEN
        INSERT INTO usuario (nome, sobrenome, email, senha, data_nascimento) VALUES 
            (''João'', ''Silva'', ''joao.silva@example.com'', ''$2a$12$sPPV9up/RlaZGUBA1AU7ju66f4o.eNSGhhCaWUdr4rnvDZ.QjaMtK'', ''2004-01-01''),
            (''Beltrano'', ''Fulano'', ''beltrano6@email.com'', ''$2a$10$VSNkf9uSMo/hYnjgRyzjCu.sTyd0ke1ECVxELvS9RY83h3H3C3N7K'', ''2004-01-01''), 
            (''Maria'', ''Sousa'', ''maria@email.com'', ''$2a$10$7qjx6.X6Ks/Alr89qKGnMO6TyjHesE0hDWg.1G6.1Wr5eYB6hdHQy'', ''1990-08-28''), 
            (''João'', ''Silveira'', ''joao@email.com'', ''$2a$10$USyTp/imBubmFEm7F5TM2.gqi983mdR5da9vdi5btQNCh43krt.s.'', ''1985-03-12''), 
            (''Ana'', ''Lima'', ''ana@email.com'', ''$2a$10$hIhRZ0lGoRjgs4.lE0KLfOi0tVZqlfqFhLFitWWBHfVPbrR2Wxh2i'', ''1998-07-25''), 
            (''Pedro'', ''Almeida'', ''pedro@email.com'', ''$2a$10$E/XEsCuPvmie9keiUqosbegsxbEgQiyGbGXIhN0Rf3KAj7TzrkmSK'', ''1982-11-10'');
    END IF;
    
    IF NOT EXISTS (SELECT * FROM postagem) THEN
        INSERT INTO postagem (conteudo, data_criacao, autor_id) VALUES 
			(''O gato está em cima do telhado.'', ''2024-05-26'', ''1''),
			(''A chuva cai suavemente sobre a cidade.'', ''2024-05-25'', ''2''),
			(''O mercado de ações subiu novamente.'', ''2024-05-24'', ''3''),
			(''Ela gosta de ler livros de ficção científica.'', ''2024-05-23'', ''4''),
			(''Estamos planejando uma viagem para a praia.'', ''2024-05-22'', ''5''),
			(''O jantar estará pronto em uma hora.'', ''2024-05-21'', ''6'');
	END IF;
	
	IF NOT EXISTS (SELECT * FROM comentario) THEN
        INSERT INTO comentario (texto, data_criacao, author_id, id_postagem) VALUES 
			(''O gato parece estar muito confortável no telhado.'', ''2024-05-26'', ''1'', ''1''),
			(''Adoro ouvir a chuva cair, é muito relaxante.'', ''2024-05-25'', ''2'', ''2''),
			(''É ótimo ver o mercado de ações subindo novamente.'', ''2024-05-24'', ''3'', ''3''),
			(''Livros de ficção científica são realmente fascinantes.'', ''2024-05-23'', ''4'', ''4''),
			(''Mal posso esperar pela nossa viagem para a praia!'', ''2024-05-22'', ''5'', ''5''),
			(''Estou ansioso pelo jantar, aposto que será delicioso.'', ''2024-05-21'', ''6'', ''6'');
	END IF;
	
	IF NOT EXISTS (SELECT * FROM relacionamento) THEN

        INSERT INTO relacionamento (id_seguidor, id_seguindo, data_inicio_seguimento) VALUES 
            (''2'', ''1'', ''2024-05-27''),
            (''3'', ''1'', ''2024-05-27''),
                (''5'', ''3'', ''2024-05-29''),
                (''1'', ''3'', ''2024-05-28''),
                (''4'', ''1'', ''2024-05-28'');

    END IF;
	

END;
'

