CREATE TABLE quest
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(32) UNIQUE NOT NULL,
    type           SMALLINT           NOT NULL
        REFERENCES quest_type (id),
    min_age        SMALLINT           NOT NULL,
    max_age        SMALLINT,
    min_person_num SMALLINT           NOT NULL,
    max_person_num SMALLINT           NOT NULL,
    complexity     SMALLINT           NOT NULL,
    rating         REAL               NOT NULL,
    start_price    NUMERIC            NOT NULL,
    spent_time     SMALLINT           NOT NULL,
    description    TEXT               NOT NULL,
    create_date    TIMESTAMP          NOT NULL DEFAULT NOW():: TIMESTAMP,
    update_date    TIMESTAMP
);

COMMENT ON TABLE quest IS 'Квесты';

COMMENT ON COLUMN quest.id IS 'Идентификатор';
COMMENT ON COLUMN quest.name IS 'Наименование';
COMMENT ON COLUMN quest.type IS 'Тип квеста';
COMMENT ON COLUMN quest.min_age IS 'Минимальный возраст участников';
COMMENT ON COLUMN quest.max_age IS 'Минимальный возраст участников';
COMMENT ON COLUMN quest.min_person_num IS 'Минимальный количество участников';
COMMENT ON COLUMN quest.max_person_num IS 'Максимальное количество участников';
COMMENT ON COLUMN quest.complexity IS 'Сложность';
COMMENT ON COLUMN quest.rating IS 'Рейтинг';
COMMENT ON COLUMN quest.start_price IS 'Начальная цена';
COMMENT ON COLUMN quest.spent_time IS 'Время прохождения';
COMMENT ON COLUMN quest.description IS 'Описание';
COMMENT ON COLUMN quest.create_date IS 'Дата создания';
COMMENT ON COLUMN quest.update_date IS 'Дата редактирования';