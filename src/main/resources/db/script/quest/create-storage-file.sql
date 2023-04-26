CREATE TABLE storage_file
(
    id          BIGSERIAL PRIMARY KEY,
    file_name   VARCHAR(64) NOT NULL,
    data        BYTEA       NOT NULL,
    size        INTEGER     NOT NULL,
    extension   VARCHAR(8),
    create_date TIMESTAMP   NOT NULL DEFAULT NOW():: timestamp,
    update_date TIMESTAMP
);

COMMENT ON TABLE storage_file IS 'Сохраненные файлы';

COMMENT ON COLUMN storage_file.id IS 'Идентификатор';
COMMENT ON COLUMN storage_file.file_name IS '';
COMMENT ON COLUMN storage_file.data IS 'Содержимое файла';
COMMENT ON COLUMN storage_file.size IS 'Размер файла';
COMMENT ON COLUMN storage_file.extension IS 'Расширение файла';
COMMENT ON COLUMN storage_file.create_date IS 'Дата создания';
COMMENT ON COLUMN storage_file.update_date IS 'Дата редактирования';