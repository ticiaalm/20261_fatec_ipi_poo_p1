import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bardo extends Personagem {

    public Bardo(String nome) throws Exception {
        super(nome);
        carregarMusicas();
    }

    public Bardo(String nome, int energia, int fome, int sono) throws Exception {
        super(nome, energia, fome, sono);
        carregarMusicas();
    }

    List<Musica> disponivel = new ArrayList<>();

    private void carregarMusicas() throws Exception {
        List<Musica> musicas = MusicaDAO.listar();
        for (Musica musica : musicas) {
            disponivel.add(musica);
        }
    }

    @Override
    public void realizarAcaoAleatoria() {
        var gerador = new Random();
        int OQueFazer = gerador.nextInt(1, 11);

        if (estaVivo()) {
            switch (OQueFazer) {
                case 1, 2:
                    cacar();
                    break;
                case 3, 4:
                    comer();
                    break;
                case 5, 6, 7, 8, 9, 10:
                    dormir();
                    break;
            }
            aprenderMusica(disponivel);
            if (estaVivo()) {
                System.out.println(this);
            } else {
                morte();
            }
        }
    }

    @Override
    public void duelar(Personagem adversario) {
        Random gerador = new Random();
        if (repertorio.size() == 0) {
            System.out.println(nome + " não conhece nenhuma música. Duelo encerrado.");
        } else {
            int indice = gerador.nextInt(repertorio.size());
            Musica sorteada = repertorio.get(indice);
            System.out.println(nome + " iniciou o duelo com: " + sorteada.getTitulo());
            if (adversario.conheceMusica(sorteada)) {
                setEnergia(getEnergia() - 1);
                adversario.setEnergia(adversario.getEnergia() - 1);
                System.out.println(adversario.nome + " já conhece a música.");
                System.out.println("Ambos perdem 1 ponto de energia.");
            } else {
                adversario.setEnergia(adversario.getEnergia() - 1);
                adversario.repertorio.add(sorteada);
                System.out.println(adversario.nome + " não conhece a música.");
                System.out.println(
                        adversario.nome + " perdeu 1 ponto de energia, mas aprendeu a música: " + sorteada.getTitulo());
            }
        }
    }
}