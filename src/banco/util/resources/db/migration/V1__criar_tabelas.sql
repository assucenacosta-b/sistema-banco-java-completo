
CREATE TABLE IF NOT EXISTS usuarios (
    id     BIGSERIAL PRIMARY KEY,
    login  VARCHAR(50)  NOT NULL UNIQUE,
    senha  VARCHAR(64)  NOT NULL,
    nome   VARCHAR(120) NOT NULL,
    perfil VARCHAR(20)  NOT NULL CHECK (perfil IN ('ADMIN', 'OPERADOR'))
);

CREATE TABLE IF NOT EXISTS clientes (
    id        BIGSERIAL PRIMARY KEY,
    nome      VARCHAR(120) NOT NULL,
    cpf       VARCHAR(14)  NOT NULL UNIQUE,
    telefone  VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS contas_correntes (
    id             BIGSERIAL PRIMARY KEY,
    numero_conta   VARCHAR(30)    NOT NULL UNIQUE,
    saldo          NUMERIC(15,2)  NOT NULL DEFAULT 0,
    limite_cheque  NUMERIC(15,2)  NOT NULL DEFAULT 0,
    cliente_id     BIGINT         NOT NULL REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS contas_poupanca (
    id               BIGSERIAL PRIMARY KEY,
    numero_conta     VARCHAR(30)    NOT NULL UNIQUE,
    saldo            NUMERIC(15,2)  NOT NULL DEFAULT 0,
    taxa_rendimento  NUMERIC(8,4)   NOT NULL DEFAULT 0,
    cliente_id       BIGINT         NOT NULL REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS transacoes (
    id          BIGSERIAL PRIMARY KEY,
    conta_id    BIGINT        NOT NULL,
    tipo_conta  VARCHAR(20)   NOT NULL CHECK (tipo_conta IN ('CORRENTE', 'POUPANCA')),
    descricao   VARCHAR(120)  NOT NULL,
    valor       NUMERIC(15,2) NOT NULL,
    data_hora   TIMESTAMP     NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_transacoes_conta ON transacoes (conta_id, tipo_conta);