-- Création de la séquence pour l'ID
CREATE SEQUENCE player_id_seq;

-- Création de la table players
CREATE TABLE players
(
    player_id BIGINT NOT NULL DEFAULT nextval('player_id_seq'),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    birth_day DATE NOT NULL,
    rank_id INTEGER NOT NULL,
    points INTEGER NOT NULL,
    CONSTRAINT pk_players PRIMARY KEY (player_id)
);

-- Liaison de la séquence à la colonne player_id
ALTER SEQUENCE player_id_seq OWNED BY players.player_id;

-- Définition du propriétaire de la table
ALTER TABLE IF EXISTS public.players OWNER TO postgres;
