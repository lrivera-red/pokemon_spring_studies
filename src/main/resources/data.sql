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
FROM CSVRead('D:\Documentos\Java-estudos\pokemon-estudo\src\main\resources\pokemon.csv');