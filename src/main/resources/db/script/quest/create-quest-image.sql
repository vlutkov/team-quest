CREATE TABLE quest_image
(
    id          BIGSERIAL PRIMARY KEY,
    quest_id    BIGINT    NOT NULL
        REFERENCES quest (id),
    image_path  VARCHAR   NOT NULL,
    create_date TIMESTAMP NOT NULL DEFAULT NOW():: TIMESTAMP,
    update_date TIMESTAMP
);

COMMENT ON TABLE quest_image IS 'Фото квестов';

COMMENT ON COLUMN quest_image.id IS 'Идентификатор';
COMMENT ON COLUMN quest_image.image_path IS 'Относительный путь к файлу в файловом хранилище';
COMMENT ON COLUMN quest_image.create_date IS 'Дата создания';
COMMENT ON COLUMN quest_image.update_date IS 'Дата редактирования';