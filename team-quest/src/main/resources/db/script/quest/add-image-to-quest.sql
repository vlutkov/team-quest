ALTER TABLE quest ADD COLUMN image_id BIGINT UNIQUE NOT NULL;

COMMENT ON COLUMN quest.image_id IS 'Идентификатор картинки-баннера квеста';