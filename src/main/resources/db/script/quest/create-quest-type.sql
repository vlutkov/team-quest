CREATE TABLE quest_type
(
    id          SMALLSERIAL PRIMARY KEY,
    name        VARCHAR(32) NOT NULL,
    code        VARCHAR(32) NOT NULL,
    create_date TIMESTAMP   NOT NULL DEFAULT NOW():: timestamp,
    update_date TIMESTAMP
);

COMMENT ON TABLE quest_type IS 'Типы квестов';

COMMENT ON COLUMN quest_type.id IS 'Идентификатор';
COMMENT ON COLUMN quest_type.name IS 'Наименование';
COMMENT ON COLUMN quest_type.code IS 'Код';
COMMENT ON COLUMN quest_type.create_date IS 'Дата создания';
COMMENT ON COLUMN quest_type.update_date IS 'Дата редактирования';

INSERT INTO quest_type (name, code) VALUES ('Загадка', 'mystery');
INSERT INTO quest_type (name, code) VALUES ('Ужасы', 'horror');
INSERT INTO quest_type (name, code) VALUES ('Детский', 'childish');