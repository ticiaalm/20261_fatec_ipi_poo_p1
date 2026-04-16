import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {
    public static void main(String[] args) throws Exception {
        var gerador = new Random();
        var cacador = new Personagem("Vita Violinista");
        var tico = new Personagem("Tico Trovador", 3, 8, 8);
        tico.repertorio.add(new Musica("God's Plan"));
        boolean campeao = false;

        String[] itens = {
                "Pena",
                "Couro",
                "Osso",
                "Presa",
                "Garra"
        };

        List<Musica> disponivel = new ArrayList<>();
        String[] titulos = {
                "Center Mass",
                "Purple Rain",
                "Kidult",
                "End of Beginning",
                "In a Lake",
                "Timeless",
                "Don't Fear The Reaper",
                "Karma Police",
                "The Boss",
                "Layla"
        };

        for (int i = 0; i < 10; i++) {
            disponivel.add(new Musica(titulos[i]));
        }
        System.out.println("******************************");
        while (!cacador.estaMorto() || !tico.estaMorto()) {
            int OQueFazer = gerador.nextInt(1, 4);
            int acaoTico = gerador.nextInt(1, 11);
            int quemDuela = gerador.nextInt(1, 3);

            if (!tico.estaMorto()) {
                switch (acaoTico) {
                    case 1, 2: {
                        int QualItem = gerador.nextInt(1, 6);
                        tico.mochila.add(itens[QualItem - 1]);
                        tico.cacar();
                        break;
                    }
                    case 3, 4:
                        tico.comer();
                        break;
                    case 5, 6, 7, 8, 9, 10:
                        tico.dormir();
                        break;
                }
                System.out.println(tico);
                tico.aprenderMusica(disponivel);
                System.out.println("Repertório: " + tico.repertorio);
                tico.morte(tico.nome, tico.mochila, tico.repertorio);
                System.out.println("******************************");
            }
            if (!cacador.estaMorto()) {
                switch (OQueFazer) {
                    case 1: {
                        int QualItem = gerador.nextInt(1, 6);
                        cacador.mochila.add(itens[QualItem - 1]);
                        cacador.cacar();
                        break;
                    }
                    case 2:
                        cacador.comer();
                        break;
                    case 3:
                        cacador.dormir();
                        break;
                }
                System.out.println(cacador);
                cacador.aprenderMusica(disponivel);
                System.out.println("Repertório: " + cacador.repertorio);
                cacador.morte(cacador.nome, cacador.mochila, cacador.repertorio);
                System.out.println("******************************");
            }
            if (!cacador.estaMorto() && !tico.estaMorto()) {
                switch (quemDuela) {
                    case 1: {
                        tico.dueloMusical(cacador);
                        break;
                    }
                    case 2: {
                        cacador.dueloMusical(tico);
                        break;
                    }
                }
            }
            if (!campeao) {
                if (cacador.estaMorto() && !tico.estaMorto()) {
                    System.out.println(cacador.nome + " está MORTO!");
                    System.out.println(tico.nome + " é o CAMPEÃO!");
                    campeao = true;
                } else if (!cacador.estaMorto() && tico.estaMorto()) {
                    System.out.println(tico.nome + " está MORTO!");
                    System.out.println(cacador.nome + " é o CAMPEÃO!");
                    campeao = true;
                }
            }
            Thread.sleep(5000);
        }
        System.out.println("Não restaram personagens vivos. GAME OVER.");
    }
}