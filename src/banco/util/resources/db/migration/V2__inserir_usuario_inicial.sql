

INSERT INTO usuarios (login, senha, nome, perfil)
VALUES (
    'assucenacosta',
    'dd6d2a1cb7a13c23508ebdacef68d9669e61c19539f22080e889849b7f9f3b67',
    'Assucena Costa Belarmino dos Reis',
    'ADMIN'
)
ON CONFLICT (login) DO NOTHING;