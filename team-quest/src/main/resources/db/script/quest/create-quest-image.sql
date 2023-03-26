CREATE TABLE quest_image
(
    id                BIGSERIAL PRIMARY KEY,
    quest_id          BIGINT    NOT NULL
        REFERENCES quest (id),
    external_image_id BIGINT    NOT NULL,
    is_preview        BOOL,
    create_date       TIMESTAMP NOT NULL DEFAULT NOW():: TIMESTAMP,
    update_date       TIMESTAMP
);

COMMENT ON TABLE quest_image IS 'Фото квестов';

COMMENT ON COLUMN quest_image.id IS 'Идентификатор';
COMMENT ON COLUMN quest_image.external_image_id IS 'Идентификатор фото в файлом хранилище';
COMMENT ON COLUMN quest_image.is_preview IS 'Используется ли фото для превью квеста';
COMMENT ON COLUMN quest_image.create_date IS 'Дата создания';
COMMENT ON COLUMN quest_image.update_date IS 'Дата редактирования';