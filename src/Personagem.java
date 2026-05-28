import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Personagem {
    String nome;
    private int energia;
    private int fome;
    private int sono;
    List<String> mochila = new ArrayList<>();
    List<Musica> repertorio = new ArrayList<>();

    Personagem(String nome) {
        this.nome = nome;
        System.out.println("Construindo novo personagem: " + nome);
        energia = 10;
        fome = 0;
        sono = 0;
    }

    Personagem(String nome, int energia, int fome, int sono) {
        System.out.println("Construindo novo personagem: " + nome);
        this.nome = nome;
        this.energia = energia < 0 || energia > 10 ? 10 : energia;
        this.fome = fome >= 0 && fome <= 10 ? fome : 0;
        this.sono = sono >= 0 && sono <= 10 ? sono : 0;
    }

    void morte() {
        if (energia <= 0) {
            System.out.println(nome + " Morreu");
            System.out.println("Inventario de " + nome + ": " + mochila);
            System.out.println("Repertorio de " + nome + ": " + repertorio);
            System.out.println("**********");
        }
    }

    boolean estaVivo() {
        return energia > 0;
    }

    void cacar() {
        if (energia >= 2) {
            System.out.printf("%s caçando\n", nome);
            energia -= 2;
        } else {
            System.out.printf(
                    "%s sem energia para caçar\n", nome);
        }
        if (fome < 10)
            fome = fome + 1;
        sono = sono == 10 ? sono : sono + 1;
    }

    void comer() {
        if (fome >= 1) {
            System.out.println(nome + " comendo");
            energia = Math.min(energia + 1, 10);
            fome--;
        } else {
            System.out.println(nome + " sem fome");
        }
    }

    void dormir() {
        if (sono >= 1) {
            System.out.print(nome + " dormindo\n");
            energia = energia == 10 ? energia : energia + 1;
            sono -= 1;
        } else {
            System.out.println(nome + " sem sono");
        }
    }

    public String toString() {
        return String.format(
                "%s: e:%d, f:%d, s:%d\nmochila:%s\nrepertorio:%s\n",
                nome, energia, fome, sono, mochila, repertorio);
    }

    void aprenderMusica(List<Musica> disponivel) {
        Random gerador = new Random();
        int indice = gerador.nextInt(disponivel.size());
        Musica sorteada = disponivel.get(indice);
        if (repertorio.contains(sorteada)) {
            System.out.println(nome + " já conhece a música: " + sorteada.getTitulo());
        } else {
            repertorio.add(sorteada);
            System.out.println(nome + " aprendeu a música: " + sorteada.getTitulo());
        }
    }

    void adicionaMusica(Musica musica) {
        repertorio.add(musica);
    }

    boolean conheceMusica(Musica musica) {
        for (int i = 0; i < repertorio.size(); i++) {
            if (repertorio.get(i).getTitulo().equals(musica.getTitulo())) {
                return true;
            }
        }
        return false;
    }

    boolean temItem(String item) {
        for (int i = 0; i < mochila.size(); i++) {
            if (mochila.get(i).equals(item)) {
                return true;
            }
        }
        return false;
    }

    public abstract void realizarAcaoAleatoria();

    public abstract void duelar(Personagem adversario);

    public int getEnergia() {
        return energia;
    }

    public int getFome() {
        return fome;
    }

    public int getSono() {
        return sono;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setFome(int fome) {
        this.fome = fome;
    }

    public void setSono(int sono) {
        this.sono = sono;
    }
}