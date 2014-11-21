ALTER TABLE SAAD.ENDERECAMENTO_INTERNO ADD SEQ_USUARIO INTEGER;
comment on column SAAD.ENDERECAMENTO_INTERNO.SEQ_USUARIO is
'Representa o vínculo com do SEQ_LOCAL tabela USUARIO do banco JUSTIÇA COMUNS.
Identifica para qual usuário esta sendo endereçado o documento.';

alter table SAAD.ENDERECAMENTO_INTERNO
   add constraint FK_ENIN_USUARIO foreign key (SEQ_USUARIO)
      references DB2SA.USUARIO (SEQ_USUARIO)
      on delete restrict on update restrict;

create index SAAD.IX_USUARIO_SEQ_USUARIO on SAAD.ENDERECAMENTO_INTERNO (
   SEQ_USUARIO          ASC
);