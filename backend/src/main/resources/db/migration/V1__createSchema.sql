--DROP SCHEMA IF EXISTS healfiness_schema;

CREATE SCHEMA IF NOT EXISTS "${schema}"
    AUTHORIZATION postgres;

SET search_path TO "${schema}";