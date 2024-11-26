Aluno aluno = new Aluno();
AlunoDAO dao = new AlunoDAO();
dao.save(aluno);

// Active Record (uma instância de aluno é ao mesmo um registro no Banco)
Aluno aluno = new Aluno();
aluno.save()
aluno.delete();

Conta conta;
// Active Record
conta.update();

// Active Record
$matricula = Matricula.find(234);
$matricula->delete();

// Outro Módulo/Outra classe responsável pela persistência
Data
Access
Object
// objeto de acesso a dados
// Table Data Gateway (descontinuado)

// Padrão Moderno: Repositório (repository)
