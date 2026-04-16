public class Musica implements Comparable<Musica> {
    private String titulo;

    public Musica(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public int compareTo(Musica m) {
        return this.titulo.compareTo(m.titulo);
    }

    @Override
    public String toString() {
        return String.format(("(%s)"), titulo);
    }
}