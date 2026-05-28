import java.util.Random;

public class Jogo {
    public static void main(String[] args) throws Exception {
        Personagem cacador = new Cacador("Vita Violinista");
        Personagem tico = new Bardo("Tico Trovador", 3, 8, 8);
        tico.repertorio.add(new Musica("God's Plan"));
        boolean campeao = false;

        while (cacador.estaVivo() || tico.estaVivo()) {
            cacador.realizarAcaoAleatoria();
            tico.realizarAcaoAleatoria();
            System.out.println("**********");

            var gerador = new Random();
            int quemDuela = gerador.nextInt(1, 3);

            if (cacador.estaVivo() && tico.estaVivo()) {
                switch (quemDuela) {
                    case 1: {
                        tico.duelar(cacador);
                        break;
                    }
                    case 2: {
                        cacador.duelar(tico);
                        break;
                    }
                }
                System.out.println("**********");
                tico.morte();
                cacador.morte();
            }

            if (!campeao) {
                if (!cacador.estaVivo() && tico.estaVivo()) {
                    System.out.println(cacador.nome + " está morto!");
                    System.out.println(tico.nome + " é o campeão!");
                    campeao = true;
                    System.out.println("**********");
                } else if (cacador.estaVivo() && !tico.estaVivo()) {
                    System.out.println(tico.nome + " está morto!");
                    System.out.println(cacador.nome + " é o campeão!");
                    campeao = true;
                    System.out.println("**********");

                } else if (!cacador.estaVivo() && !tico.estaVivo()) {
                    System.out.println("Empate! Os dois morreram juntos.");
                    campeao = true;
                    System.out.println("**********");
                }
            }
            Thread.sleep(5000);
        }
        System.out.println("Não restaram personagens vivos. GAME OVER.");
    }
}