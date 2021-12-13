INSERT INTO Pokemon(
    name_pk,
    type_1,
    type_2,
    stats_total,
    stats_hp,
    stats_attack,
    stats_defense,
    stats_sp_attack,
    stats_sp_def,
    stats_speed,
    generation,
    legendary
)
SELECT *
FROM CSVRead('/home/lrivera/ProjetosPessoais/pokemon_spring_studies/src/main/resources/pokemon.csv');


INSERT INTO USUARIO(nome, email, senha) VALUES('Administrador', 'admin@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
