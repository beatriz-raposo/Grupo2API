DO '
BEGIN
    IF NOT EXISTS (SELECT * FROM usuario) THEN
        INSERT INTO usuario (nome, sobrenome, email, senha, data_nascimento) VALUES 
            (''João'', ''Silva'', ''joao.silva@example.com'', ''$2a$12$sPPV9up/RlaZGUBA1AU7ju66f4o.eNSGhhCaWUdr4rnvDZ.QjaMtK'', ''2004-01-01'', '' ),
            (''Beltrano'', ''Fulano'', ''beltrano6@email.com'', ''$2a$10$VSNkf9uSMo/hYnjgRyzjCu.sTyd0ke1ECVxELvS9RY83h3H3C3N7K'', ''2004-01-01''), 
            (''Maria'', ''Sousa'', ''maria@email.com'', ''$2a$10$7qjx6.X6Ks/Alr89qKGnMO6TyjHesE0hDWg.1G6.1Wr5eYB6hdHQy'', ''1990-08-28''), 
            (''João'', ''Silveira'', ''joao@email.com'', ''$2a$10$USyTp/imBubmFEm7F5TM2.gqi983mdR5da9vdi5btQNCh43krt.s.'', ''1985-03-12''), 
            (''Ana'', ''Lima'', ''ana@email.com'', ''$2a$10$hIhRZ0lGoRjgs4.lE0KLfOi0tVZqlfqFhLFitWWBHfVPbrR2Wxh2i'', ''1998-07-25''), 
            (''Pedro'', ''Almeida'', ''pedro@email.com'', ''$2a$10$E/XEsCuPvmie9keiUqosbegsxbEgQiyGbGXIhN0Rf3KAj7TzrkmSK'', ''1982-11-10'');
    END IF;
END;
'
