CREATE TABLE parcelamento (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    descricao VARCHAR(20) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    quantidade_parcelas TINYINT UNSIGNED,
    data_criacao DATETIME NOT NULL,
    FOREIGN KEY(cliente_id) REFERENCES cliente(id)
);