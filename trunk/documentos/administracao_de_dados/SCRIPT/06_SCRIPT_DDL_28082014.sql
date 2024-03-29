-- NOVO ATRIBUTO NA TABELA DOCUMENTO_UNIDADE
ALTER TABLE SAAD.DOCUMENTO_UNIDADE ADD TX_PASTA_FISICA VARCHAR(50);
comment on column SAAD.DOCUMENTO_UNIDADE.TX_PASTA_FISICA is 'Descrição do local onde guarda o fichário fisíco.';

-- RETIRAR MANDATORIO DO ATRIBUTO NR_DOCUMENTO_ORIGEM DA TABELA DOCUMENTO_UNIDADE
ALTER TABLE SAAD.DOCUMENTO_UNIDADE ALTER COLUMN NR_DOCUMENTO_ORIGEM DROP NOT NULL;

-- NOVO ATRIBUTO NA TABELA EVENTO
ALTER TABLE SAAD.EVENTO ADD NM_RESPONSAVEL_CONVITE VARCHAR(150);
comment on column SAAD.EVENTO.NM_RESPONSAVEL_CONVITE is 'Nome do responsável pelo convite. Referente ao nome de quem está convidando para o evento.';