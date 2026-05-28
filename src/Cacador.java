import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cacador extends Personagem {

    public Cacador(String nome) {
        super(nome);
        carregarItens();
    }

    public Cacador(String nome, int energia, int fome, int sono) {
        super(nome, energia, fome, sono);
        carregarItens();
    }

    List<String> itens = new ArrayList<>();

    private void carregarItens() {
        String[] nomeItens = {
                "Pena",
                "Couro",
                "Osso",
                "Presa",
                "Garra"
        };
        for (int i = 0; i < nomeItens.length; i++) {
            itens.add(nomeItens[i]);
        }
    }

    @Override
    public void realizarAcaoAleatoria() {
        var gerador = new Random();
        int OQueFazer = gerador.nextInt(1, 4);
        if (estaVivo()) {
            switch (OQueFazer) {
                case 1: {
                    cacar();
                    int QualItem = gerador.nextInt(1, 6);
                    mochila.add(itens.get(QualItem - 1));
                    break;
                }
                case 2:
                    comer();
                    break;
                case 3:
                    dormir();
                    break;
            }
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
        if (mochila.size() == 0) {
            System.out.println(nome + " não tem nenhum item. Duelo encerrado.");
        } else {
            int indice = gerador.nextInt(mochila.size());
            String item = mochila.get(indice);
            System.out.println(nome + " iniciou o duelo com: " + item);
            if (adversario.temItem(item)) {
                setEnergia(getEnergia() - 1);
                adversario.setEnergia(adversario.getEnergia() - 1);
                System.out.println(adversario.nome + " já tem esse item.");
                System.out.println("Ambos perdem 1 ponto de energia.");
            } else {
                adversario.setEnergia(adversario.getEnergia() - 1);
                adversario.mochila.add(item);
                mochila.remove(indice);
                System.out.println(adversario.nome + " não tem esse item.");
                System.out.println(adversario.nome + " perdeu 1 ponto de energia, mas ganhou o item: " + item);
            }
        }

    }
}